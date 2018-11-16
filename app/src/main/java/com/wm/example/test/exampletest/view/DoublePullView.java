package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Author:qyw
 * on 2018/11/1.
 * QQ:448739075
 * 描述：
 */
public class DoublePullView extends LinearLayout {
    private View mOnlyChildView;
    private int mPWidth, mPhight;

    public DoublePullView(Context context) {
        super(context);
    }

    public DoublePullView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DoublePullView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DoublePullView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mOnlyChildView = getChildAt(0);
        mPWidth = getMeasuredWidth();
        mPhight = getMeasuredHeight();
        measureChild(mOnlyChildView, widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        mOnlyChildView.layout(0, 0, mPWidth, mPhight * 2);
    }

    private int dowY, moveDistance, endY, totalDistance;
    private boolean isTop = true, isBottom = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dowY = (int) getY();
                totalDistance=0;
                moveDistance=0;
                break;
            case MotionEvent.ACTION_MOVE:
                endY = (int) getY();
                moveDistance = endY - dowY;
                totalDistance = moveDistance + totalDistance;
                startMove();
                dowY = endY;
                break;
            case MotionEvent.ACTION_UP:
                if (Math.abs(totalDistance) >= getMeasuredHeight() / 2) {
                    if (moveDistance < 0) {
                        //往上拉
                        if(isTop){
                            moveToBottom();
                        }else {
                           reset();
                        }
                    } else if(moveDistance>0){
                        if(isBottom){
                            moveToTop();
                        }else {
                           reset();
                        }
                    }
                } else {
                    reset();
                }
                break;
        }
        return true;
    }

    private void startMove() {
        mOnlyChildView.layout(0, totalDistance, getMeasuredWidth(), totalDistance + getMeasuredHeight() * 2);
    }

    private void moveToTop() {
        mOnlyChildView.layout(0, 0, mPWidth, mPhight * 2);
        isTop = true;
        isBottom = false;
    }

    private void moveToBottom() {
        mOnlyChildView.layout(0, -mPhight, getMeasuredWidth(), mPhight);
        isTop = false;
        isBottom = true;
    }


    private void reset(){
        if(isTop){
            moveToTop();
        }else {
            moveToBottom();
        }
    }
}
