package org.billthefarmer.draw;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.Menu;
import android.view.MenuItem;
import java.io.File;
import java.io.FileOutputStream;

public class Main extends Activity
{
    private Paint paint;
    private Path path;
    private RectF rect;

    private LinearGradient black;
    private LinearGradient darkgreen;
    private LinearGradient green;
    private LinearGradient yellow;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

	// Create paint

	paint = new Paint();
	path = new Path();
	rect = new RectF(-224, -224, 224, 224);

	black = new LinearGradient(0, 0, 0, -512,
				   Color.BLACK,
				   Color.WHITE,
				   Shader.TileMode.CLAMP);
	darkgreen = new LinearGradient(0, 0, 0, -512,
				       Color.rgb(0, 128, 0),
				       Color.WHITE,
				       Shader.TileMode.CLAMP);
	green = new LinearGradient(0, 0, 0, -512,
				   Color.GREEN,
				   Color.WHITE,
				   Shader.TileMode.CLAMP);
	yellow = new LinearGradient(0, 0, 0, -512,
				    Color.YELLOW,
				    Color.WHITE,
				    Shader.TileMode.CLAMP);
    }

    // On create option menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
	// Inflate the menu; this adds items to the action bar if it
	// is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
    }

    // On options item

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
	// Get id

	int id = item.getItemId();
	switch (id)
	{
	    // Settings

	case R.id.action_save:
	    save();

	    return true;

	default:
	    return false;
	}
    }

    // Save

    private void save()
    {
	Bitmap bitmap = Bitmap.createBitmap(512, 512, Bitmap.Config.ARGB_8888);
	Canvas canvas = new Canvas(bitmap);

	canvas.drawColor(Color.TRANSPARENT);
	canvas.translate(256, 256);

	paint.setShader(black);
	paint.setStyle(Paint.Style.FILL);
	paint.setAntiAlias(true);
	canvas.drawRoundRect(rect, 64, 64, paint);
	canvas.clipRect(rect);

	paint.setStyle(Paint.Style.STROKE);
	paint.setShader(darkgreen);
	paint.setStrokeWidth(8);

	for (int i = -160; i <= 160; i += 64)
	    canvas.drawLine(i, -224, i, 224, paint);

	for (int i = -160; i <= 160; i += 64)
	    canvas.drawLine(-224, i, 224, i, paint);

	path.rewind();
	path.moveTo(-224, 160);

	float a = 320;
	float b = -64;
	float c = 32;

	for (int x = -224; x <= 224; x++)
	{
	    float y = 160 - a * (float)Math.exp(-((x - b) * (x - b)) /
						(2 * c * c));
	    path.lineTo(x, y);
	}

	paint.setShader(green);
	canvas.drawPath(path, paint);

	paint.setShader(yellow);
	canvas.drawLine(-64, -224, -64, 224, paint);

	try
	{
	    File file = new File(getExternalFilesDir(null), "icon.png");
	    FileOutputStream stream = new FileOutputStream(file);

	    bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
	    stream.close();
	}

	catch(Exception e) {}
    }
}
