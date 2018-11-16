package com.wm.example.test.exampletest.base;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.utils.MActivityMannage;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(innitLayout());
        activity = this;
        ButterKnife.bind(activity);
        innitData();
        MActivityMannage.getInstance().addActivity(activity);
    }


    /**
     * 初始化布局
     */
    public abstract int innitLayout();

    /**
     * 初始化基础数据
     */
    public abstract void innitData();

    /**
     * 获取activity对象
     *
     * @return
     */
    public Activity getMyActivity() {
        return activity;
    }

    /**
     * 设置点击返回事件
     */
    public void colseActivity() {
        ImageView iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

    }

    /**
     * 设置标题
     *
     * @param activityTitle
     */
    public void setTitle(String activityTitle) {
        TextView tv_title = findViewById(R.id.tv_title);
        tv_title.setText(activityTitle);

    }
}
