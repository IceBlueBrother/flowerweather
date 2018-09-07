package com.flowerweather.android.gson;

import java.util.List;

public class CitySearch {
    public String status;
    public List<Citybasic> basic;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Citybasic> getBasic() {
        return basic;
    }

    public void setBasic(List<Citybasic> basic) {
        this.basic = basic;
    }
}
