package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.wm.example.test.exampletest.utils.DisplayUtil;
import com.wm.example.test.exampletest.utils.LogUtils;

/**
 * Created by Author:qyw
 * on 2018/10/29.
 * QQ:448739075
 * 描述：
 */
public class ArrowDirection extends View {
    private Paint mPaint,paint;
    private int radius=90;
    public ArrowDirection(Context context) {
        this(context,null);
    }

    public ArrowDirection(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }
    public ArrowDirection(Context context,  AttributeSet attrs,int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        mPaint=new Paint();
        mPaint.setColor(Color.BLACK);
        paint=new Paint();
        paint.setColor(Color.YELLOW);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int height=getHeight();
        mPaint.setColor(Color.BLACK);
        canvas.drawArc(0,0,width,height,0,360,true,mPaint);
        canvas.drawArc(0,0,width,height,0,radius,true,paint);
        if(radius>0){
            String text=String.valueOf(radius*100f/360f).substring(0,2)+"%";
            mPaint.setColor(Color.RED);
            mPaint.setTextSize(DisplayUtil.dip2px(20));
            canvas.drawText(text,0,text.length(),width/2,height/2,mPaint);
        }
    }

    public void setRadius(int radius){
        this.radius=radius;
        invalidate();
        LogUtils.i("radius:"+radius);
    }
}
