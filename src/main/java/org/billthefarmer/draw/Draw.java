package org.billthefarmer.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class Draw extends View
{
    public static final int STEP = 64;
    public static final int ICON_WIDTH = 240;

    private int width;
    private int height;

    private Paint paint;
    private Path path;
    private RectF rect;

    private LinearGradient black;
    private LinearGradient darkgreen;
    private LinearGradient green;
    private LinearGradient yellow;
    private LinearGradient blue;
    private LinearGradient cyan;
    private LinearGradient magenta;
    private LinearGradient olive;
    private LinearGradient aqua;

    public Draw(Context context, AttributeSet attrs)
    {
	super(context, attrs);

	// Create paint

	paint = new Paint();
	path = new Path();
	rect = new RectF(-ICON_WIDTH, -ICON_WIDTH, ICON_WIDTH, ICON_WIDTH);

	black = new LinearGradient(0, 0, 0, -Main.WIDTH,
				   Color.BLACK,
				   Color.WHITE,
				   Shader.TileMode.CLAMP);
	darkgreen = new LinearGradient(0, 0, 0, -Main.WIDTH,
				       Color.rgb(0, 128, 0),
				       Color.WHITE,
				       Shader.TileMode.CLAMP);
	green = new LinearGradient(0, 0, 0, -Main.WIDTH,
				   Color.GREEN,
				   Color.WHITE,
				   Shader.TileMode.CLAMP);
	yellow = new LinearGradient(0, 0, 0, -Main.WIDTH,
				    Color.YELLOW,
				    Color.WHITE,
				    Shader.TileMode.CLAMP);
 	blue = new LinearGradient(0, 0, 0, -Main.WIDTH,
				    Color.BLUE,
				    Color.WHITE,
				    Shader.TileMode.CLAMP);
 	cyan = new LinearGradient(0, 0, 0, -Main.WIDTH,
				    Color.CYAN,
				    Color.WHITE,
				    Shader.TileMode.CLAMP);
 	magenta = new LinearGradient(0, 0, 0, -Main.WIDTH,
				    Color.MAGENTA,
				    Color.WHITE,
				    Shader.TileMode.CLAMP);
 	olive = new LinearGradient(0, 0, 0, -Main.WIDTH,
				    0xff6f6f00,
				    Color.WHITE,
				    Shader.TileMode.CLAMP);
 	aqua = new LinearGradient(0, 0, 0, -Main.WIDTH,
				    0xffbfffbf,
				    Color.WHITE,
				    Shader.TileMode.CLAMP);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
	super.onSizeChanged(w, h, oldw, oldh);

	// Get dimensions

	width = w;
	height = h;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
	canvas.translate(width / 2, height / 2);

	drawIcon(canvas);
    }

    protected void drawIcon(Canvas canvas)
    {
	paint.setShader(black);
	paint.setStyle(Paint.Style.FILL);
	paint.setAntiAlias(true);
	canvas.drawRoundRect(rect, STEP, STEP, paint);
	canvas.clipRect(rect);

	paint.setStyle(Paint.Style.STROKE);
	paint.setShader(darkgreen);
	paint.setStrokeWidth(8);

	for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
	    canvas.drawLine(i, -ICON_WIDTH, i, ICON_WIDTH, paint);

	for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
	    canvas.drawLine(-ICON_WIDTH, i, ICON_WIDTH, i, paint);

	path.rewind();
	path.moveTo(-ICON_WIDTH, ICON_WIDTH - STEP);

	float a = 320;
	float b = -STEP;
	float c = 32;

	for (int x = -ICON_WIDTH; x <= ICON_WIDTH; x++)
	{
	    float y = ICON_WIDTH - STEP - a *
		(float)Math.exp(-((x - b) * (x - b)) / (2 * c * c));
	    path.lineTo(x, y);
	}

	paint.setShader(green);
	canvas.drawPath(path, paint);

	paint.setShader(yellow);
	canvas.drawLine(-STEP, -ICON_WIDTH, -STEP, ICON_WIDTH, paint);
    }
}
