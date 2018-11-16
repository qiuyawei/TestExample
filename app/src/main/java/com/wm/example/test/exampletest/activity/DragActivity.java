package com.wm.example.test.exampletest.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ProgressBar;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.base.BaseActivity;
import com.wm.example.test.exampletest.bean.Book;
import com.wm.example.test.exampletest.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Author:qyw
 * on 2018/10/26.
 * QQ:448739075
 * 描述：
 */
public class DragActivity extends BaseActivity {

    public static void lanuchActivity(Activity activity, ArrayList<Book> books){
        Intent intent=new Intent(activity,DragActivity.class);
        intent.putParcelableArrayListExtra("book",books);
        activity.startActivity(intent);
    }
    @Override
    public int innitLayout() {
        return R.layout.activity_drag_test;
    }

    @Override
    public void innitData() {
       ArrayList<Book> books=getIntent().getParcelableArrayListExtra("book");
       if(books==null){
           LogUtils.i("booke==null");
       }else {
          for(Book book:books){
              LogUtils.i("name:"+book.getName());
          }
       }
    }

}
