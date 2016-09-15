package com.example.weatherapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.model.Forecast;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String ENDPOINT = "http://api.openweathermap.org";

    @BindView(R.id.temperature) TextView temperature;
    @BindView(R.id.weather_description) TextView description;
    @BindView(R.id.highTemp) TextView highTemp;
    @BindView(R.id.lowTemp) TextView lowTemp;
    @BindView(R.id.icon) ImageView weatherIcon;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Inject WeatherAPI api;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        App app = (App) getApplication();
        app.getObjectGraph().inject(this);

        requestData();

    }

    private void requestData() {

        Call<Forecast> call = api.getWeatherData();
        call.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();
                loadToolbarTitle(forecast);
                description.setText(forecast.weather.get(0).description);
                temperature.setText(asFahrenheit(forecast.main.temp));
                lowTemp.setText(asFahrenheit(forecast.main.tempMin));
                highTemp.setText(asFahrenheit(forecast.main.tempMax));
                loadIcon(forecast.weather.get(0).icon);
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Weather data unavailable!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void loadToolbarTitle(Forecast forecast) {
        toolbar.setTitle(forecast.name);
        toolbar.setSubtitle(R.string.current_weather);
    }

    private void loadIcon(String icon) {
        switch (icon) {
            case "01d":
                weatherIcon.setImageResource(R.drawable.weather_01d);
                break;
            case "02d":
                weatherIcon.setImageResource(R.drawable.weather_02d);
                break;
            case "03d":
                weatherIcon.setImageResource(R.drawable.weather_03d);
                break;
            case "04d":
                weatherIcon.setImageResource(R.drawable.weather_04d);
                break;
            case "09d":
                weatherIcon.setImageResource(R.drawable.weather_09d);
                break;
            case "10d":
                weatherIcon.setImageResource(R.drawable.weather_10d);
                break;
            case "11d":
                weatherIcon.setImageResource(R.drawable.weather_11d);
                break;
            case "13d":
                weatherIcon.setImageResource(R.drawable.weather_13d);
                break;
            case "50d":
                weatherIcon.setImageResource(R.drawable.weather_50d);
                break;
            case "01n":
                weatherIcon.setImageResource(R.drawable.weather_01n);
                break;
            case "02n":
                weatherIcon.setImageResource(R.drawable.weather_02n);
                break;
            case "03n":
                weatherIcon.setImageResource(R.drawable.weather_03n);
                break;
            case "04n":
                weatherIcon.setImageResource(R.drawable.weather_04n);
                break;
            case "09n":
                weatherIcon.setImageResource(R.drawable.weather_09n);
                break;
            case "10n":
                weatherIcon.setImageResource(R.drawable.weather_10n);
                break;
            case "11n":
                weatherIcon.setImageResource(R.drawable.weather_11n);
                break;
            case "13n":
                weatherIcon.setImageResource(R.drawable.weather_13n);
                break;
            case "50n":
                weatherIcon.setImageResource(R.drawable.weather_50n);
                break;
        }

    }

    private String asFahrenheit(Double temp) {
        // F = 9/5(K - 273) + 32
        Long fahr = Math.round(((temp - 273.15)*9/5) + 32);
        return fahr.toString();
    }


}
