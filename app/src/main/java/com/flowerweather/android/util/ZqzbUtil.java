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
    public static boolean FBCity(Context context,String getC){
        List<City> dataCityList;
        List<MyCity> dataMyCityList;

        Citybasic city=new Citybasic();
        City c=new City();

            Utility.queryFromServer(context,AddressStatus.getCityAdd+getC+"&group=cn&number=1&mode=equal","SearchCityFBCity");
            if (Utility.CitySearch!=null&&!"".equals(Utility.CitySearch)){
                city=Utility.CitySearch.getBasic().get(0);
                if (getC.equals(city.getParent_city())&&city.getLocation().equals(city.getParent_city())){

                }else {
                    return false;
                }
            }else {
                return false;
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

        return true;
    }
}
