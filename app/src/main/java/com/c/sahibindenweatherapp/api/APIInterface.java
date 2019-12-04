package com.c.sahibindenweatherapp.api;

import com.c.sahibindenweatherapp.api.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ataer Caner on 2019-12-03.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public interface APIInterface {

    @GET("daily")
    Call<WeatherResponse> getDailyForecast(@Query("q") String city,
                                           @Query("units") String unit,
                                           @Query("cnt") int count,
                                           @Query("appid") String apiKey);



}
