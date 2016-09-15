package com.example.weatherapp;

import com.example.weatherapp.model.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by benba on 7/13/2016.
 */
public interface WeatherAPI {
    @GET("/data/2.5/weather?q=Winter%20Park,FL&appid=f8fdae74c29544baebdb927d392c5538")
    Call<Forecast> getWeatherData();

}
