package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Author:qyw
 * on 2018/11/1.
 * QQ:448739075
 * 描述：
 */
public class MyScllView extends ScrollView {
    public MyScllView(Context context) {
        super(context);
    }

    public MyScllView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScllView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScllView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        for(int i=0;i<getChildCount();i++){
            View chidView=getChildAt(i);
            measureChild(chidView,widthMeasureSpec,heightMeasureSpec);
        }
    }

    int hadUsedHor=0;
    int hadUsedVtic=0;
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for(int i=0;i<getChildCount();i++){
            View chidView=getChildAt(i);
            if(chidView.getMeasuredWidth()+hadUsedHor>getMeasuredWidth()){
                hadUsedVtic=chidView.getMeasuredHeight();
                hadUsedHor=0;
            }
            chidView.layout(hadUsedHor,hadUsedVtic,hadUsedHor+chidView.getMeasuredWidth(),hadUsedVtic+chidView.getMeasuredHeight());
            hadUsedHor=hadUsedHor+chidView.getMeasuredWidth();
        }
    }
}
