package com.flowerweather.android.db;

import org.litepal.crud.DataSupport;

/**
 * 天气实况
 */
public class Now extends DataSupport{
    //主键Id
    private int id;
    //最后更新时间
    private String lastUpdate;
    //气象文字
    private String text;
    //气象代码
    private String code;
    //温度
    private String temperature;
    //城市主表Id
    private int CityId;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
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
