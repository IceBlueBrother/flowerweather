package com.flowerweather.android.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.flowerweather.android.AddCityActivity;
import com.flowerweather.android.R;

public class AddCityEditViewLayout extends LinearLayout{

    public AddCityEditViewLayout(final Context context, AttributeSet attrs){
        super(context,attrs);
        //LayoutInflater.from()构建LayoutInflater对象
        //inflate()动态加载一个布局文件
        //参数1：要加载的布局文件的id
        //参数2：给加载好的布局文件添加一个父布局，此处指定为TitleLayout
        LayoutInflater.from(context).inflate(R.layout.add_city_editview,this);

        final EditText EditText= (EditText) findViewById(R.id.edit_view);
        Button SearchCity= (Button) findViewById(R.id.search_city);
        SearchCity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //先判断输入框中是否输入了内容
                String editText=EditText.getText().toString();
                if (editText==null||"".equals(editText)){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                    //设置标题
//
                    //设置内容
                    dialog.setMessage("请先输入关键字");
                    //可否取消
                    dialog.setCancelable(false);
                    //设置确定按钮点击事件
                    dialog.setPositiveButton("OK",new DialogInterface.
                            OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
//                    //设置取消按钮点击事件
//                    dialog.setNegativeButton("Calcel",new DialogInterface.
//                            OnClickListener(){
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                        }
//                    });
                    //显示对话框
                    dialog.show();
                    return;
                }
                Toast.makeText(context, "弹窗看看", Toast.LENGTH_SHORT).show();
                RecyclerView DefaultCity= (RecyclerView) ((Activity) context).findViewById(R.id.default_city);
                DefaultCity.setVisibility(View.GONE);
            }
        });
    }

}
