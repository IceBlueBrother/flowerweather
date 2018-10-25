package com.flowerweather.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flowerweather.android.R;
import com.flowerweather.android.db.Day;

import java.util.List;

public class WeatherDailyAdapter extends RecyclerView.Adapter<WeatherDailyAdapter.ViewHolder>{
    List<Day> dayList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView Wddate;
        private TextView TextDay;
        private TextView TextNight;
        private TextView tempe;
        private TextView windDirection;
        private TextView windDirectionDegree;
        private TextView windSpeed;
        private TextView windScale;

        public ViewHolder(View itemView) {
            super(itemView);
            Wddate= (TextView) itemView.findViewById(R.id.WDdate);
            TextDay= (TextView) itemView.findViewById(R.id.text_day);
            TextNight= (TextView) itemView.findViewById(R.id.text_night);
            tempe= (TextView) itemView.findViewById(R.id.tempe);
            windDirection= (TextView) itemView.findViewById(R.id.wind_direction);
            windDirectionDegree= (TextView) itemView.findViewById(R.id.wind_direction_degree);
            windSpeed= (TextView) itemView.findViewById(R.id.wind_speed);
            windScale= (TextView) itemView.findViewById(R.id.wind_scale);
        }
    }

    public WeatherDailyAdapter(List<Day> days){
        dayList=days;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载flower_item布局
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_daily_item,parent,false);
        //将加载出来的布局传到构造函数中
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //通过position得到当前Day实例
        Day day=dayList.get(position);
        //将数据设置到ViewHolder
        holder.Wddate.setText(day.getDate());
        holder.TextDay.setText(day.getText_day());
        holder.TextNight.setText(day.getText_night());
        holder.tempe.setText(day.getHigh()+"/"+day.getLow()+"℃");
        holder.windDirection.setText("风向："+day.getWind_direction());
        holder.windDirectionDegree.setText("风向角度："+day.getWind_direction_degree());
        holder.windSpeed.setText("风速："+day.getWind_speed());
        holder.windScale.setText("风力等级："+day.getWind_scale());
    }

    @Override
    public int getItemCount() {
        return dayList.size();
    }

}
