package com.c.sahibindenweatherapp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.c.sahibindenweatherapp.R;
import com.c.sahibindenweatherapp.api.model.WeatherItems;
import com.c.sahibindenweatherapp.util.TempUtil;

/**
 * Created by Ataer Caner on 2019-12-04.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public class DetailFragment extends Fragment {

    private TextView txtSelectedStatus;
    private TextView txtSelectedTemp;

    public static final String BUNDLE_DETAIL_FRAGMENT = "bundle_detail_fragment";
    private WeatherItems weatherItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherItems = getArguments().getParcelable(BUNDLE_DETAIL_FRAGMENT);

        initViews(view);

        txtSelectedStatus.setText(weatherItems.getWeather().get(0).getDescription());

        
        txtSelectedTemp.setText(TempUtil.getCelcius(weatherItems.getTemp().getDay()));

    }


    private void initViews(View view) {
        txtSelectedStatus = view.findViewById(R.id.txtSelectedStatus);
        txtSelectedTemp = view.findViewById(R.id.txtSelectedTemp);
    }
}
