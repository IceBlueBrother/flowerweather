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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.flowerweather.android.AddCityActivity;
import com.flowerweather.android.R;
import com.flowerweather.android.gson.CitySearch;
import com.flowerweather.android.gson.Citybasic;
import com.flowerweather.android.status.AddressStatus;
import com.flowerweather.android.util.HttpUtil;
import com.flowerweather.android.util.Utility;
import com.flowerweather.android.util.ZqzbUtil;

import java.util.ArrayList;
import java.util.List;

public class AddCityEditViewLayout extends LinearLayout{

    public AddCityEditViewLayout(final Context context, AttributeSet attrs){
        super(context,attrs);
        //LayoutInflater.from()构建LayoutInflater对象
        //inflate()动态加载一个布局文件
        //参数1：要加载的布局文件的id
        //参数2：给加载好的布局文件添加一个父布局，此处指定为TitleLayout
        LayoutInflater.from(context).inflate(R.layout.add_city_editview,this);

        final EditText EditText= (EditText) findViewById(R.id.edit_view);
        final Button SearchCity= (Button) findViewById(R.id.search_city);
        SearchCity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //先判断输入框中是否输入了内容
                String editText=EditText.getText().toString();
                if (editText==null||"".equals(editText)){
                    AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                    //设置内容
                    dialog.setMessage("请先输入关键字");
                    //可否取消
                    dialog.setCancelable(false);
                    //设置确定按钮点击事件
                    dialog.setPositiveButton("确认",new DialogInterface.
                            OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    //显示对话框
                    dialog.show();
                    return;
                }else{
                    //输入内容不为空，则进行查询
                    Utility.queryFromServer(context,AddressStatus.getCityAdd+editText+"&group=cn&number=20","SearchCity");

                    if (Utility.citySearch!=null&&!"".equals(Utility.citySearch)){
                        //访问查询，获取结果列表
                        CitySearch citySearch=Utility.citySearch;
                        //判断查询结果
                        if("ok".equals(citySearch.getStatus())){
                            //有数据，隐藏RecyclerView
                            RecyclerView DefaultCity= (RecyclerView) ((Activity)context).findViewById(R.id.default_city);
                            DefaultCity.setVisibility(View.GONE);
                            //将listView展示出来
                            ListView listView= (ListView) ((Activity)context).findViewById(R.id.choice_city);
                            listView.setVisibility(View.VISIBLE);
                            //获取需要展示的数据
                            final List<String> dataList=new ArrayList<>();
                            for (Citybasic b:citySearch.getBasic()){
                                dataList.add(b.getLocation()+","+b.getParent_city()+","+b.getAdmin_area());
                            }
                            //展示结果列表
                            ArrayAdapter<String> adapter=new ArrayAdapter<>(context,
                                    android.R.layout.simple_list_item_1,dataList);
                            listView.setAdapter(adapter);
                            //获取用户点击事件
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String[] getC=dataList.get(position).split(",");
                                    //获取城市
                                    //先查询数据库是否存在该城市
                                    //将该城市添加到用户城市列表
                                    ZqzbUtil.FBCity(context,getC[1]);
                                    //关闭Activity
                                    ((Activity) context).finish();
                                }
                            });
                            //跳转到MainActivity
                            //剩下的等MainActivity完成再继续（假装一下）
                            return;
                        }
                    }
                }
            }
        });
    }

}
