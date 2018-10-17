package com.flowerweather.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.flowerweather.android.R;
import com.flowerweather.android.db.Now;
import com.flowerweather.android.gson.CitySearch;
import com.flowerweather.android.gson.Nowresults;
import com.flowerweather.android.gson.gsonin.Nlocation;
import com.flowerweather.android.gson.gsonin.Nnow;
import com.flowerweather.android.status.AddressStatus;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class getWeatherUtil {
    /**
     * 获取天气实况
     *
     * @param CityName
     * @return
     */
    public static void getWeatherNow(final Context context, final String CityName, final View view){
        HttpUtil.sendOkHttpRequest(AddressStatus.getWeatherNow+CityName, new Callback() {
            private static final String TAG = "Utility";

            @Override
            public void onFailure(Call call, final IOException e) {
                Log.d(TAG, "onFailure: " + CityName);
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                        //设置内容
                        dialog.setMessage(e.getMessage());
                        //可否取消
                        dialog.setCancelable(false);
                        //设置确定按钮点击事件
                        dialog.setPositiveButton("确认", new DialogInterface.
                                OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                        //显示对话框
                        dialog.show();
                    }
                });
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                final String responseText = response.body().string();
                Log.d(TAG, "onResponse:" + CityName);
                Log.d(TAG, "onResponse: " + responseText);
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: ...");
                        //获得结果
                        Nowresults nowresults = getNowresults(responseText);
                        //没有得到内容
                        if (nowresults==null){
                            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                            //设置内容
                            dialog.setMessage("未知错误");
                            //可否取消
                            dialog.setCancelable(false);
                            //设置确定按钮点击事件
                            dialog.setPositiveButton("确认", new DialogInterface.
                                    OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ((Activity) context).finish();
                                }
                            });
                            //显示对话框
                            dialog.show();
                        }else{
                            //有内容
                            Nnow nnow=nowresults.getNow();
                            Nlocation nlocation=nowresults.getLocation();
                            String updateTimeT=nowresults.getLast_update();
                            String updateTime=updateTimeT.substring(0,10)+" "+updateTimeT.substring(11,19);
                            //将数据存入数据库
                            List<Now> l=DataSupport.where("CityName=?",CityName).find(Now.class);
                            Now now=null;
                            if (l!=null&&l.size()>0){
                                //有值更新
                                now=l.get(0);
                                now.setLastUpdate(updateTime);
                                now.setText(nnow.getText());
                                now.setCode(nnow.getCode());
                                now.setTemperature(nnow.getTemperature());
                                now.save();
                            }else {
                                //无值插入
                                now=new Now();
                                now.setLastUpdate(updateTime);
                                now.setText(nnow.getText());
                                now.setCode(nnow.getCode());
                                now.setTemperature(nnow.getTemperature());
                                now.setCityName(CityName);
                                now.save();
                            }
                            //展示信息
                            TextView Wtemperature= (TextView) view.findViewById(R.id.Weather_temperature);
                            TextView Wtext= (TextView) view.findViewById(R.id.Weather_text);
                            Wtemperature.setText(now.getTemperature()+"℃");
                            Wtext.setText(now.getText());
                        }
                    }
                });
            }
        });
    }


    /**
     * 获取城市搜索返回的信息
     *
     * @param response
     * @return
     */
    public static Nowresults getNowresults(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("results");
            String Content=jsonArray.getJSONObject(0).toString();
            Log.d("CitySearch", "SearchCity: "+Content);
            return new Gson().fromJson(Content,Nowresults.class);
        } catch (JSONException e) {
            Log.d("CitySearch", "SearchCity: "+e.getMessage());
            e.printStackTrace();
        }
        Log.d("CitySearch", "SearchCity: null");
        return null;
    }

}