package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.graphics.Color;
import android.icu.util.Measure;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wm.example.test.exampletest.utils.DisplayUtil;
import com.wm.example.test.exampletest.utils.LogUtils;

/**
 * Created by Author:qyw
 * on 2018/10/26.
 * QQ:448739075
 * 描述：
 */
public class MyView extends ViewGroup {



    /*  @Override
      protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
          super.onMeasure(widthMeasureSpec, heightMeasureSpec);
          int widthMode = MeasureSpec.getMode(widthMeasureSpec);
          int widthSize = MeasureSpec.getSize(widthMeasureSpec);

          int heightMode = MeasureSpec.getMode(heightMeasureSpec);
          int heightSize = MeasureSpec.getSize(heightMeasureSpec);
          LogUtils.i("widthMode:"+widthMode);
          switch (widthMode) {
              case MeasureSpec.AT_MOST://恰好是wrapContent 的值
                  //子View尽可能的大
                  widthSize=200;
                  break;
              case MeasureSpec.EXACTLY:
                  //ziView有准确的zhi

                  break;

              case MeasureSpec.UNSPECIFIED:
                  //无法预测的

                  break;

          }

          switch (heightMode) {
              case MeasureSpec.AT_MOST://恰好是wrapContent 的值
                  //子View尽可能的大
                  heightSize=200;
                  break;
              case MeasureSpec.EXACTLY:
                  //ziView有准确的zhi

                  break;

              case MeasureSpec.UNSPECIFIED:
                  //无法预测的

                  break;

          }
          setMeasuredDimension(widthSize,heightSize);
      }*/
    private int horizontalSpace = 10;
    private int verticalSpace = 10;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int hadUsedHorizontal = 15;//水平已经使用的距离
        int hadUsedVertical = 15;//垂直已经使用的距离
        int width = getMeasuredWidth();//测量自己的宽度
        LogUtils.i("测量自己的宽度:"+width);
        LogUtils.i("测量自己的高度:"+DisplayUtil.px2dip(getMeasuredHeight()));
        LogUtils.i("测量自己的高度:"+DisplayUtil.px2dip(getHeight()));

//        int height = getMeasuredHeight();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            //判断是否已经超出宽度
            if (view.getMeasuredWidth() + hadUsedHorizontal > width) {
                //已经超出了宽度水平方向已经放不下了 另起一行摆放  换行重置水平间距
                hadUsedVertical = hadUsedVertical + view.getMeasuredHeight() + verticalSpace;
                hadUsedHorizontal = 15;
            }
            //摆放子view 位置
            view.layout(hadUsedHorizontal, hadUsedVertical, hadUsedHorizontal + view.getMeasuredWidth(), hadUsedVertical + view.getMeasuredHeight());
            hadUsedHorizontal = hadUsedHorizontal + horizontalSpace + view.getMeasuredWidth();
            LogUtils.i("i:"+i+"="+view.getMeasuredHeight());
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //这里是测量子view的宽和高 假如不去测量那么这些控件的高度都是0
        LogUtils.i("测量子VIew的宽和高，然后去放");
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
    }


}
