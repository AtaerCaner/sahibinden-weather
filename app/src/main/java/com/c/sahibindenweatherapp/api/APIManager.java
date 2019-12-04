package com.c.sahibindenweatherapp.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ataer Caner on 2019-12-03.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public class APIManager {

    private static Retrofit retrofit;


    public static Retrofit getClient(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/forecast/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();


        return retrofit;
    }


}
