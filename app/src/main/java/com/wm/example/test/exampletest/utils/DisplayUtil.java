package com.wm.example.test.exampletest.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.location.LocationManager;
import android.os.Environment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by vinson.ma on 2017/9/22
 *
 * @Description 屏幕相关参数获取工具
 */

public class DisplayUtil {

    private DisplayUtil() {
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(double dipValue) {
        return (int) (dipValue * getDisplayDensity() + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(double pxValue) {
        return (int) (pxValue / getDisplayDensity() + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     */
    public static int px2sp(float pxValue) {
        return (int) (pxValue / getScaledDensity() + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float spValue) {
        return (int) (spValue * getScaledDensity() + 0.5f);
    }

    /**
     * 获取手机屏幕的像素高
     */
    public static int getDisplayHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取手机屏幕的像素宽
     */
    public static int getDisplayWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕密度
     */
    public static float getDisplayDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * 获取字体密度
     */
    public static float getScaledDensity() {
        return Resources.getSystem().getDisplayMetrics().scaledDensity;
    }

    /**
     * 获取手机状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        if (context == null || ((Activity) context).getWindow().getDecorView() == null) {
            return 0;
        }
        int statusHeight;
        Rect localRect = new Rect();
        ((Activity) context).getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = context.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * 获得View在屏幕的位置
     */
    public static Rect getRectOnScreen(View v) {
        if (v == null) {
            return new Rect();
        }
        int[] point = new int[2];
        v.getLocationOnScreen(point);
        return new Rect(point[0], point[1], point[0] + v.getWidth(), point[1] + v.getHeight());
    }

    public static void hideKeyboard(Activity context) {
        if (context != null && context.getCurrentFocus() != null)
            ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        LogUtils.d("hide keyboard");
    }

    public static void showKeyboard(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive())
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    private static Map<String, Bitmap> carChartMap = new HashMap<String, Bitmap>();
    public static void recycleBitmaps() {
        try {
            Iterator iter = carChartMap.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                Object val = entry.getValue();
                if(val!=null){
                    ((Bitmap)val).recycle();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        carChartMap.clear();
    }

    public static Bitmap getImageFromExternalStorage(String carChartImageId, String imageName) {
        Bitmap carChar = carChartMap.get( carChartImageId + imageName);
        if (carChar != null) {
            LogUtils.d("carChartMap has this img");
            return carChar;
        }else{
            LogUtils.d("carChartMap has no this img");
        }
        String sdpath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "";// 获取sdcard的根路径
        String filePath = Environment.getExternalStorageDirectory() + "/WM-Motor/CarChart/"  + carChartImageId + "/" + imageName;
        File file = new File(filePath);
        if (file.exists()) {
            Bitmap bm = null;
            try {
                bm = BitmapFactory.decodeFile(filePath);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
            if (bm != null) {
                carChartMap.put( carChartImageId + imageName, bm);
                return bm;
            }
        }
        return null;
    }
    public static boolean isLocationEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context
                .LOCATION_SERVICE);
        boolean networkProvider = locationManager.isProviderEnabled(LocationManager
                .NETWORK_PROVIDER);
        boolean gpsProvider = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (networkProvider || gpsProvider) return true;
        return false;
    }
//    public static byte[] getFileFromExternalStorage(Context context, String fileName) {
////        LogUtils.d("getFileFromExternalStorage");
////        JSONObject carInfoJO = UserInfo.getCurrentVehicleInfo(context);
////        if (carInfoJO == null) {
////            LogUtils.d("car info jo = null");
////            return null;
////        }else{
////            LogUtils.d("car info jo != null");
////        }
////        String strModelName = carInfoJO.getString("modelName");
//        String sdpath = Environment.getExternalStorageDirectory().getAbsolutePath() + "";// 获取sdcard的根路径
//        String filePath = Environment.getExternalStorageDirectory() + "/WM-Motor/CarChart/" + "EX5" + "/" + fileName;
//        LogUtils.d("filePath=" + filePath);
//        File file = new File(filePath);
//        if (file.exists()) {
//            LogUtils.d("file exist");
//            return readFile(file);
//        }
//        return null;
//    }

    public static byte[] readFile(File file) {
        // 需要读取的文件，参数是文件的路径名加文件名
        if (file.isFile()) {
            // 以字节流方法读取文件
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                // 设置一个，每次 装载信息的容器
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                // 开始读取数据
                int len = 0;// 每次读取到的数据的长度
                while ((len = fis.read(buffer)) != -1) {// len值为-1时，表示没有数据了
                    // append方法往sb对象里面添加数据
                    outputStream.write(buffer, 0, len);
                }
                // 输出字符串
                return outputStream.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            LogUtils.d("文件不存在！");
        }
        return null;
    }
}
