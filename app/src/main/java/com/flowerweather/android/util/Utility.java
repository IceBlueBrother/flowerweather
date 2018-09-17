package com.flowerweather.android.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.flowerweather.android.db.MyCity;
import com.flowerweather.android.gson.CitySearch;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Utility {
    public static CitySearch citySearch;
    /**
     * 访问服务器，获取访问数据
     *
     * @param address
     * @return
     */
    public static void queryFromServer(final Context context, final String address, final String type){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            private static final String TAG = "Utility";
            @Override
            public void onFailure(Call call, final IOException e) {
                Log.d(TAG, "onFailure: "+address);
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog.Builder dialog=new AlertDialog.Builder(context);
                        //设置内容
                        dialog.setMessage(e.getMessage());
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
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseText= response.body().string();
                Log.d(TAG, "onResponse:"+address);
                Log.d(TAG, "onResponse: "+responseText);
                ((Activity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: ...");
                        if ("SearchCity".equals(type)){
                            Log.d(TAG, "run: "+SearchCity(responseText));
                            citySearch = SearchCity(responseText);
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
    public static CitySearch SearchCity(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("HeWeather6");
            String cityContent=jsonArray.getJSONObject(0).toString();
            Log.d("CitySearch", "SearchCity: "+cityContent);
            return new Gson().fromJson(cityContent,CitySearch.class);
        } catch (JSONException e) {
            Log.d("CitySearch", "SearchCity: "+e.getMessage());
            e.printStackTrace();
        }
        Log.d("CitySearch", "SearchCity: null");
        return null;
    }

}
