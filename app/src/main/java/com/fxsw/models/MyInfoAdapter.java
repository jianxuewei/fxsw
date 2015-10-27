package com.fxsw.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fxsw.fxsw.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 2015/10/27.
 */
public class MyInfoAdapter extends ArrayAdapter<Info> {
    int resourceId;
    List<Info> list=new ArrayList<>();

    public MyInfoAdapter(Context context, int resource, List<Info> objects) {
        super(context, resource, objects);
        this.resourceId=resource;
        this.list=objects;

    }

    @Override
    public Info getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Info info =getItem(position);
        View v= LayoutInflater.from(getContext()).inflate(resourceId,null);
        v.setPadding(0,30,0,30);
        ImageView imageView=(ImageView)v.findViewById(R.id.imageview);
        TextView textView=(TextView)v.findViewById(R.id.textview);
        imageView.setImageResource(info.getImgId());
        textView.setText(info.getName());
        return  v;

    }
}