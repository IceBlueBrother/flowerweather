package com.flowerweather.android.util;

import android.content.Context;

import com.flowerweather.android.db.City;
import com.flowerweather.android.db.MyCity;
import com.flowerweather.android.gson.Citybasic;
import com.flowerweather.android.status.AddressStatus;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class ZqzbUtil {

    /**
     * 页面三点击城市时的操作
     */
    public static void FBCity(Context context,String getC){
        Utility.queryFromServer(context,AddressStatus.getCityAdd+getC+"&group=cn&number=1&mode=equal","SearchCityFBCity",getC);
    }
}
