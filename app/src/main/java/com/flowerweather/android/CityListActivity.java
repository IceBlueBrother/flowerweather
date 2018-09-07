package com.flowerweather.android;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CityListActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        
        FloatingActionButton AddCity= (FloatingActionButton) findViewById(R.id.add_city);
        AddCity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_city:
                Intent addCityIntent=new Intent(this,AddCityActivity.class);
                startActivity(addCityIntent);
                break;
            default:
                break;
        }
    }
}
