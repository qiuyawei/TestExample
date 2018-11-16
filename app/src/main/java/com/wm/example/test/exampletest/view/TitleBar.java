package com.wm.example.test.exampletest.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.utils.LogUtils;

/**
 * Created by Author:qyw
 * on 2018/10/26.
 * QQ:448739075
 * 组合自定义
 */
public class TitleBar extends LinearLayout {
    private RelativeLayout mRelativeLayout;
    private ImageView mLeftImage;
    private TextView mTitle;


    public TitleBar(Context context) {
        super(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        innit(context,attrs);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        innit(context,attrs);
    }




    private void innit(final Context context, AttributeSet attrs){
        //把当前布局添加到这个里面
        LayoutInflater.from(context).inflate(R.layout.title_bar,this);
        mLeftImage=findViewById(R.id.iv_back);
        mTitle=findViewById(R.id.tv_title);
        mLeftImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)context).finish();
            }
        });
        TypedArray array=context.obtainStyledAttributes(attrs,R.styleable.textAttr);
        String title=array.getString(R.styleable.textAttr_textContent);
        mTitle.setText(title);
        if(array!=null){
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LogUtils.i("onMeasure:");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtils.i("onLayout:");

    }
}

