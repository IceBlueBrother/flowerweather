package com.flowerweather.android.db;

import org.litepal.crud.DataSupport;

import java.util.List;

public class Daily extends DataSupport{
    //主键Id
    private int id;
    //最后更新时间
    private String lastUpdate;
    //逐日天气（今天和未来四天）
    private List<Day> date;
    //城市主表Id
    private int CityId;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Day> getDate() {
        return date;
    }

    public void setDate(List<Day> date) {
        this.date = date;
    }

    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
