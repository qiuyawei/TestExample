package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.View;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.utils.DisplayUtil;

/**
 * Created by Author:qyw
 * on 2018/11/12.
 * QQ:448739075
 * 描述：自定义进度条
 */
public class ProcessView extends View {
    private int mPercent;//当前进度 ，总进度是100
    private Paint mPaint;//画笔
    private int mClor=Color.RED,mBgColor;
    private int width=0;
    private String text;
    public ProcessView(Context context) {
        super(context);
        innit(context,null);
    }

    public ProcessView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        innit(context,attrs);

    }

    public ProcessView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit(context,attrs);

    }

    public ProcessView(Context context,  AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        innit(context,attrs);

    }

    private void innit(Context context,AttributeSet attributeSet){
        TypedArray array=context.obtainStyledAttributes(attributeSet,R.styleable.mProcess);
        if(array!=null){
            mPercent=array.getInt(R.styleable.mProcess_percent,50);
            mBgColor=array.getColor(R.styleable.mProcess_bg_color,Color.GRAY);
            mClor=array.getColor(R.styleable.mProcess_percent_color,Color.BLACK);
//            text=array.getString(R.styleable.mProcess_percent_text);

            array.recycle();
        }
        mPaint=new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=getWidth();

        mPaint.setColor(mBgColor);
        //画背景,长度是屏幕宽度-60，高度 90

        RectF rectF=new RectF(30,30,width-60,120);
        canvas.drawRoundRect(rectF,10,10,mPaint);
        //画进度
        mPaint.setColor(mClor);
        RectF rect1=new RectF(30,33,(int) (width*mPercent/100f-60),120-3);
        canvas.drawRoundRect(rect1,10,10,mPaint);
        mPaint.setColor(Color.GREEN);
        text=mPercent+"%";
        mPaint.setTextSize(DisplayUtil.dip2px(20));
        canvas.drawText(text,width/2-getTextWidht(mPaint,text),180,mPaint);


    }

    public void setmPercent(int percent){
        this.mPercent=percent;
        //改变进度重绘页面
        invalidate();
    }

    private int  getTextWidht(Paint paint,String text){
        Rect rect=new Rect();
        paint.getTextBounds(text,0,1,rect);
        return rect.width();
    }
}
