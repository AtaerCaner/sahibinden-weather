package com.c.sahibindenweatherapp.util;

import java.util.Calendar;

/**
 * Created by Ataer Caner on 2019-12-04.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

public class DateUtil {
    private static String[] days = {"Pazartesi", "Salı","Carsamba","Persembe","Cuma","Cumartesi","Pazar"};


    public static String getTodayAsName() {
        return getGivenDayOfWeekAsName(getTodaysDayOfWeekAsIndex());
    }


    public static int getTodaysDayOfWeekAsIndex() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }


    public static String getGivenDayOfWeekAsName(int index) {
        return days[index];
    }
}
