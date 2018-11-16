package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.utils.DisplayUtil;
import com.wm.example.test.exampletest.utils.LogUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Author:qyw
 * on 2018/11/1.
 * QQ:448739075
 * 描述：
 */
public class CircleImageView extends android.support.v7.widget.AppCompatImageView {
    private Paint mPain;
    private int mType = 0;//0代表圆形，1代表圆角
    private Bitmap mBitmap;
    private int mRadius = 0;//圆形半径
    private int width, height;
    private String mImagePath;//网络图片url
    private BitmapShader bitmapShader;

    public CircleImageView(Context context) {
        super(context);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getWidth();
        height = getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        innitShader();
        if (mType == 0) {
            mRadius = Math.min(width, height)/2;
        } else {
            mRadius = 10;
        }
        if (mType == 1) {
            drawRoundRect(canvas);
        } else {
            drawCircle(canvas);
        }
    }

    private void drawRoundRect(Canvas canvas) {
        LogUtils.i("screenWidth:"+DisplayUtil.getDisplayWidth());
        LogUtils.i("getLeft:"+DisplayUtil.px2dip(getLeft()));
        LogUtils.i("getRight:"+DisplayUtil.px2dip(getRight()));
//        LogUtils.i("getBottom:"+getBottom());
//        LogUtils.i("getTop:"+getTop());

        RectF rect = new RectF(0, 0, getWidth(), getHeight());
        canvas.drawRoundRect(rect, 30, 30, mPain);
    }

    private void drawCircle(Canvas canvas) {
        float cx = getPaddingLeft() + getWidth() / 2;
        float cy = getPaddingTop() + getHeight() / 2;
        canvas.drawCircle(cx, cy, mRadius, mPain);
    }

    private void innitShader() {
        mPain = new Paint();
        if (mImagePath != null && !TextUtils.isEmpty(mImagePath)) {

        } else {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        }
        bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale=getHeight()/mBitmap.getHeight();
        Matrix matrix=new Matrix();
        matrix.setScale(scale,scale);
        bitmapShader.setLocalMatrix(matrix);
        mPain.setShader(bitmapShader);
    }

    public void setmType(int mType) {
        this.mType = mType;
        invalidate();
    }

    public void setmRadius(int mRadius) {
        this.mRadius = mRadius;
    }

    public void setmImagePath(String path){
        this.mImagePath=path;
        getNetImage();
    }

    HttpURLConnection connection;

    private void getNetImage() {
        new AsyncTask<Bitmap, Integer, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Bitmap... bitmaps) {
                Bitmap bitmap = null;
                try {
                    URL url = new URL(mImagePath);
                    try {
                        connection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = connection.getInputStream();
                        if (inputStream != null) {
                            bitmap = BitmapFactory.decodeStream(inputStream);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                if(bitmap!=null){
                    mBitmap=bitmap;
                    invalidate();
                }
            }
        }.execute();
    }
}
