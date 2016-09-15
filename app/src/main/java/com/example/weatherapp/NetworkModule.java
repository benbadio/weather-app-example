package com.example.weatherapp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by benba on 7/15/2016.
 */

@Module(injects = {
        MainActivity.class
})
public class NetworkModule {
    public static final String ENDPOINT = "http://api.openweathermap.org";

    @Provides @Singleton
    Retrofit provideRetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    @Provides @Singleton
    WeatherAPI provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherAPI.class);
    }
}
