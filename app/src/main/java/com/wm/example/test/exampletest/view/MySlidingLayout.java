package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.wm.example.test.exampletest.utils.LogUtils;

/**
 * Created by Author:qyw
 * on 2018/11/2.
 * QQ:448739075
 * 描述：
 */
public class MySlidingLayout extends LinearLayout {
    //一共有两个子View
    private View leftChildView, rightChildView;
    //屏幕的宽和高
    private int displayWidth, displayHeight, leftViewWidth, leftViewHeight;
    //记录点击的初始x坐标和抬起的x坐标
    private int downX = 0, upX = 0;
    private int moveDistance = 0, totalMoveDistance = 0;
    private boolean isRightVisable = true, isLeftVisable = false;//默认显示右边的
    private int edge;//默认边界值


    public MySlidingLayout(Context context) {
        super(context);
        innit();
    }

    public MySlidingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        innit();

    }

    public MySlidingLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit();

    }

    public MySlidingLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        innit();

    }

    private void innit() {

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (leftChildView == null) {
            leftChildView = getChildAt(0);
            rightChildView = getChildAt(1);
            displayHeight = getMeasuredHeight();
            displayWidth = getMeasuredWidth();
            leftViewWidth = leftChildView.getMeasuredWidth();
            leftViewHeight = leftChildView.getMeasuredHeight();
            edge = displayWidth / 3;
        }
        measureChild(leftChildView, widthMeasureSpec, heightMeasureSpec);
        measureChild(rightChildView, widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //初始的时候隐藏左边布局
        leftChildView.layout(-leftViewWidth, 0, 0, displayHeight);
        rightChildView.layout(0, 0, displayWidth, displayHeight);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                moveDistance = 0;
                totalMoveDistance = 0;
                break;
            case MotionEvent.ACTION_MOVE:
                upX = (int) event.getX();
                moveDistance = upX - downX;
                totalMoveDistance += moveDistance;
                LogUtils.i("totalMoveDistance:" + totalMoveDistance);
                LogUtils.i("moveDistance:" + moveDistance);
                if (isLeftVisable) {
                    if (totalMoveDistance > 0) {
                    } else if (totalMoveDistance <= 0) {
                        move();
                    }
                } else if (isRightVisable) {
                    if (totalMoveDistance < 0) {

                    } else if (totalMoveDistance >= 0) {
                        move();
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                if (isRightVisable) {
                    if (totalMoveDistance > 0 && Math.abs(totalMoveDistance) > leftViewWidth / 3) {
                        showigLeft(true);
                    } else {
                        showRight(true);
                    }
                } else if (isLeftVisable) {
                    if (totalMoveDistance < 0 && Math.abs(totalMoveDistance) > leftViewWidth / 3) {
                        showRight(true);
                    } else {
                        showigLeft(true);
                    }
                }
                break;
        }

        return true;
    }

    //显示左边边View
    private void showigLeft(boolean isFast) {
        if (isFast) {
            leftChildView.layout(0, 0, leftViewWidth, leftViewHeight);
            rightChildView.layout(leftViewWidth, 0, leftViewWidth + displayWidth, displayHeight);
            isLeftVisable = true;
            isRightVisable = false;
        } else {
            new MyAsynTask().execute(leftViewWidth);
        }

    }


    //显示右边View
    private void showRight(boolean isFast) {
        if (isFast) {
            leftChildView.layout(-leftViewWidth, 0, 0, leftViewHeight);
            rightChildView.layout(0, 0, displayWidth, displayHeight);
            isRightVisable = true;
            isLeftVisable = false;
        } else {
            new MyAsynTask().execute(leftViewWidth);
        }

    }

    //开始滑动
    private void move() {
        LogUtils.i("move-distance:" + moveDistance);
        int leftChild_left = leftChildView.getLeft();
        int leftChild_Right = leftChildView.getRight();
        int rightChild_left = rightChildView.getLeft();
        int rightChild_Right = rightChildView.getRight();
        leftChildView.layout(leftChild_left + moveDistance, 0, leftChild_Right + moveDistance, leftViewHeight);
        rightChildView.layout(rightChild_left + moveDistance, 0, rightChild_Right + moveDistance, displayHeight);
        downX = upX;
    }

    public void changeView() {
        if (isRightVisable) {
            showigLeft(false);
        } else {
            showRight(false);
        }
    }

    private long temp = 0, ralV = 0;

    public class MyAsynTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            int value = integers[0].intValue();
            temp = value;
            if (isRightVisable) {
                for (int i = Math.abs(value); i >= 0; i--) {
                    publishProgress(i);
                    try {
                        if (value != 0)
                            //让时间逐渐增加 ,给用户一个缓冲时间
                            ralV = temp / value;
                        Thread.sleep(ralV);
                        temp--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                for (int i = 0; i <= Math.abs(value); i++) {
                    publishProgress(i);
                    try {
                        if (value != 0)
                            //让时间逐渐增加 ,给用户一个缓冲时间
                            ralV = temp / value;
                        Thread.sleep(ralV);
                        temp--;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            int i = values[0].intValue();
            LogUtils.i("onProgressUpdate:" + i);
            leftChildView.layout(-i, 0, leftViewWidth - i, leftViewHeight);
            rightChildView.layout(leftViewWidth - i, 0, leftViewWidth + displayWidth - i, displayHeight);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (isRightVisable) {
                isLeftVisable = true;
                isRightVisable = false;
            } else {
                isLeftVisable = false;
                isRightVisable = true;
            }
        }
    }


    private void sleep(){

    }
}
