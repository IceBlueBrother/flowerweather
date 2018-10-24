package com.flowerweather.android.gson;

import com.flowerweather.android.gson.gsonin.Ddaily;
import com.flowerweather.android.gson.gsonin.Nlocation;

import java.util.List;

public class Dailyresults {

    private Nlocation location;
    private List<Ddaily> daily;
    private String last_update;

    public Nlocation getLocation() {
        return location;
    }

    public void setLocation(Nlocation location) {
        this.location = location;
    }

    public List<Ddaily> getDaily() {
        return daily;
    }

    public void setDaily(List<Ddaily> daily) {
        this.daily = daily;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
