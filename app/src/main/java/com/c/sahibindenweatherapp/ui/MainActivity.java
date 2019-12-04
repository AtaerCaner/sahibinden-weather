package com.c.sahibindenweatherapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.c.sahibindenweatherapp.BaseActivity;
import com.c.sahibindenweatherapp.R;
import com.c.sahibindenweatherapp.adapter.WeatherItemAdapter;
import com.c.sahibindenweatherapp.api.model.WeatherItems;
import com.c.sahibindenweatherapp.api.model.WeatherResponse;
import com.c.sahibindenweatherapp.manager.NetworkManager;
import com.c.sahibindenweatherapp.util.DateUtil;
import com.c.sahibindenweatherapp.util.TempUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    public static final String EXTRA_COUNT = "extra_count";
    private String TAG = "";


    private TextView txtCurrentTemp, txtCurrentDay;
    private RecyclerView rcvItems;
    private WeatherItemAdapter weatherItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherItemAdapter = new WeatherItemAdapter(weatherItems -> {

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra(DetailActivity.BUNDLE_DETAIL_ACTIVITY, weatherItems);
            startActivity(intent);
        });


        initViews();

        NetworkManager.getWeather("Istanbul").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();
                if (weatherResponse != null) {
                    Double day = weatherResponse.getWeatherItems().get(0).getTemp().getDay();
                    txtCurrentTemp.setText(TempUtil.getCelcius(day));
                    txtCurrentDay.setText(DateUtil.getTodayAsName());


                    weatherItemAdapter.setWeatherItems(weatherResponse.getWeatherItems());

                }

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: ");
            }
        });
    }


    private void initViews() {
        txtCurrentTemp = findViewById(R.id.txtCurrentTemp);
        txtCurrentDay = findViewById(R.id.txtCurrentDay);
        rcvItems = findViewById(R.id.rcvItems);

        rcvItems.setAdapter(weatherItemAdapter);
        rcvItems.setLayoutManager(new LinearLayoutManager(this));
    }

}
