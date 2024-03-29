package com.c.sahibindenweatherapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.c.sahibindenweatherapp.R;
import com.c.sahibindenweatherapp.api.model.WeatherItems;
import com.c.sahibindenweatherapp.util.DateUtil;
import com.c.sahibindenweatherapp.util.ResourceUtil;
import com.c.sahibindenweatherapp.util.TempUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ataer Caner on 2019-12-04.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.WeatherItemHolder>{

    private final Context context;
    private List<WeatherItems> weatherItems;
    private WeatherItemClickListener weatherItemClickListener;


    public interface WeatherItemClickListener {
        void onItemClicked(WeatherItems weatherItems);
    }

    public WeatherItemAdapter(Context context, WeatherItemClickListener weatherItemClickListener) {
        weatherItems = new ArrayList<>();
        this.context = context;
        this.weatherItemClickListener = weatherItemClickListener;
    }

    @NonNull
    @Override
    public WeatherItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        return new WeatherItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherItemHolder holder, int position) {
        WeatherItems weatherItems = this.weatherItems.get(position);

        Double day = weatherItems.getTemp().getDay();

        holder.txtTemp.setText(TempUtil.getCelcius(day));
        String dayName = DateUtil.getGivenDayOfWeekAsName((position + DateUtil.getTodaysDayOfWeekAsIndex()) % 7);
        holder.txtDay.setText(dayName);


        Glide.with(context).load(ResourceUtil.getImageUrl(weatherItems.getWeather().get(0).getIcon())).into(holder.imgItem);

        holder.itemView.setOnClickListener(view -> weatherItemClickListener.onItemClicked(weatherItems));
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }

    class WeatherItemHolder extends RecyclerView.ViewHolder {

        TextView txtDay;
        TextView txtTemp;
        ImageView imgItem;

        WeatherItemHolder(@NonNull View itemView) {
            super(itemView);
            txtDay = itemView.findViewById(R.id.txtDay);
            txtTemp = itemView.findViewById(R.id.txtTemp);
            imgItem = itemView.findViewById(R.id.imgItem);
        }
    }


    public void setWeatherItems(List<WeatherItems> weatherItems) {
        this.weatherItems = weatherItems;
        notifyDataSetChanged();
    }

}
