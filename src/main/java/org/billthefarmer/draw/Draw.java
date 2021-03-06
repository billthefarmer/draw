package org.billthefarmer.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class Draw extends View
{
    public static final int STEP = 64;
    public static final int XSTEP = 56;
    public static final int YSTEP = 48;
    public static final int ICON_WIDTH = 224;

    public static final int colours[] =
    {
        Color.MAGENTA, Color.BLUE, Color.CYAN,
        Color.GREEN, Color.YELLOW, Color.RED
    };

    public static int icon;

    private int width;
    private int height;

    private Paint paint;
    private Path path;

    private RectF rect;
    private RectF topLeft;
    private RectF topRight;
    private RectF bottomLeft;
    private RectF bottomRight;

    private LinearGradient black;
    private LinearGradient white;
    private LinearGradient darkgreen;
    private LinearGradient green;
    private LinearGradient yellow;
    private LinearGradient blue;
    private LinearGradient cyan;
    private LinearGradient magenta;
    private LinearGradient olive;
    private LinearGradient aqua;
    private LinearGradient spectrum;
    private LinearGradient histogram;

    private RadialGradient redOrange;
    private RadialGradient orangeYellow;
    private RadialGradient yellowGreen;
    private RadialGradient greenBlue;

    public Draw(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // Create paint

        paint = new Paint();
        path = new Path();

        rect = new RectF(-ICON_WIDTH, -ICON_WIDTH, ICON_WIDTH, ICON_WIDTH);

        topLeft = new RectF(-ICON_WIDTH, -ICON_WIDTH, 0, 0);
        topRight = new RectF(0, -ICON_WIDTH, ICON_WIDTH, 0);
        bottomLeft = new RectF(-ICON_WIDTH, 0, 0, ICON_WIDTH);
        bottomRight = new RectF(0, 0, ICON_WIDTH, ICON_WIDTH);

        black = new LinearGradient(0, 0, 0, -Main.WIDTH,
                                   Color.BLACK,
                                   Color.WHITE,
                                   Shader.TileMode.CLAMP);
        white = new LinearGradient(0, 0, 0, -Main.WIDTH,
                                   Color.rgb(192, 192, 192),
                                   Color.WHITE,
                                   Shader.TileMode.CLAMP);
        darkgreen = new LinearGradient(0, 0, 0, -Main.WIDTH,
                                       Color.rgb(0, 64, 0),
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

        spectrum = new LinearGradient(ICON_WIDTH, ICON_WIDTH,
                                      -ICON_WIDTH, -ICON_WIDTH,
                                      colours, null,
                                      Shader.TileMode.CLAMP);

        redOrange = new RadialGradient(0, 0, ICON_WIDTH,
                                       Color.RED, Color.rgb(255, 127, 0),
                                       Shader.TileMode.CLAMP);
        orangeYellow = new RadialGradient(0, 0, ICON_WIDTH,
                                          Color.rgb(255, 127, 0), Color.YELLOW,
                                          Shader.TileMode.CLAMP);
        yellowGreen = new RadialGradient(0, 0, ICON_WIDTH * 2,
                                         Color.YELLOW, Color.GREEN,
                                         Shader.TileMode.CLAMP);
        greenBlue = new RadialGradient(0, 0, ICON_WIDTH * 3,
                                       Color.GREEN, Color.BLUE,
                                       Shader.TileMode.CLAMP);

        histogram = new LinearGradient(ICON_WIDTH, 0,
                                       -ICON_WIDTH, 0,
                                       colours, null,
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

        paint.setShader(null);
        paint.setTextAlign(Paint.Align.LEFT);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(0);
        canvas.drawLine(-width / 2, -Main.WIDTH / 2,
                        width / 2, -Main.WIDTH / 2, paint);
        canvas.drawLine(-width / 2, Main.WIDTH / 2,
                        width / 2, Main.WIDTH / 2, paint);
        canvas.drawLine(-Main.WIDTH / 2, -height / 2,
                        -Main.WIDTH / 2, height / 2, paint);
        canvas.drawLine(Main.WIDTH / 2, -height / 2,
                        Main.WIDTH / 2, height / 2, paint);
     }

    protected void drawIcon(Canvas canvas)
    {
        switch (icon)
        {
        case R.id.sudoku:
            drawSudokuIcon(canvas);
            break;

        case R.id.histogram:
            drawHistogramIcon(canvas);
            break;

        case R.id.pollen:
            drawPollenIcon(canvas);
            break;

        case R.id.rainbow:
            drawRainbowIcon(canvas);
            break;

        case R.id.crossword:
            drawCrosswordIcon(canvas);
            break;

        case R.id.scope:
            drawScopeIcon(canvas);
            break;

        case R.id.siggen:
            drawSigGenIcon(canvas);
            break;

        case R.id.strobe:
            drawStrobeIcon(canvas);
            break;

        case R.id.tuner:
            drawTunerIcon(canvas);
            break;

        case R.id.tdr:
            drawTDRIcon(canvas);
            break;
        }
    }

    protected void drawSudokuIcon(Canvas canvas)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setShader(null);
        paint.setColor(Color.WHITE);
        canvas.drawRoundRect(rect, STEP, STEP, paint);;

        paint.setShader(black);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(16);
        canvas.drawRoundRect(rect, STEP, STEP, paint);

        canvas.drawLine(-ICON_WIDTH / 3, -ICON_WIDTH, -ICON_WIDTH / 3,
                        ICON_WIDTH, paint);
        canvas.drawLine(ICON_WIDTH / 3, -ICON_WIDTH, ICON_WIDTH / 3,
                        ICON_WIDTH, paint);
        canvas.drawLine(-ICON_WIDTH, -ICON_WIDTH / 3, ICON_WIDTH,
                        -ICON_WIDTH / 3, paint);
        canvas.drawLine(-ICON_WIDTH, ICON_WIDTH / 3, ICON_WIDTH,
                        ICON_WIDTH / 3, paint);

        paint.setTextSize(ICON_WIDTH / 2);
        paint.setStrokeWidth(8);
        paint.setShader(null);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("3", -ICON_WIDTH / 8, -ICON_WIDTH / 2, paint);
        canvas.drawText("6", -ICON_WIDTH / 8, ICON_WIDTH * 3 / 16, paint);
        canvas.drawText("5", ICON_WIDTH / 2, ICON_WIDTH * 3 / 16, paint);
        canvas.drawText("2", -ICON_WIDTH * 13 / 16, ICON_WIDTH * 13 / 16,
                        paint);
    }

    protected void drawHistogramIcon(Canvas canvas)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        paint.setShader(black);
        canvas.drawRoundRect(rect, STEP, STEP, paint);

        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setShader(darkgreen);
        paint.setStrokeWidth(8);

        for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
            canvas.drawLine(i, -ICON_WIDTH, i, ICON_WIDTH, paint);

        for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
            canvas.drawLine(-ICON_WIDTH, i, ICON_WIDTH, i, paint);

        path.rewind();
        path.moveTo(ICON_WIDTH, ICON_WIDTH - STEP);
        path.lineTo(-ICON_WIDTH, ICON_WIDTH - STEP);

        float a = STEP * 6;
        float b = 0;
        float c = 112;

        for (int x = -ICON_WIDTH; x <= ICON_WIDTH; x++)
        {
            float y = ICON_WIDTH - STEP - a *
                      (float) Math.exp(-((x - b) * (x - b)) / (2 * c * c));
            path.lineTo(x, y);
        }

        paint.setShader(histogram);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(8);
        canvas.drawPath(path, paint);
    }

    protected void drawPollenIcon(Canvas canvas)
    {
        paint.setAntiAlias(true);
        paint.setShader(greenBlue);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(rect, STEP, STEP, paint);

        paint.setShader(null);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTypeface(Typeface.create((String) null, Typeface.BOLD));
        paint.setTextSize(ICON_WIDTH);
        canvas.drawText("L", 0, 96, paint);
    }

    protected void drawRainbowIcon(Canvas canvas)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setShader(spectrum);
        canvas.drawRoundRect(rect, STEP, STEP, paint);
    }

    protected void drawCrosswordIcon(Canvas canvas)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setShader(null);
        paint.setColor(Color.WHITE);
        canvas.drawRoundRect(rect, STEP, STEP, paint);

        paint.setShader(black);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(16);
        canvas.drawRoundRect(rect, STEP, STEP, paint);

        canvas.drawLine(-ICON_WIDTH / 3, -ICON_WIDTH - 8, -ICON_WIDTH / 3,
                        ICON_WIDTH + 8, paint);
        canvas.drawLine(ICON_WIDTH / 3, -ICON_WIDTH - 8, ICON_WIDTH / 3,
                        ICON_WIDTH + 8, paint);
        canvas.drawLine(-ICON_WIDTH - 8, -ICON_WIDTH / 3, ICON_WIDTH + 8,
                        -ICON_WIDTH / 3, paint);
        canvas.drawLine(-ICON_WIDTH - 8, ICON_WIDTH / 3, ICON_WIDTH + 8,
                        ICON_WIDTH / 3, paint);

        paint.setTextSize(ICON_WIDTH / 2);
        paint.setStrokeWidth(8);
        paint.setShader(null);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawText("1", -ICON_WIDTH * 7 / 8, -ICON_WIDTH / 2,
                        paint);
        canvas.drawText("2", ICON_WIDTH * 7 / 16, -ICON_WIDTH / 2,
                        paint);
        canvas.drawText("3", -ICON_WIDTH * 7 / 8, ICON_WIDTH * 13 / 16,
                        paint);

        paint.setShader(black);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(-ICON_WIDTH / 3, -ICON_WIDTH / 3,
                        ICON_WIDTH / 3, ICON_WIDTH / 3, paint);
    }

    protected void drawScopeIcon(Canvas canvas)
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
        path.moveTo(-ICON_WIDTH - 8, 16);
        for (int x = -ICON_WIDTH - 8; x <= ICON_WIDTH + 8; x++)
        {
            float y = (float)
                      Math.sin(x * 1 * Math.PI / ICON_WIDTH) * YSTEP * 3;
            path.lineTo(x, y);
        }

        paint.setShader(green);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPath(path, paint);
    }

    protected void drawSigGenIcon(Canvas canvas)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        paint.setShader(black);
        canvas.drawRoundRect(rect, STEP, STEP, paint);

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
        path.moveTo(-ICON_WIDTH - 8, -ICON_WIDTH + (STEP * 3) / 2 + 12);
        for (int x = -ICON_WIDTH - 8; x <= ICON_WIDTH + 8; x++)
        {
            float y = -ICON_WIDTH + (STEP * 3) / 2 - (float)
                      Math.sin(x * 2 * Math.PI / ICON_WIDTH) * YSTEP;
            path.lineTo(x, y);
        }

        // Square
        path.moveTo(-ICON_WIDTH - 8, 0);
        path.rLineTo(0, -YSTEP);
        path.rLineTo(XSTEP + 2, 0);
        path.rLineTo(0, YSTEP * 2);
        path.rLineTo(XSTEP + 2, 0);
        path.rLineTo(0, -YSTEP * 2);
        path.rLineTo(XSTEP + 2, 0);
        path.rLineTo(0, YSTEP * 2);
        path.rLineTo(XSTEP + 2, 0);
        path.rLineTo(0, -YSTEP * 2);
        path.rLineTo(XSTEP + 2, 0);
        path.rLineTo(0, YSTEP * 2);
        path.rLineTo(XSTEP + 2, 0);
        path.rLineTo(0, -YSTEP * 2);
        path.rLineTo(XSTEP + 2, 0);
        path.rLineTo(0, YSTEP * 2);
        path.rLineTo(XSTEP + 2, 0);
        path.rLineTo(0, -YSTEP);

        // Sawtooth
        path.moveTo(-ICON_WIDTH - 8, ICON_WIDTH - (STEP * 3) / 2);
        path.rLineTo(XSTEP + 2, -YSTEP);
        path.rLineTo(0, YSTEP * 2);
        path.rLineTo(XSTEP * 2 + 4, -YSTEP * 2);
        path.rLineTo(0, YSTEP * 2);
        path.rLineTo(XSTEP * 2 + 4, -YSTEP * 2);
        path.rLineTo(0, YSTEP * 2);
        path.rLineTo(XSTEP * 2 + 4, -YSTEP * 2);
        path.rLineTo(0, YSTEP * 2);
        path.rLineTo(XSTEP + 2, -YSTEP);

        paint.setShader(green);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPath(path, paint);
    }

    @SuppressWarnings("deprecation")
    protected void drawStrobeIcon(Canvas canvas)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        paint.setShader(olive);
        canvas.drawRoundRect(rect, STEP, STEP, paint);

        canvas.save();
        canvas.save();
        canvas.clipRect(topRight);
        paint.setShader(aqua);
        canvas.drawRoundRect(rect, STEP, STEP, paint);
        canvas.restore();
        canvas.clipRect(bottomLeft);
        canvas.drawRoundRect(rect, STEP, STEP, paint);
        canvas.restore();
    }

    protected void drawTunerIcon(Canvas canvas)
    {
        paint.setShader(black);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
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
        path.moveTo(-ICON_WIDTH - 8, ICON_WIDTH - STEP);

        float a = 320;
        float b = -STEP;
        float c = 32;

        for (int x = -ICON_WIDTH - 8; x <= ICON_WIDTH + 8; x++)
        {
            float y = ICON_WIDTH - STEP - a *
                      (float) Math.exp(-((x - b) * (x - b)) / (2 * c * c));
            path.lineTo(x, y);
        }

        paint.setShader(green);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPath(path, paint);

        paint.setShader(yellow);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawLine(-STEP, -ICON_WIDTH - 8, -STEP, ICON_WIDTH + 8, paint);
    }

    protected void drawTDRIcon(Canvas canvas)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        paint.setShader(black);
        canvas.drawRoundRect(rect, STEP, STEP, paint);

        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setShader(darkgreen);
        paint.setStrokeWidth(8);

        for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
            canvas.drawLine(i, -ICON_WIDTH, i, ICON_WIDTH, paint);

        for (int i = -(ICON_WIDTH - STEP); i <= ICON_WIDTH - STEP; i += STEP)
            canvas.drawLine(-ICON_WIDTH, i, ICON_WIDTH, i, paint);

        path.rewind();
        path.moveTo(-ICON_WIDTH, 0);

        float a = STEP * 3;
        float b = -ICON_WIDTH + STEP;
        float c = 8;

        for (int x = -ICON_WIDTH; x <= 0; x++)
        {
            float y = -a * (float) Math.exp(-((x - b) * (x - b)) / (2 * c * c));
            path.lineTo(x, y);
        }

        a = -STEP * 2;
        b = STEP * 2.5f;

        for (int x = 0; x <= ICON_WIDTH; x++)
        {
            float y = -a * (float) Math.exp(-((x - b) * (x - b)) / (2 * c * c));
            path.lineTo(x, y);
        } 

        paint.setShader(green);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        canvas.drawPath(path, paint);

        paint.setShader(yellow);
        canvas.drawLine(STEP * 2.5f, -ICON_WIDTH, STEP * 2.5f, ICON_WIDTH, paint);
    }
}
