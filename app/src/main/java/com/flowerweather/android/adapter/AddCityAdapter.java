package com.flowerweather.android.adapter;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flowerweather.android.R;
import com.flowerweather.android.util.Utility;
import com.flowerweather.android.util.ZqzbUtil;

import java.util.ArrayList;
import java.util.List;

public class AddCityAdapter extends RecyclerView.Adapter<AddCityAdapter.ViewHolder>{
    private String[] mDefaultCitys={"北京","上海","广州","深圳","天津","武汉","沈阳","重庆","杭州","南京",
        "哈尔滨","长春","呼和浩特","石家庄","银川","乌鲁木齐","拉萨","西宁","西安","兰州","太原","昆明",
        "南宁","成都","长沙","济南","南昌","合肥","郑州","福州","贵阳","海口","秦皇岛","桂林","三亚",
        "香港","澳门"};

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView defaultCityItem;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            defaultCityItem= (TextView) itemView.findViewById(R.id.default_city_item);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.add_city_item,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                String city=mDefaultCitys[position];
                //正在这里处理逻辑
                ZqzbUtil.FBCity(parent.getContext(),city);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.defaultCityItem.setText(mDefaultCitys[position]);
    }

    @Override
    public int getItemCount() {
        return mDefaultCitys.length;
    }

}
