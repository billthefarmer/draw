package org.billthefarmer.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class Draw extends View
{
    private int width;
    private int height;

    private Paint paint;
    private Path path;
    private RectF rect;

    public Draw(Context context, AttributeSet attrs)
    {
	super(context, attrs);

	// Create paint

	paint = new Paint();
	path = new Path();
	rect = new RectF(-224, -224, 224, 224);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
	super.onSizeChanged(w, h, oldw, oldh);

	// Get dinemsions

	width = w;
	height = h;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
	canvas.drawColor(Color.WHITE);
	canvas.translate(width / 2, height / 2);

	paint.setColor(Color.BLACK);
	paint.setStyle(Paint.Style.FILL);
	paint.setAntiAlias(true);
	canvas.drawRoundRect(rect, 64, 64, paint);
	canvas.clipRect(rect);

	paint.setStyle(Paint.Style.STROKE);
	paint.setColor(Color.rgb(0, 128, 0));
	paint.setStrokeWidth(8);

	for (int i = -160; i <= 160; i += 64)
	    canvas.drawLine(i, -224, i, 224, paint);

	for (int i = -160; i <= 160; i += 64)
	    canvas.drawLine(-224, i, 224, i, paint);

	path.rewind();
	path.moveTo(-224, 160);

	float a = 320;
	float b = -48;
	float c = 32;

	for (int x = -224; x <= 224; x++)
	{
	    float y = 160 - a * (float)Math.exp(-((x - b) * (x - b)) /
						(2 * c * c));
	    path.lineTo(x, y);
	}

	paint.setColor(Color.GREEN);
	canvas.drawPath(path, paint);

	paint.setColor(Color.YELLOW);
	canvas.drawLine(-48, -224, -48, 224, paint);
    }
}
