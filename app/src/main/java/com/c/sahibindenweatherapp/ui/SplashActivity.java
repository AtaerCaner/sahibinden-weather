package com.c.sahibindenweatherapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import com.c.sahibindenweatherapp.BaseActivity;
import com.c.sahibindenweatherapp.R;

/**
 * Created by Ataer Caner on 2019-12-03.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                intent.putExtra(MainActivity.EXTRA_COUNT, 15);
                startActivity(intent);
            }
        }, 1000);*/


        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }, 1000);


    }
}
