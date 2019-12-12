package com.c.sahibindenweatherapp.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


/**
 * Created by Ataer Caner on 2019-12-12.
Copyright (c) 2019 sahibinden. All rights reserved.
 *
 */


fun String.toImageUrl(): String {
    return "http://openweathermap.org/img/wn/$this@2x.png"
}

fun FragmentManager.addFragment(containerId: Int, fragment: Fragment) {
    this.beginTransaction().add(containerId,fragment).commit()
}

operator fun FragmentManager.minus(popCount: Int) {
    for (i in 0 until popCount) {
        this.popBackStack()
    }

}


infix fun Int.days(string: String) {

}