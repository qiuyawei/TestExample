package com.wm.example.test.exampletest.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.adapter.ListAdapter;
import com.wm.example.test.exampletest.base.BaseActivity;
import com.wm.example.test.exampletest.utils.Folder;
import com.wm.example.test.exampletest.utils.Image;
import com.wm.example.test.exampletest.utils.ImageConfig;
import com.wm.example.test.exampletest.utils.LogUtils;
import com.wm.example.test.exampletest.utils.PickImageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AlbumActivity extends BaseActivity {
    ImageConfig imageConfig;
    @BindView(R.id.gridView)
    GridView gridView;
    ListAdapter adapter;


    public static void lanuchActivity(Activity activity){
        Intent intent=new Intent(activity,AlbumActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public int innitLayout() {
        return R.layout.activity_album;
    }

    @Override
    public void innitData() {
        imageConfig=new ImageConfig();
        // 首次加载所有图片
        getSupportLoaderManager().initLoader(LOADER_ALL, null, mLoaderCallback);
        adapter=new ListAdapter(this,mResultFolder);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> images=new ArrayList<>();
                for(int i=0;i<mResultFolder.get(position).images.size();i++){
                    images.add(mResultFolder.get(position).images.get(i).path);
                }
                ImageActivity.lanuchActivity(getMyActivity(),images);
            }
        });
    }


    private String mPermison=Manifest.permission.READ_EXTERNAL_STORAGE;
    private static final int REQUEST_CODE=100;
    private void selectPic() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            int per = checkSelfPermission(mPermison);
            if(per!=PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[]{mPermison},REQUEST_CODE);
            }else {
                goNext();
            }
        }else {
            goNext();
        }
    }
    private static final int PICTURE_REQUEST=1000;
    private void goNext(){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,PICTURE_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_CODE:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    goNext();
                }
                break;
        }
    }



    // 文件夹数据
    private ArrayList<Folder> mResultFolder = new ArrayList<Folder>();
    private boolean hasFolderGened = false;
    // 不同loader定义
    private static final int LOADER_ALL = 0;
    private static final int LOADER_CATEGORY = 1;
    private LoaderManager.LoaderCallbacks<Cursor> mLoaderCallback = new LoaderManager.LoaderCallbacks<Cursor>() {

        private final String[] IMAGE_PROJECTION = {
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.DATE_ADDED,
                MediaStore.Images.Media._ID};

        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            LogUtils.i("onCreateLoader");
            // 根据图片设置参数新增验证条件
            StringBuilder selectionArgs = new StringBuilder();

            if (imageConfig != null) {
                if (imageConfig.minWidth != 0) {
                    selectionArgs.append(MediaStore.Images.Media.WIDTH + " >= " + imageConfig.minWidth);
                }

                if (imageConfig.minHeight != 0) {
                    selectionArgs.append("".equals(selectionArgs.toString()) ? "" : " and ");
                    selectionArgs.append(MediaStore.Images.Media.HEIGHT + " >= " + imageConfig.minHeight);
                }

                if (imageConfig.minSize != 0f) {
                    selectionArgs.append("".equals(selectionArgs.toString()) ? "" : " and ");
                    selectionArgs.append(MediaStore.Images.Media.SIZE + " >= " + imageConfig.minSize);
                }

                if (imageConfig.mimeType != null) {
                    selectionArgs.append(" and (");
                    for (int i = 0, len = imageConfig.mimeType.length; i < len; i++) {
                        if (i != 0) {
                            selectionArgs.append(" or ");
                        }
                        selectionArgs.append(MediaStore.Images.Media.MIME_TYPE + " = '" + imageConfig.mimeType[i] + "'");
                    }
                    selectionArgs.append(")");
                }
            }

            if (id == LOADER_ALL) {
                CursorLoader cursorLoader = new CursorLoader(getMyActivity(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        selectionArgs.toString(), null, IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            } else if (id == LOADER_CATEGORY) {
                String selectionStr = selectionArgs.toString();
                if (!"".equals(selectionStr)) {
                    selectionStr += " and" + selectionStr;
                }
                CursorLoader cursorLoader = new CursorLoader(getMyActivity(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, IMAGE_PROJECTION,
                        IMAGE_PROJECTION[0] + " like '%" + args.getString("path") + "%'" + selectionStr, null,
                        IMAGE_PROJECTION[2] + " DESC");
                return cursorLoader;
            }

            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            LogUtils.i("onLoadFinished");

            if (data != null) {
                mResultFolder.clear();
                List<Image> images = new ArrayList<Image>();
                int count = data.getCount();
                if (count > 0) {
                    data.moveToFirst();
                    do {
                        String path = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[0]));
                        String name = data.getString(data.getColumnIndexOrThrow(IMAGE_PROJECTION[1]));
                        long dateTime = data.getLong(data.getColumnIndexOrThrow(IMAGE_PROJECTION[2]));

                        Image image = new Image(path, name, dateTime);
                        images.add(image);
//                        if (!hasFolderGened) {
                            // 获取文件夹名称
                            File imageFile = new File(path);
                            File folderFile = imageFile.getParentFile();

                            Folder folder = new Folder();
                            folder.name = folderFile.getName();
                            folder.path = folderFile.getAbsolutePath();
                            folder.cover = image;
                            if (!mResultFolder.contains(folder)) {
                                List<Image> imageList = new ArrayList<Image>();
                                imageList.add(image);
                                folder.images = imageList;
                                mResultFolder.add(folder);
                            } else {
                                // 更新
                                Folder f = mResultFolder.get(mResultFolder.indexOf(folder));
                                f.images.add(image);
                            }
//                        }

                    } while (data.moveToNext());
//                    hasFolderGened = true;
                }

                adapter.notifyDataSetChanged();

            }
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {
            LogUtils.i("onLoaderReset=");
        }
    };
}
