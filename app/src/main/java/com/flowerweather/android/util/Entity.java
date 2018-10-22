package com.flowerweather.android.util;

public class Entity {

    private String key;
    private String value;

    public Entity(String k,String v){
        this.key=k;
        this.value=v;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
