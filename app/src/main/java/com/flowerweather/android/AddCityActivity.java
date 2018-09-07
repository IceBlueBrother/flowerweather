package com.flowerweather.android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.flowerweather.android.adapter.AddCityAdapter;

public class AddCityActivity extends AppCompatActivity {

    private AddCityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        RecyclerView AddCityRecyclerView= (RecyclerView) findViewById(R.id.default_city);
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);
        AddCityRecyclerView.setLayoutManager(layoutManager);
        adapter=new AddCityAdapter();
        AddCityRecyclerView.setAdapter(adapter);
    }
}
