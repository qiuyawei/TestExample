package com.wm.example.test.exampletest.activity;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.base.BaseActivity;


public class SplashActivity extends BaseActivity {

    @Override
    public int innitLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void innitData() {
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ExitTestActivity.lanuchActivity(getMyActivity());
                finish();
            }
        }, 2000);
    }
}
