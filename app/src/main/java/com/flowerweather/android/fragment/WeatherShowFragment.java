package com.flowerweather.android.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flowerweather.android.R;
import com.flowerweather.android.db.Now;
import com.flowerweather.android.util.getWeatherUtil;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherShowFragment extends Fragment{
    private static final String TAB_POSITION="tab_position";
    private static final String CITY_NAME="city_name";

    public WeatherShowFragment() {

    }

    public static WeatherShowFragment newInstance(int tabPosition,String CityName) {
        WeatherShowFragment fragment = new WeatherShowFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAB_POSITION, tabPosition);
        bundle.putString(CITY_NAME,CityName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int tabPosition = bundle.getInt(TAB_POSITION);
        String CityName=bundle.getString(CITY_NAME);
        View view=inflater.inflate(R.layout.weather_show_fragment,container,false);

        //获取天气实况
        List<Now> list=DataSupport.where("CityName=?",CityName).find(Now.class);
        if (list!=null&&list.size()>0){
            Now now=list.get(0);

            try {
                //判断最后更新时间
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date newTime=new Date();
                Date lastUpdate=format.parse(now.getLastUpdate());
                int minutes = (int) ((newTime.getTime() - lastUpdate.getTime())/(1000 * 60));
                if (minutes>60){
                    getWeatherUtil.getWeatherNow(getContext(),CityName,view);
                }else {
                    TextView Wtemperature= (TextView) view.findViewById(R.id.Weather_temperature);
                    TextView Wtext= (TextView) view.findViewById(R.id.Weather_text);
                    Wtemperature.setText(now.getTemperature()+"℃");
                    Wtext.setText(now.getText());
                }
            } catch (ParseException e) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                //设置内容
                dialog.setMessage(e.getMessage());
                //可否取消
                dialog.setCancelable(false);
                //设置确定按钮点击事件
                dialog.setPositiveButton("确认", new DialogInterface.
                        OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                });
                //显示对话框
                dialog.show();
            }
        }else {
            getWeatherUtil.getWeatherNow(getContext(),CityName,view);
        }

        return view;
    }
}
