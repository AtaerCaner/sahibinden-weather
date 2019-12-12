package com.c.sahibindenweatherapp.ui


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.c.sahibindenweatherapp.BaseActivity
import com.c.sahibindenweatherapp.R
import com.c.sahibindenweatherapp.adapter.WeatherItemAdapter
import com.c.sahibindenweatherapp.api.model.WeatherItems
import com.c.sahibindenweatherapp.api.model.WeatherResponse
import com.c.sahibindenweatherapp.ext.toImageUrl
import com.c.sahibindenweatherapp.manager.NetworkManager
import com.c.sahibindenweatherapp.util.DateUtil
import com.c.sahibindenweatherapp.util.ResourceUtil
import com.c.sahibindenweatherapp.util.TempUtil
import kotlinx.android.synthetic.main.activity_main.*

import java.io.IOException

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity(), AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        fetchWeatherData(country[p2])
    }

    private val TAG = ""


    private var weatherItemAdapter: WeatherItemAdapter? = null

    val country = arrayOf("Istanbul","Bursa","Izmir")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        weatherItemAdapter = WeatherItemAdapter(this) { weatherItems ->

            val intent = Intent(this@MainActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.BUNDLE_DETAIL_ACTIVITY, weatherItems)
            startActivity(intent)
        }



        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, country)


        spinner.adapter = arrayAdapter
        spinner.onItemSelectedListener = this




        weatherItemAdapter!!.notifyDataSetChanged()

        initViews()

        fetchWeatherData("İstanbul")


        btnOk.setOnClickListener {


            if (edtCity!!.text.toString() != "") {
                hideKeyboard()
                progress!!.visibility = View.VISIBLE
                fetchWeatherData(edtCity!!.text.toString())
            } else {
                edtCity!!.error = "Şehir alanı doldurulmalıdır"
            }

        }


    }


    private fun fetchWeatherData(city: String) {

        NetworkManager.getWeather(city).enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                val weatherResponse = response.body()

                progress!!.visibility = View.GONE
                if (weatherResponse != null) {
                    val weatherItems = weatherResponse.weatherItems[0]

                    val day = weatherItems.temp.day
                    txtCurrentTemp!!.text = TempUtil.getCelcius(day!!)
                    txtCurrentDay!!.text = DateUtil.getTodayAsName()

                    Glide.with(this@MainActivity).load(weatherItems.weather[0].icon.toImageUrl()).into(imgCurrent!!)


                    weatherItemAdapter!!.setWeatherItems(weatherResponse.weatherItems)
                } else {

                    try {
                        showError(response.errorBody()!!.string())
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }

            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                progress!!.visibility = View.GONE
                showError(t.message)
            }
        })
    }


    private fun showError(message: String?) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    private fun initViews() {


        rcvItems!!.adapter = weatherItemAdapter
        rcvItems!!.layoutManager = LinearLayoutManager(this)
    }


    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm?.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }



    companion object {

        val EXTRA_COUNT = "extra_count"
    }
}
