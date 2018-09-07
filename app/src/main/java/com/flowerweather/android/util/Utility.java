package com.flowerweather.android.util;

import com.flowerweather.android.gson.CitySearch;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

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
            return new Gson().fromJson(cityContent,CitySearch.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
