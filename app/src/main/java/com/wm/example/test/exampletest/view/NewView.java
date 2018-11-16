package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.wm.example.test.exampletest.utils.LogUtils;

/**
 * Created by Author:qyw
 * on 2018/10/29.
 * QQ:448739075
 * 描述：
 */
public class NewView extends ViewGroup {
    public NewView(Context context) {
        super(context);
    }

    public NewView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NewView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NewView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int hadUsedHor = 0;
        int hadUsedVtic = 0;
        int widthP = getMeasuredWidth();
        LogUtils.i("onLayout:"+hadUsedHor);

        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
//            if(view.getVisibility()!=View.GONE) {
            if (childView.getMeasuredWidth() + hadUsedHor > widthP) {
                LogUtils.i("i:"+i);
                //换行
                hadUsedVtic = hadUsedVtic + childView.getMeasuredHeight();
            }
            childView.layout(hadUsedHor,hadUsedVtic,hadUsedHor+childView.getMeasuredWidth(),hadUsedVtic+childView.getMeasuredHeight());
            hadUsedHor=hadUsedHor+childView.getMeasuredWidth();
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
    }
}
