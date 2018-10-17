package com.flowerweather.android.gson;

import com.flowerweather.android.gson.gsonin.Nlocation;
import com.flowerweather.android.gson.gsonin.Nnow;

public class Nowresults {

    private Nlocation location;
    private Nnow now;
    private String last_update;

    public Nlocation getLocation() {
        return location;
    }

    public void setLocation(Nlocation location) {
        this.location = location;
    }

    public Nnow getNow() {
        return now;
    }

    public void setNow(Nnow now) {
        this.now = now;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }
}
