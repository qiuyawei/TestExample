package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.utils.DisplayUtil;
import com.wm.example.test.exampletest.utils.LogUtils;

/**
 * Created by Author:qyw
 * on 2018/10/29.
 * QQ:448739075
 * 描述：
 */
public class SingView extends View {
    public SingView(Context context) {
        super(context);
        innit();

    }

    public SingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        innit();

    }

    public SingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit();

    }

    public SingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        innit();
    }

    private Paint mPaint, circlePaint;
    private int mColor = Color.BLUE;
    private Rect mRect, strRect;
    private String content = "图片12345";

    private void innit() {
        mPaint = new Paint();
        mPaint.setColor(mColor);
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        mRect = new Rect(10, 10, DisplayUtil.getDisplayWidth() - 20, DisplayUtil.getDisplayWidth() + 10);
        strRect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        innit();
        canvas.drawRect(mRect, mPaint);
//        canvas.drawOval(new RectF(mRect),circlePaint);
//        canvas.drawArc(new RectF(mRect),0,45,true,circlePaint);
//        mPaint.setColor(Color.GREEN);
//        canvas.drawArc(new RectF(mRect),45,45,true,mPaint);
//        mPaint.setColor(Color.YELLOW);
//        canvas.drawArc(new RectF(mRect),90,45,true,mPaint);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawArc(new RectF(mRect),135,45,true,mPaint);
        circlePaint.setTextSize(DisplayUtil.dip2px(12));//字体大小12dp

        circlePaint.getTextBounds(content, 0, 1, strRect);
        LogUtils.i("width:" + strRect.width());
        LogUtils.i("heigth:" + strRect.height());
        float postionX=DisplayUtil.getDisplayWidth()/2-(strRect.width())*content.length()/2;
        float postionY=(DisplayUtil.getDisplayWidth()-20)/2;


        canvas.drawText(content,postionX,postionY,circlePaint);
        int width=DisplayUtil.getDisplayWidth();
        int height=DisplayUtil.getDisplayHeight();
        canvas.drawLine(width/2,0,width/2,height,circlePaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.i("event:"+event.getAction());
        return super.onTouchEvent(event);
    }
}
