package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wm.example.test.exampletest.R;

/**
 * Created by Author:qyw
 * on 2018/10/26.
 * QQ:448739075
 * 描述：自定义初级控件 TextView
 */
public class CustomView extends View {
    private int mTextColor;
    private int mTextSize;
    private Paint mPaint;
    private String mText;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context,  AttributeSet attrs) {
        super(context, attrs);
        innitData(context,attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innitData(context,attrs);

    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        innitData(context,attrs);

    }

    private void innitData(Context context,AttributeSet attrs){
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.textAttr);
        mTextColor=array.getColor(R.styleable.textAttr_textColor,context.getResources().getColor(R.color.colorAccent));
        mTextSize=array.getDimensionPixelSize(R.styleable.textAttr_textSize,(int)(context.getResources().getDimension(R.dimen.dp16)));
        mText=array.getString(R.styleable.textAttr_textContent);
        if(array!=null) array.recycle();
        mPaint=new Paint();
        mPaint.setColor(mTextColor);
        mPaint.setTextSize(mTextSize);
        mPaint.setAntiAlias(true);//防抖动，去矩尺
        Log.i("TAG","R.styleable.textAttr_textColor:"+R.styleable.textAttr_textColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //开始绘制
        canvas.drawText(mText,getWidth()/2,getHeight()/2,mPaint);
    }
}
