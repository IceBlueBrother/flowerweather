package com.flowerweather.android.uicustomviews;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flowerweather.android.CityListActivity;
import com.flowerweather.android.R;

public class TitleLayout extends LinearLayout{

    public TitleLayout(final Context context, AttributeSet attrs){
        super(context,attrs);
        //LayoutInflater.from()构建LayoutInflater对象
        //inflate()动态加载一个布局文件
        //参数1：要加载的布局文件的id
        //参数2：给加载好的布局文件添加一个父布局，此处指定为TitleLayout
        LayoutInflater.from(context).inflate(R.layout.title,this);

        Button titleBack= (Button) findViewById(R.id.title_back);
        titleBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, CityListActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
