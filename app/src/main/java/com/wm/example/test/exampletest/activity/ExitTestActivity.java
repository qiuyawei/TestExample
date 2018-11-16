package com.wm.example.test.exampletest.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Person;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.base.BaseActivity;
import com.wm.example.test.exampletest.bean.Book;
import com.wm.example.test.exampletest.bean.GoodsBean;
import com.wm.example.test.exampletest.fragment.BlankFragment1;
import com.wm.example.test.exampletest.fragment.BlankFragment2;
import com.wm.example.test.exampletest.observable.BoxTest;
import com.wm.example.test.exampletest.service.MyService;
import com.wm.example.test.exampletest.utils.CallBack;
import com.wm.example.test.exampletest.utils.LogUtils;
import com.wm.example.test.exampletest.utils.MyThreadPool;
import com.wm.example.test.exampletest.view.BitmapShaderView;
import com.wm.example.test.exampletest.view.CircleImageView;
import com.wm.example.test.exampletest.view.ProcessView;
import com.wm.example.test.lib.ThreadPoolProxy;
import com.wm.example.test.lib.ThreadPoolProxyFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class ExitTestActivity extends BaseActivity implements BlankFragment1.OnFragmentInteractionListener,BlankFragment2.OnFragmentInteractionListener{
    @BindView(R.id.viewPage)
    ViewPager viewPager;
    @BindView(R.id.tbLayout)
    TabLayout tabLayout;

    ArrayList<Fragment> fragments=new ArrayList<>();
    String[]names={"A","B"};
    public static void lanuchActivity(Activity activity){
        Intent intent=new Intent(activity,ExitTestActivity.class);
        activity.startActivity(intent);
    }
    @Override
    public int innitLayout() {
        return R.layout.activity_exit_test;
    }

    @Override
    public void innitData() {
//        这是新的提交，并且本地分支是AJAX 远程分支second
        fragments.add(new BlankFragment1());
        fragments.add(new BlankFragment2());
        for(int i=0;i<fragments.size();i++){
            TabLayout.Tab tab=tabLayout.newTab();
//            tab.setText(names[i]);
//            tab.setContentDescription(names[i]);

            tabLayout.addTab(tab);
        }
        tabLayout.setSelectedTabIndicatorColor(Color.GREEN);
        tabLayout.setTabTextColors(Color.BLACK,Color.RED);
//        tabLayout.setBackgroundColor(Color.GRAY);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setSelectedTabIndicatorGravity(TabLayout.INDICATOR_GRAVITY_CENTER);
        tabLayout.setTabIndicatorFullWidth(false);
//        tabLayout.setSelectedTabIndicatorColor(Color.RED);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                tabLayout.getTabAt(i).setText(names[i]);

                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

        });
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
