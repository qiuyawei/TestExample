package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
public class DotTouchWithHand extends View {
    private int mRadius = 10;
    private int mPaintColor = Color.BLUE;
    private Paint mPint,paint;

    public DotTouchWithHand(Context context) {
        super(context);
    }

    public DotTouchWithHand(Context context, AttributeSet attrs) {
        super(context, attrs);
        innit(context, attrs);
    }

    public DotTouchWithHand(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit(context, attrs);

    }

    public DotTouchWithHand(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        innit(context, attrs);

    }


    private void innit(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.circleAtrr);
        mRadius =(int) array.getDimension(R.styleable.circleAtrr_circle_radius,50);
        mPaintColor = array.getColor(R.styleable.circleAtrr_circle_color, Color.RED);
        if (array != null) {
            array.recycle();
        }
        mPint = new Paint();
        mPint.setAntiAlias(true);
        mPint.setColor(mPaintColor);
        mPint.setDither(true);
        paint=new Paint();
        paint.setColor(Color.RED);
        LogUtils.i("mRadius:" + mRadius);
        LogUtils.i("mRadius2:" + DisplayUtil.px2dip(mRadius));
    }

    private float currentX, currentY;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(currentX==0){
            currentX = getWidth() / 2;
            currentY = getHeight() / 2;
        }
        canvas.drawCircle(currentX, currentY, mRadius, mPint);
        canvas.drawCircle(currentX,currentY,12,paint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX=event.getX();
        currentY=event.getY();
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
