package org.billthefarmer.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class Draw extends View
{
    public static final int STEP = 64;
    public static final int XSTEP = 56;
    public static final int YSTEP = 48;
    public static final int ICON_WIDTH = 224;

    private int width;
    private int height;

    private Paint paint;
    private Path path;

    private RectF rect;
    private RectF topRight;
    private RectF bottomLeft;

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

	topRight = new RectF(0, -ICON_WIDTH, ICON_WIDTH, 0);
	bottomLeft = new RectF(-ICON_WIDTH, 0, 0, ICON_WIDTH);

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
				   Color.rgb(111, 111, 0),
				   // 0xff6f6f00,
				   Color.WHITE,
				   Shader.TileMode.CLAMP);
 	aqua = new LinearGradient(0, 0, 0, -Main.WIDTH,
				  Color.rgb(191, 255, 191),
				  // 0xffbfffbf,
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
	paint.setStyle(Paint.Style.FILL);
	paint.setAntiAlias(true);

	paint.setShader(black);
	canvas.drawRoundRect(rect, STEP, STEP, paint);
	// canvas.clipRect(rect);

	paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeCap(Paint.Cap.BUTT);
	paint.setShader(darkgreen);
	paint.setStrokeWidth(8);

	for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
	    canvas.drawLine(i, -ICON_WIDTH, i, ICON_WIDTH, paint);

	for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
	    canvas.drawLine(-ICON_WIDTH, i, ICON_WIDTH, i, paint);

	path.rewind();

	// Sine
	path.moveTo(-ICON_WIDTH, 0);
	for (int x = -ICON_WIDTH; x <= ICON_WIDTH; x++)
	{
	    float y = (float)
		Math.sin(x * 1 * Math.PI / ICON_WIDTH) * YSTEP * 3;
	    path.lineTo(x, y);
	}

	paint.setShader(green);
	paint.setStrokeCap(Paint.Cap.ROUND);
	canvas.drawPath(path, paint);
    }

    protected void drawSiggenIcon(Canvas canvas)
    {
	paint.setStyle(Paint.Style.FILL);
	paint.setAntiAlias(true);

	paint.setShader(black);
	canvas.drawRoundRect(rect, STEP, STEP, paint);
	// canvas.clipRect(rect);

	paint.setStyle(Paint.Style.STROKE);
	paint.setStrokeCap(Paint.Cap.BUTT);
	paint.setShader(darkgreen);
	paint.setStrokeWidth(8);

	for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
	    canvas.drawLine(i, -ICON_WIDTH, i, ICON_WIDTH, paint);

	for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
	    canvas.drawLine(-ICON_WIDTH, i, ICON_WIDTH, i, paint);

	path.rewind();

	// Sine
	path.moveTo(-ICON_WIDTH, -ICON_WIDTH + (STEP * 3) / 2);
	for (int x = -ICON_WIDTH; x <= ICON_WIDTH; x++)
	{
	    float y = -ICON_WIDTH + (STEP * 3) / 2 - (float)
		Math.sin(x * 4 * Math.PI / ICON_WIDTH) * YSTEP;
	    path.lineTo(x, y);
	}

	// Square
	path.moveTo(-ICON_WIDTH, 0);
	path.rLineTo(0, -YSTEP);
	path.rLineTo(XSTEP, 0);
	path.rLineTo(0, YSTEP * 2);
	path.rLineTo(XSTEP, 0);
	path.rLineTo(0, -YSTEP * 2);
	path.rLineTo(XSTEP, 0);
	path.rLineTo(0, YSTEP * 2);
	path.rLineTo(XSTEP, 0);
	path.rLineTo(0, -YSTEP * 2);
	path.rLineTo(XSTEP, 0);
	path.rLineTo(0, YSTEP * 2);
	path.rLineTo(XSTEP, 0);
	path.rLineTo(0, -YSTEP * 2);
	path.rLineTo(XSTEP, 0);
	path.rLineTo(0, YSTEP * 2);
	path.rLineTo(XSTEP, 0);
	path.rLineTo(0, -YSTEP);

	// Sawtooth
	path.moveTo(-ICON_WIDTH, ICON_WIDTH - (STEP * 3) / 2);
	path.rLineTo(XSTEP, -YSTEP);
	path.rLineTo(0, YSTEP * 2);
	path.rLineTo(XSTEP * 2, -YSTEP * 2);
	path.rLineTo(0, YSTEP * 2);
	path.rLineTo(XSTEP * 2, -YSTEP * 2);
	path.rLineTo(0, YSTEP * 2);
	path.rLineTo(XSTEP * 2, -YSTEP * 2);
	path.rLineTo(0, YSTEP * 2);
	path.rLineTo(XSTEP, -YSTEP);

	paint.setShader(green);
	paint.setStrokeCap(Paint.Cap.ROUND);
	canvas.drawPath(path, paint);
    }

    protected void drawStrobeIcon(Canvas canvas)
    {
	paint.setStyle(Paint.Style.FILL);
	paint.setAntiAlias(true);

	paint.setShader(olive);
	canvas.drawRoundRect(rect, STEP, STEP, paint);

	canvas.clipRect(topRight);
	paint.setShader(aqua);
	canvas.drawRoundRect(rect, STEP, STEP, paint);
	canvas.clipRect(bottomLeft, Region.Op.REPLACE);
	canvas.drawRoundRect(rect, STEP, STEP, paint);
    }

    protected void drawTunerIcon(Canvas canvas)
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
