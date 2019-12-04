package com.c.sahibindenweatherapp.ui;


import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.c.sahibindenweatherapp.BaseActivity;
import com.c.sahibindenweatherapp.R;
import com.c.sahibindenweatherapp.api.model.WeatherResponse;
import com.c.sahibindenweatherapp.manager.NetworkManager;
import com.c.sahibindenweatherapp.util.TempUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    public static final String EXTRA_COUNT = "extra_count";
    private String TAG = "";


    private TextView txtCurrentTemp, txtCurrentDay;
    private RecyclerView rcvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initViews();

        NetworkManager.getWeather("Istanbul").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();
                if (weatherResponse != null) {
                    Double day = weatherResponse.getList().get(0).getTemp().getDay();
                    txtCurrentTemp.setText(TempUtil.getCelcius(day));
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
    }

}
