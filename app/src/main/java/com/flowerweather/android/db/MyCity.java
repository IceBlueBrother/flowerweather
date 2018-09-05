package com.flowerweather.android.db;

import org.litepal.crud.DataSupport;

public class MyCity extends DataSupport{
    //主键Id
    private int id;
    //城市名称
    private String City;
    //城市Id（用户数据库里的Id）
    private int CityId;
    //是否选中（优先加载这个）
    private String sfxz;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getSfxz() {
        return sfxz;
    }

    public void setSfxz(String sfxz) {
        this.sfxz = sfxz;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }
}
