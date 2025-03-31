package ru.stankin.labs.sem12;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Random;

public class TargetView extends View {

    private int numCircles = 5;
    private Paint paint;
    private int[] circleColors;
    private Random random;


    public TargetView(Context context) {
        super(context);
        init();
    }

    public TargetView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TargetView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        random = new Random();
        initializeColors();
    }

    private void initializeColors() {
        circleColors = new int[numCircles];
        //int RandomColor = getRandomColor();
        for (int i = 0; i < numCircles; i++) {
            circleColors[i] = (i % 2 != 0) ? 0xFF000000 : 0xFFFF0000;
        }
    }

    private int getRandomColor() {
        return 0xFF000000 | random.nextInt(0xFFFFFF);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int size = Math.min(width, height);
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

        int minDimension = Math.min(width, height);
        int radiusStep = minDimension / (2 * numCircles);

        for (int i = 0; i < numCircles; i++) {
            int radius = (numCircles - i) * radiusStep;
            paint.setColor(circleColors[i]);
            canvas.drawCircle(width / 2f, height / 2f, radius, paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            changeOddCirclesColor();
            return true;
        }
        return super.onTouchEvent(event);
    }

    private void changeOddCirclesColor() {
        int RandomColor = getRandomColor();
        for (int i = 0; i < numCircles; i++) {
            if (i % 2 == 0) {
                circleColors[i] = RandomColor;
            }
        }
        invalidate();
    }

    public void setNumCircles(int numCircles) {
        this.numCircles = numCircles;
        initializeColors();
        invalidate();
    }
}

