package ru.sberbank.learning.rates;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Dimension;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by DevCom on 01.05.2017.
 */

public class PaintingView extends View {

    Paint paint = new Paint();
    Canvas mCanva = new Canvas();
    int width =RatesActivity.dispWidth;
    int height =RatesActivity.dispHeight;
    float[] f = {
            40, 500, 100, 500,
            100, 500, 100, 500,
            100, 500, 100, 500,
            100, 500, 100, 500,
            100, 500, 100, 500};

    public PaintingView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        paint.setColor(Color.RED);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(10);
    }

    public PaintingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public PaintingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        if (f[2] >= 100 && f[2] <= 299) {
            f[2] += 2;
            f[3] += 1;
            if (f[2] >= 299) {
                float tmp1 = f[2];
                float tmp2 = f[3];
                f[4] = tmp1;
                f[5] = tmp2;
                f[6] = tmp1;
                f[7] = tmp2;
            }
        } else if (f[6] >= 300 && f[6] <= 499) {
            f[6] += 2;
            f[7] -= 4;
            if (f[6] >= 499) {
                float tmp1 = f[6];
                float tmp2 = f[7];
                f[8] = tmp1;
                f[9] = tmp2;
                f[10] = tmp1;
                f[11] = tmp2;
            }
        } else if (f[10] >= 500 && f[10] < 699) {
            f[10] += 2;
            f[11] += 8;
            if (f[10] >= 699) {
                float tmp1 = f[10];
                float tmp2 = f[11];
                f[12] = tmp1;
                f[13] = tmp2;
                f[14] = tmp1;
                f[15] = tmp2;
            }
        } else if (f[14] >= 700 && f[14] < 899) {
            f[14] += 2;
            f[15] -= 2;
        }
        drawi(canvas);
        invalidate();

    }

    public void drawi(Canvas canvas) {
        canvas.drawLines(f, paint);

    }

}