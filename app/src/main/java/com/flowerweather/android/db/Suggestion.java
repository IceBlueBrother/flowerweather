package com.flowerweather.android.db;

import org.litepal.crud.DataSupport;

public class Suggestion extends DataSupport{
    private int id;
    //洗车
    private String car_washing;
    //穿衣
    private String dressing;
    //感冒
    private String flu;
    //运动
    private String sport;
    //旅游
    private String travel;
    //紫外线
    private String uv;
    //最后更新时间
    private String lastUpdate;
    //城市名
    private String CityName;

    public String getCar_washing() {
        return car_washing;
    }

    public void setCar_washing(String car_washing) {
        this.car_washing = car_washing;
    }

    public String getDressing() {
        return dressing;
    }

    public void setDressing(String dressing) {
        this.dressing = dressing;
    }

    public String getFlu() {
        return flu;
    }

    public void setFlu(String flu) {
        this.flu = flu;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }
}
