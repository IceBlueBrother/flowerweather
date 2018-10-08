package com.flowerweather.android;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flowerweather.android.adapter.CityListAdapter;
import com.flowerweather.android.db.MyCity;

import org.litepal.crud.DataSupport;

import java.util.List;

public class CityListActivity extends AppCompatActivity implements View.OnClickListener{
    private CityListAdapter adapter;

    private SwipeRefreshLayout swipeRefresh;

    private List<MyCity> myCityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        RecyclerView CityList= (RecyclerView) findViewById(R.id.city_list);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        CityList.setLayoutManager(manager);
        myCityList=DataSupport.findAll(MyCity.class);
        adapter=new CityListAdapter(myCityList);
        CityList.setAdapter(adapter);

        swipeRefresh= (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        //设置下拉进度条的颜色
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        //设置下拉刷新监听器
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                refreshMyCity();
            }
        });
        
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

    private  void refreshMyCity(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //切回主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myCityList.clear();
                        myCityList.addAll(DataSupport.findAll(MyCity.class));
                        //通知数据发生变化
                        adapter.notifyDataSetChanged();
                        //刷新结束，隐藏进度条
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        myCityList.clear();
        myCityList.addAll(DataSupport.findAll(MyCity.class));
        adapter.notifyDataSetChanged();
    }
}
