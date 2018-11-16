package com.wm.example.test.exampletest.activity;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.GridView;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.adapter.ImageAdapter;
import com.wm.example.test.exampletest.base.BaseActivity;
import com.wm.example.test.exampletest.utils.Folder;
import com.wm.example.test.exampletest.utils.Image;
import com.wm.example.test.exampletest.utils.LogUtils;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by Author:qyw
 * on 2018/11/7.
 * QQ:448739075
 * 描述：
 */
public class ImageActivity extends BaseActivity {
    @BindView(R.id.gridView)
    GridView gridView;
    ImageAdapter adapter;
    private ArrayList<String> data=new ArrayList<>();


    public static void lanuchActivity(Activity activity, ArrayList<String> folder){
        Intent intent=new Intent(activity,ImageActivity.class);
        intent.putExtra("data",folder);
        activity.startActivity(intent);
    }
    @Override
    public int innitLayout() {
        return R.layout.activity_image;
    }

    @Override
    public void innitData() {
        data=getIntent().getStringArrayListExtra("data");
        adapter=new ImageAdapter(getMyActivity(),data);
        gridView.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            LogUtils.i("back:");
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(TextUtils.isEmpty(dirPath)){
            dirPath=getFolderPaht(data.get(0));
        }
        LogUtils.i("dirPath:"+dirPath);
        getAllImagePath();
        adapter.notifyDataSetChanged();
    }

    private String dirPath="";
    private String getFolderPaht(String childPath){
        if(!TextUtils.isEmpty(childPath)){
           int index=childPath.lastIndexOf("/");
            dirPath=childPath.substring(0,index);
        }
        return dirPath;
    }

    private ArrayList<String> getAllImagePath(){
        File file=new File(dirPath);
        if(file.isDirectory()){
          File[]files=  file.listFiles();
          if(files.length>0){
              data.clear();
          }
          for(int i=0;i<files.length;i++){
              data.add(files[i].getPath());
          }
        }

        return data;
    }
}
