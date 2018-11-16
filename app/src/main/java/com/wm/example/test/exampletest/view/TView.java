package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.graphics.LightingColorFilter;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.wm.example.test.exampletest.utils.DisplayUtil;
import com.wm.example.test.exampletest.utils.LogUtils;

/**
 * Created by Author:qyw
 * on 2018/10/29.
 * QQ:448739075
 * 描述：
 */
public class TView extends LinearLayout {

    public TView(Context context) {
        super(context);
    }

    public TView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //测量ziview 宽度和高度
    View bottomView;
    View topView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.i("childCount:" + getChildCount());
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        maxPageDistance = mHeight / 3;//设置超过屏幕高度的1/3允许显示下个VIew
        topView = getChildAt(0);
        bottomView = getChildAt(1);


        measureChild(topView, widthMeasureSpec, heightMeasureSpec);
        measureChild(bottomView, widthMeasureSpec, heightMeasureSpec);


    }

    //摆放子View的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogUtils.i("onLayout-changed:"+changed);

//        LogUtils.i("topViewMessHeight:" + topView.getMeasuredHeight());
//        LogUtils.i("topViewMessHeight:" + DisplayUtil.px2dip(topView.getMeasuredHeight()));

        topView.layout(0, 0, mWidth, mHeight);
        bottomView.layout(0, mHeight, mWidth, mHeight*2);

    }

    int maxPageDistance = 0;//允许翻页的距离，就是滑动距离超过这个值就显示上一个或者下一个
    int mWidth, mHeight, scllHeight;
    int movedistance, starty, endY, hadMoved;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                starty = (int) event.getY();
                hadMoved = 0;
                movedistance = 0;
                LogUtils.i("startY:" + starty);
                break;
            case MotionEvent.ACTION_MOVE:
                endY = (int) event.getY();
                movedistance = endY - starty;
                //计算总的移动距离，用来判断用户最终是像上拉还是像下拉(正值上拉，负下拉)
                hadMoved = movedistance + hadMoved;

                if (isBottomIsShow && hadMoved <= 0) {
                    //如果底部布局已经出来，那么继续上拉，由于下面没有view了所以不再处理
                } else if (isTopViewIsShow && hadMoved >= 0) {
                    //如果顶部布局已经出来，那么继续下拉，由于上面没有view了所以不再处理
                } else {
                    //处于中间环节
                    startMove();
                    starty = endY;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (isTopViewIsShow) {
                    //如果是像下拉，那么没什么改变的，如果上拉
                    if (hadMoved < 0) {
                        //上拉并且上拉高度超过整个显示屏的高度的一半，那么抬起手时候显示底部View
                        if (Math.abs(hadMoved) > mHeight / 3) {
                            showBottomView();
                        } else {
                            showTopView();
                        }
                    } else {
                        showTopView();
                    }
                } else if (isBottomIsShow) {
                    //如果此时底部View可见，那么只有下拉有效
                    if (hadMoved > 0) {
                        //下拉并且下拉移动的总距离大于屏幕高度的一半，放手时候显示顶部View
                        if (Math.abs(hadMoved) > getMeasuredHeight() / 3) {
                            showTopView();
                        } else {
                            showBottomView();
                        }
                    } else {
                        showBottomView();
                    }
                }

                break;
        }
        LogUtils.i("value:"+super.onTouchEvent(event));
        return true;
    }

    public void startMove() {
        if (isBottomIsShow) {
            LogUtils.i("moveDistance:" + movedistance);
            //此时是向下拉，所以 hadMoved是正数
            int top = topView.getTop();
            int bottom = topView.getBottom();
            int bTop = bottomView.getTop();
            int bBottom = bottomView.getBottom();
            topView.layout(0, top + movedistance, mWidth, bottom + movedistance);
            bottomView.layout(0, bTop + movedistance, mWidth, bBottom + movedistance);
        } else if (isTopViewIsShow) {
            //此时是向上拉，所以 hadMoved是负数
            topView.layout(0, 0 + hadMoved, mWidth, mHeight + hadMoved);
            bottomView.layout(0, mHeight + hadMoved, mWidth, mHeight + mHeight + hadMoved);
        }

    }

    private boolean isBottomIsShow = false;
    private boolean isTopViewIsShow = true;

    private void showBottomView() {
        topView.layout(0, -getMeasuredHeight(), getMeasuredWidth(), 0);
        bottomView.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        isTopViewIsShow = false;
        isBottomIsShow = true;
    }

    private void showTopView() {
        topView.layout(0, 0, getMeasuredWidth(), getMeasuredHeight());
        bottomView.layout(0, getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight() * 2);
        isTopViewIsShow = true;
        isBottomIsShow = false;
    }
}
