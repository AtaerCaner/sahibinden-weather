package com.c.sahibindenweatherapp.ui;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    public static final String EXTRA_COUNT = "extra_count";
    private String TAG = "";


    private TextView txtCurrentTemp, txtCurrentDay;
    private EditText edtCity;
    private Button btnOk;
    private ProgressBar progress;
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

        fetchWeatherData("İstanbul");


        btnOk.setOnClickListener(view -> {
            if (!edtCity.getText().toString().equals("")) {
                progress.setVisibility(View.VISIBLE);
                fetchWeatherData(edtCity.getText().toString());
            } else {
                edtCity.setError("Şehir alanı doldurulmalıdır");
            }

        });


    }



    private void fetchWeatherData(String city) {
        NetworkManager.getWeather(city).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                WeatherResponse weatherResponse = response.body();

                progress.setVisibility(View.GONE);
                if (weatherResponse != null) {
                    Double day = weatherResponse.getWeatherItems().get(0).getTemp().getDay();
                    txtCurrentTemp.setText(TempUtil.getCelcius(day));
                    txtCurrentDay.setText(DateUtil.getTodayAsName());


                    weatherItemAdapter.setWeatherItems(weatherResponse.getWeatherItems());
                } else {

                    try {
                        showError(response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                progress.setVisibility(View.GONE);
                showError(t.getMessage());
            }
        });
    }


    private void showError(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private void initViews() {
        txtCurrentTemp = findViewById(R.id.txtCurrentTemp);
        txtCurrentDay = findViewById(R.id.txtCurrentDay);
        rcvItems = findViewById(R.id.rcvItems);
        edtCity = findViewById(R.id.edtCity);
        btnOk = findViewById(R.id.btnOk);
        progress = findViewById(R.id.progress);

        rcvItems.setAdapter(weatherItemAdapter);
        rcvItems.setLayoutManager(new LinearLayoutManager(this));
    }

}
