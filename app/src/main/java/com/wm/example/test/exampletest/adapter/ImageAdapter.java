package com.wm.example.test.exampletest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.utils.DisplayUtil;
import com.wm.example.test.exampletest.utils.Image;

import java.util.ArrayList;

/**
 * Created by Author:qyw
 * on 2018/11/7.
 * QQ:448739075
 * 描述：
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> mdata;
    private int width;

    public ImageAdapter(Context context,ArrayList<String> data){
        this.mContext=context;
        this.mdata=data;
        width=DisplayUtil.getDisplayWidth()/2-10;
    }
    @Override
    public int getCount() {
        return mdata.size();
    }

    @Override
    public Object getItem(int position) {
        return mdata.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=View.inflate(mContext,R.layout.item_image,null);
            holder=new ViewHolder();
            holder.imageView=convertView.findViewById(R.id.ivImage);
            convertView.setTag(holder);
            holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(width,width));
        }else {
            holder= (ViewHolder) convertView.getTag();
        }


        Glide.with(mContext).load(mdata.get(position)).apply(
                RequestOptions.overrideOf(width).centerCrop()
        ).into(holder.imageView);
        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
    }
}
