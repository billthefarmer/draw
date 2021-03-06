package org.billthefarmer.draw;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileOutputStream;

public class Main extends Activity
{
    public static final int WIDTH = 512;

    private Draw draw;

    // Called when the activity is first created.
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        draw = findViewById(R.id.draw);
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
        case R.id.action_draw:
            break;

        case R.id.action_save:
            saveIcon();
            break;

        default:
            drawIcon(id);
            break;
        }

        return true;
    }

    // drawIcon
    private void drawIcon(int id)
    {
        if (draw != null)
        {
            draw.icon = id;
            draw.invalidate();
        }
    }

    // saveIcon
    private void saveIcon()
    {
        Bitmap bitmap =
            Bitmap.createBitmap(WIDTH, WIDTH, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        canvas.drawColor(Color.TRANSPARENT);
        canvas.translate(WIDTH / 2, WIDTH / 2);

        if (draw != null)
            draw.drawIcon(canvas);

        File file = new File(getExternalFilesDir(null), "icon.png");
             
        try (FileOutputStream stream = new FileOutputStream(file))
        {
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        }
        catch (Exception e)
        {
        }
    }
}
