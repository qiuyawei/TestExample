package com.wm.example.test.exampletest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.utils.LogUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Author:qyw
 * on 2018/11/13.
 * QQ:448739075
 * 描述：
 */

public class BitmapShaderView extends android.support.v7.widget.AppCompatImageView {

    private float mRadius;
    private int mWidth;
    private Paint mPaint;
    private Bitmap mBitmap;
    private String imageUrl;

    public BitmapShaderView(Context context) {
        this(context, null);
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BitmapShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = Math.min(getMeasuredWidth(), getMeasuredHeight());
        mRadius = mWidth * 1.0f / 2;
//        setMeasuredDimension(mWidth, mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBitmapShader();
//        canvas.drawCircle(mRadius, mRadius, mRadius, mPaint);
//        canvas.drawCircle(mRadius,mRadius,mRadius-10.0f,mPaint);
        //第二个参数rx：x方向上的圆角半径。第三个参数ry：y方向上的圆角半径。
        canvas.drawRoundRect(new RectF(0, 0, mWidth, mWidth), 30, 30, mPaint);
    }
    private void setBitmapShader() {
        if(mBitmap==null) {
            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.llllll);
        }else {

        }
        float mBitWidth = Math.min(mBitmap.getWidth(), mBitmap.getHeight());
        Log.d("aaa", "mBitmap.getWidth() = " + mBitmap.getWidth());
        Log.d("aaa", "mBitmap.getHeight() = " + mBitmap.getHeight());
        BitmapShader mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = mWidth * 1.0f / mBitWidth;
        Log.d("aaa", "scale = " + scale);
        Matrix mMatrix = new Matrix();
        mMatrix.setScale(scale, scale);
        mBitmapShader.setLocalMatrix(mMatrix);
        mPaint.setShader(mBitmapShader);

    }


    public void setImageUrl(String imageUrls){
        this.imageUrl=imageUrls;
        new AsyncTask<Bitmap, Integer, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Bitmap... integers) {
                Bitmap bitmap=null;
                try {
                    URL url1=new URL(imageUrl);
                    try {
                        HttpURLConnection connection= (HttpURLConnection) url1.openConnection();
                        LogUtils.i("code:"+connection.getResponseCode());
                        LogUtils.i("message:"+connection.getResponseMessage());
                        InputStream inputStream=connection.getInputStream();
                        bitmap=BitmapFactory.decodeStream(inputStream);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return bitmap;
            }

            @Override
            protected void onPostExecute(Bitmap integer) {
                super.onPostExecute(integer);
                if(integer!=null){
                    mBitmap=integer;
                    invalidate();
                }

            }
        }.execute();
    }


}
