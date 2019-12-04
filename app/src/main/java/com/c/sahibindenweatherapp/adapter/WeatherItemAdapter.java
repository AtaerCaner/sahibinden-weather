package com.c.sahibindenweatherapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Ataer Caner on 2019-12-04.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.WeatherItemHolder>{


    @NonNull
    @Override
    public WeatherItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherItemHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class WeatherItemHolder extends RecyclerView.ViewHolder {


        public WeatherItemHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
