package com.flowerweather.android.db;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Daily extends DataSupport{
    //主键Id
    private int id;
    //最后更新时间
    private String lastUpdate;
    //城市主表Id
    private String CityName;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
