package com.flowerweather.android.db;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class City extends DataSupport{
    //主键ID
    private int id;
    //该地区／城市的上级城市（查天气取这个）
    private String parentArea;
    //该地区／城市所属行政区域
    private String adminArea;
    //该地区／城市所属国家名称
    private String cnty;
    //地区／城市纬度
    private String lat;
    //地区／城市经度
    private String lon;
    //该地区／城市所在时区
    private String tz;
    //最后更新天气的时间
    private Date lastUpdate;
    //是否默认(1是/0否)
    private String Sfmr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParentArea() {
        return parentArea;
    }

    public void setParentArea(String parentArea) {
        this.parentArea = parentArea;
    }

    public String getAdminArea() {
        return adminArea;
    }

    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea;
    }

    public String getCnty() {
        return cnty;
    }

    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getSfmr() {
        return Sfmr;
    }

    public void setSfmr(String sfmr) {
        Sfmr = sfmr;
    }


}
