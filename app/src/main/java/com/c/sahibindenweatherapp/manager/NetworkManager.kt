package com.c.sahibindenweatherapp.manager

import com.c.sahibindenweatherapp.api.APIInterface
import com.c.sahibindenweatherapp.api.APIManager
import com.c.sahibindenweatherapp.api.model.WeatherResponse

import retrofit2.Call

/**
 * Created by Ataer Caner on 2019-12-03.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

object NetworkManager {
    private var apiInterface: APIInterface? = null
    private val API_KEY = "33e39f544e0da25b3687090c132dc0a9"
    private val METRIC = "metric"


    fun getApiInterface(): APIInterface {
        if (apiInterface == null) {
            apiInterface = APIManager.getClient().create(APIInterface::class.java)
        }

        return apiInterface!!
    }


    fun getWeather(city: String): Call<WeatherResponse> {
        return getApiInterface().getDailyForecast(city, METRIC, 10, API_KEY)
    }


}
