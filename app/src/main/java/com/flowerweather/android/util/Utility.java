package com.flowerweather.android.util;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.flowerweather.android.R;
import com.flowerweather.android.db.City;
import com.flowerweather.android.db.MyCity;
import com.flowerweather.android.gson.CitySearch;
import com.flowerweather.android.gson.Citybasic;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Utility {
    /**
     * 访问服务器，获取访问数据
     *
     * @param address
     * @return
     */
    public static void queryFromServer(final Context context, final String address, final String type
                                        ,final String getC){
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
                            CitySearch citySearch2 = SearchCity(responseText);
                            if (citySearch2!=null&&!"".equals(citySearch2)){
                                //访问查询，获取结果列表
                                CitySearch citySearch=citySearch2;
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
                                        }
                                    });
                                }
                            }
                        }else if ("SearchCityFBCity".equals(type)){
                            List<City> dataCityList;
                            List<MyCity> dataMyCityList;

                            Citybasic city=new Citybasic();
                            City c=new City();

                            CitySearch citySearch2 = SearchCity(responseText);
                            if (citySearch2!=null&&!"".equals(citySearch2)){
                                city=citySearch2.getBasic().get(0);
                                if (getC.equals(city.getParent_city())&&city.getLocation().equals(city.getParent_city())){

                                }
                            }

                            //查询该城市是否存在
                            dataCityList= DataSupport.where("parentArea=?",getC).find(City.class);
                            if (!(dataCityList!=null&&dataCityList.size()>0)){
                                c.setParentArea(city.getParent_city());
                                c.setAdminArea(city.getAdmin_area());
                                c.setCnty(city.getCnty());
                                c.setCnty(city.getCnty());
                                c.setLat(city.getLat());
                                c.setLon(city.getLon());
                                c.setTz(city.getTz());
                                c.setSfmr("0");
                                c.save();
                            }else {
                                c=dataCityList.get(0);
                            }

                            dataMyCityList=DataSupport.where("City=?",getC).find(MyCity.class);
                            if (!(dataMyCityList!=null&&dataMyCityList.size()>0)){
                                MyCity myCity=new MyCity();
                                myCity.setCity(c.getParentArea());
                                myCity.setCityId(c.getId());
                                myCity.setSfxz("0");
                                myCity.save();
                            }

                            ((Activity) context).finish();
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

    /**
     * 获取当前所在城市，并添加/更新默认城市
     *
     * @param address
     * @return
     */
    public static void queryMyCityNow(final Context context,final String address){
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
                        Log.d(TAG, "run: "+SearchCity(responseText));
                        CitySearch citySearch2 = SearchCity(responseText);
                        if ("ok".equals(citySearch2.getStatus())){
                            Citybasic citybasic=citySearch2.getBasic().get(0);

                            //先处理City表
                            List<City> cityList= DataSupport.where("parentArea=?",citybasic.getParent_city()).find(City.class);
                            City c=new City();
                            if (cityList!=null&&cityList.size()>0){
                                c=cityList.get(0);
                                //该城市存在
                                if (!"1".equals(c.getSfmr())){
                                    //用户换城市了
                                    List<City> cityList2= DataSupport.where("Sfmr=?","1").find(City.class);
                                    if (cityList2!=null&&cityList2.size()>0){
                                        for (City c2:cityList2) {
                                            c2.setSfmr("0");
                                            c2.save();
                                        }
                                    }
                                    //保存当前数据
                                    c.setSfmr("1");
                                    c.save();
                                }
                            }else {
                                //不存在则保存
                                List<City> cityList2= DataSupport.where("Sfmr=?","1").find(City.class);
                                if (cityList2!=null&&cityList2.size()>0){
                                    for (City c2:cityList2) {
                                        c2.setSfmr("0");
                                        c2.save();
                                    }
                                }
                                //保存当前数据
                                c.setParentArea(citybasic.getParent_city());
                                c.setAdminArea(citybasic.getAdmin_area());
                                c.setCnty(citybasic.getCnty());
                                c.setCnty(citybasic.getCnty());
                                c.setLat(citybasic.getLat());
                                c.setLon(citybasic.getLon());
                                c.setTz(citybasic.getTz());
                                c.setSfmr("1");
                                c.save();
                            }

                            //同样的操作MyCity表再重复一次
                            List<MyCity> MycityList= DataSupport.where("City=?",citybasic.getParent_city()).find(MyCity.class);
                            if (MycityList!=null&&MycityList.size()>0){
                                MyCity mc=MycityList.get(0);
                                //该城市存在
                                if (!"1".equals(mc.getSfxz())){
                                    //用户换城市了
                                    List<MyCity> MycityList2= DataSupport.where("sfxz=?","1").find(MyCity.class);
                                    if (MycityList2!=null&&MycityList2.size()>0){
                                        for (MyCity c2:MycityList2) {
                                            c2.setSfxz("0");
                                            c2.save();
                                        }
                                    }
                                    //保存当前数据
                                    mc.setSfxz("1");
                                    mc.save();
                                }
                            }else {
                                //不存在则保存
                                List<MyCity> MycityList2= DataSupport.where("sfxz=?","1").find(MyCity.class);
                                if (MycityList2!=null&&MycityList2.size()>0){
                                    for (MyCity c2:MycityList2) {
                                        c2.setSfxz("0");
                                        c2.save();
                                    }
                                }
                                //保存当前数据
                                MyCity myCity=new MyCity();
                                myCity.setCity(c.getParentArea());
                                myCity.setCityId(c.getId());
                                myCity.setSfxz("1");
                                myCity.save();
                            }
                        }else{
                            ((Activity) context).finish();
                        }
                    }
                });
            }
        });
    }



}
