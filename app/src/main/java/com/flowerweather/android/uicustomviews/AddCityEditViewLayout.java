package com.flowerweather.android.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flowerweather.android.R;

public class AddCityEditViewLayout extends LinearLayout{

    public AddCityEditViewLayout(final Context context, AttributeSet attrs){
        super(context,attrs);
        //LayoutInflater.from()构建LayoutInflater对象
        //inflate()动态加载一个布局文件
        //参数1：要加载的布局文件的id
        //参数2：给加载好的布局文件添加一个父布局，此处指定为TitleLayout
        LayoutInflater.from(context).inflate(R.layout.add_city_editview,this);

        Button SearchCity= (Button) findViewById(R.id.search_city);
        SearchCity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "弹窗看看", Toast.LENGTH_SHORT).show();
                RecyclerView DefaultCity= (RecyclerView) ((Activity) context).findViewById(R.id.default_city);
                DefaultCity.setVisibility(View.GONE);
            }
        });
    }

}
