package com.c.sahibindenweatherapp.ui

import android.os.Bundle
import android.os.Parcelable
import android.widget.FrameLayout

import com.bumptech.glide.Glide
import com.c.sahibindenweatherapp.BaseActivity
import com.c.sahibindenweatherapp.R
import com.c.sahibindenweatherapp.api.model.WeatherItems
import com.c.sahibindenweatherapp.ext.addFragment
import com.c.sahibindenweatherapp.ext.days
import com.c.sahibindenweatherapp.ext.minus

/**
 * Created by Ataer Caner on 2019-12-04.
 * Copyright (c) 2019 sahibinden. All rights reserved.
 */

class DetailActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val weatherItems = intent.getParcelableExtra<WeatherItems>(BUNDLE_DETAIL_ACTIVITY)


        val fragment = DetailFragment()

        val bundle = Bundle()
        bundle.putParcelable(DetailFragment.BUNDLE_DETAIL_FRAGMENT, weatherItems)


        fragment.arguments = bundle



//        supportFragmentManager.beginTransaction().add(R.id.frmDetail, fragment).commit()

        supportFragmentManager.addFragment(R.id.frmDetail,fragment)

        2.days("ago")


        2 days "ago"


    }

    companion object {

        val BUNDLE_DETAIL_ACTIVITY = "bundle_detail_activity"
    }
}
