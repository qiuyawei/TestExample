package com.wm.example.test.exampletest.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.base.BaseActivity;
import com.wm.example.test.exampletest.utils.CallBack;
import com.wm.example.test.exampletest.utils.DisplayUtil;
import com.wm.example.test.exampletest.utils.LogUtils;
import com.wm.example.test.exampletest.utils.TestA;
import com.wm.example.test.exampletest.view.MyView;

import butterknife.BindView;
import butterknife.OnClick;

public class IndexActivity extends BaseActivity implements CallBack {
    @BindView(R.id.myView)
    MyView myView;
    @BindView(R.id.bt_value)
    Button bt_value;
    @BindView(R.id.ll_p1)
    LinearLayout ll_p1;
    @BindView(R.id.ll_p2)
    LinearLayout ll_p2;
    TestA testA;
    String[] titls={"足球","乒乓球","橄榄球","田径运动","100米自由泳","足球","乒乓球"};
    public static void lanuchActivity(Activity activity){
        Intent intent=new Intent(activity,IndexActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public int innitLayout() {
        return R.layout.activity_index;
    }

    @Override
    public void innitData() {
        testA = new TestA(this);
//        LogUtils.i("getLeft:"+ll_p2.getLeft());
//        LogUtils.i("getRight:"+ll_p2.getRight());
//        LogUtils.i("getTop:"+ll_p2.getTop());
        LogUtils.i("ll_p2-height:"+DisplayUtil.px2dip(ll_p2.getMeasuredHeight()));
        LogUtils.i("ll_p2-height:"+DisplayUtil.px2dip(ll_p2.getHeight()));

        for(int i=0;i<titls.length;i++){
            final String  msg=titls[i];
            final TextView textView=new TextView(this);
            textView.setText(titls[i]);
            textView.setBackgroundResource(R.drawable.shape_rect_bg_gray);
//            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(DisplayUtil.dip2px(10),0,0,0);
            int pading=DisplayUtil.dip2px(5);
            textView.setPadding(pading,pading,pading,pading);
            myView.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    //默认情况下 安卓返回的尺寸都 是px
    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i("OnResumgetLeft:"+ll_p2.getLeft());
        LogUtils.i("getRight:"+DisplayUtil.px2dip(ll_p2.getRight()));
        LogUtils.i("getTop:"+DisplayUtil.px2dip(ll_p2.getTop()));
        LogUtils.i("getBottom:"+DisplayUtil.px2dip(ll_p2.getBottom()));
//        LogUtils.i("dpTopx:"+DisplayUtil.dip2px(400));
//        LogUtils.i("pxTodp:"+DisplayUtil.px2dip(400));

    }

    @Override
    public void getValue(int value,long sleep) {
        Toast.makeText(this,String.valueOf(value),Toast.LENGTH_LONG).show();
    }
}
