package com.flowerweather.android;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView weatherContentText= (TextView) findViewById(R.id.weather_content_text);
        String flowerContent=generateFlowerContent();
        weatherContentText.setText(flowerContent);

//        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
//        //设置menu图标颜色为图标本身的颜色
//        navigationView.setItemIconTintList(null);
//        //动态添加menu的Item
//        navigationView.getMenu().add(R.id.nav_g,1,1,"测试");
    }

    private String generateFlowerContent(){
        StringBuilder flowerContent=new StringBuilder();
        for (int i=0;i<3000;i++){
            flowerContent.append("先试试");
        }
        return flowerContent.toString();
    }


}
