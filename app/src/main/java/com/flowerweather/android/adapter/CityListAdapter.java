package com.flowerweather.android.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flowerweather.android.CityListActivity;
import com.flowerweather.android.R;
import com.flowerweather.android.db.City;
import com.flowerweather.android.db.MyCity;
import com.flowerweather.android.util.ZqzbUtil;

import org.litepal.crud.DataSupport;

import java.util.List;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder>{
    private List<MyCity> MyCitylist;

    public boolean VFlag=false;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView CityListName;
        Button deleteMycity;

        public ViewHolder(View itemView) {
            super(itemView);
            CityListName= (TextView) itemView.findViewById(R.id.city_list_name);
            deleteMycity= (Button) itemView.findViewById(R.id.delete_mycity);
        }
    }

    public CityListAdapter(List<MyCity> list){
        MyCitylist=list;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        //加载city_list_item布局
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item,parent,false);
        //将加载出来的布局传到构造函数中
        final ViewHolder holder=new ViewHolder(view);
        holder.deleteMycity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                MyCity city=MyCitylist.get(position);
                //正在这里处理逻辑
                city.delete();
                MyCitylist.clear();
                MyCitylist.addAll(DataSupport.findAll(MyCity.class));
                notifyDataSetChanged();
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (VFlag){
            holder.deleteMycity.setVisibility(View.VISIBLE);
        }else {
            holder.deleteMycity.setVisibility(View.GONE);
        }
        //通过position得到当前MyCity实例
        MyCity myCity=MyCitylist.get(position);
        //将数据设置到ViewHolder
        holder.CityListName.setText(myCity.getCity());
    }

    @Override
    public int getItemCount() {
        return MyCitylist.size();
    }
}
