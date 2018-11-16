package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.utils.LogUtils;

/**
 * Created by Author:qyw
 * on 2018/10/30.
 * QQ:448739075
 * 描述：
 */
public class CusView extends View {
    private Paint mPaint;
    public CusView(Context context) {
        super(context);
    }

    public CusView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CusView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private Bitmap bitmap;
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        if(mPaint==null) {
            mPaint = new Paint();
            mPaint.setColor(Color.RED);
            mPaint.setTextSize(20);
            bitmap=BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        canvas.drawBitmap(bitmap,10,10,mPaint);
//        canvas.save();

//        canvas.skew(30,30);

        canvas.drawRect(0,100,300,400,mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.scale(2,2);
        canvas.drawRect(0,100,300,400,mPaint);

//        canvas.restore();
    }
}
