package com.c.sahibindenweatherapp.manager;

import com.c.sahibindenweatherapp.api.APIInterface;
import com.c.sahibindenweatherapp.api.APIManager;
import com.c.sahibindenweatherapp.api.model.WeatherResponse;

import retrofit2.Call;

/**
 * Created by Ataer Caner on 2019-12-03.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public class NetworkManager {
    private static APIInterface apiInterface;
    private static String API_KEY = "33e39f544e0da25b3687090c132dc0a9";
    private static final String METRIC = "metric";


    public static APIInterface getApiInterface() {
        if (apiInterface == null) {
            apiInterface = APIManager.getClient().create(APIInterface.class);
        }

        return apiInterface;
    }

    public static Call<WeatherResponse> getWeather(String city) {
        return getApiInterface().getDailyForecast(city, METRIC,10,API_KEY);
    }



}
