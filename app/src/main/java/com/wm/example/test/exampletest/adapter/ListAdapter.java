package com.wm.example.test.exampletest.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wm.example.test.exampletest.R;
import com.wm.example.test.exampletest.bean.NameBean;
import com.wm.example.test.exampletest.utils.DisplayUtil;
import com.wm.example.test.exampletest.utils.Folder;

import java.util.ArrayList;

/**
 * Created by Author:qyw
 * on 2018/11/1.
 * QQ:448739075
 * 描述：
 */
public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Folder> mdata;
    private int width;

    public ListAdapter(Context context,ArrayList<Folder> data){
        this.mdata=data;
        this.mContext=context;
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
            convertView=View.inflate(mContext, R.layout.item_list_name,null);
            holder=new ViewHolder();
            holder.imageView=convertView.findViewById(R.id.iv_image);
            holder.tv_date=convertView.findViewById(R.id.tv_date);
            holder.tv_count=convertView.findViewById(R.id.tv_count);
            holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(width,width));

            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        Folder folder=mdata.get(position);
        Glide.with(mContext).load(folder.images.get(0).path).apply(
                RequestOptions.overrideOf(width).centerCrop()).into(holder.imageView);
        holder.tv_count.setText("数量："+folder.images.size());
        holder.tv_date.setText(folder.name);
        return convertView;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView tv_date;
        TextView tv_count;
    }
}
