package com.flowerweather.android.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flowerweather.android.R;

public class WeatherShowFragment extends Fragment{
    private static final String TAB_POSITION="tab_position";
    private static final String CITY_NAME="city_name";

    public WeatherShowFragment() {

    }

    public static WeatherShowFragment newInstance(int tabPosition,String CityName) {
        WeatherShowFragment fragment = new WeatherShowFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(TAB_POSITION, tabPosition);
        bundle.putString(CITY_NAME,CityName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        int tabPosition = bundle.getInt(TAB_POSITION);
        String CityName=bundle.getString(CITY_NAME);
        View view=inflater.inflate(R.layout.weather_show_fragment,container,false);
        TextView Test= (TextView) view.findViewById(R.id.weather_content_test);
        StringBuffer b=new StringBuffer();
        for (int i=0;i<3000;i++){
            b.append(CityName+tabPosition);
        }
        Test.setText(b.toString());
        return view;
    }
}
