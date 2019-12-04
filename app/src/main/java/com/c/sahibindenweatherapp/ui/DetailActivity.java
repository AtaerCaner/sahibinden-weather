package com.c.sahibindenweatherapp.ui;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;

import com.c.sahibindenweatherapp.BaseActivity;
import com.c.sahibindenweatherapp.R;
import com.c.sahibindenweatherapp.api.model.WeatherItems;

/**
 * Created by Ataer Caner on 2019-12-04.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public class DetailActivity extends BaseActivity {

    public static final String BUNDLE_DETAIL_ACTIVITY = "bundle_detail_activity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        WeatherItems weatherItems = getIntent().getParcelableExtra(BUNDLE_DETAIL_ACTIVITY);


        DetailFragment fragment = new DetailFragment();

        Bundle bundle = new Bundle();
        bundle.putParcelable(DetailFragment.BUNDLE_DETAIL_FRAGMENT, weatherItems);


        fragment.setArguments(bundle);



        getSupportFragmentManager().beginTransaction().add(R.id.frmDetail, fragment).commit();

    }
}
