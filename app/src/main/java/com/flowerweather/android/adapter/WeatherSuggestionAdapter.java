package com.flowerweather.android.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flowerweather.android.R;
import com.flowerweather.android.db.MyCity;
import com.flowerweather.android.util.Entity;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Map;

public class WeatherSuggestionAdapter extends RecyclerView.Adapter<WeatherSuggestionAdapter.ViewHolder>{
    List<Entity> sugList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView WSSug;
        TextView WSBrief;

        public ViewHolder(View itemView) {
            super(itemView);
            WSSug= (TextView) itemView.findViewById(R.id.Weather_suggestion_sug);
            WSBrief= (TextView) itemView.findViewById(R.id.Weather_suggestion_brief);
        }
    }

    public WeatherSuggestionAdapter(List<Entity> list){
        sugList=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //加载weather_suggestion_item布局
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_suggestion_item,parent,false);
        //将加载出来的布局传到构造函数中
        final WeatherSuggestionAdapter.ViewHolder holder=new WeatherSuggestionAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //通过position得到当前Map实例
        Entity sug=sugList.get(position);
        //将数据设置到ViewHolder
        holder.WSSug.setText(sug.getKey());
        holder.WSBrief.setText(sug.getValue());
    }

    @Override
    public int getItemCount() {
        return sugList.size();
    }
}
