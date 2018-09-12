package com.flowerweather.android.util;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

public class FlowerWeatherApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        context=getApplicationContext();
        LitePalApplication.initialize(context);
    }

    public static Context getContext(){
        return context;
    }
}
