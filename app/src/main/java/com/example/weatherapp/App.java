package com.example.weatherapp;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by benba on 7/15/2016.
 */
public class App extends Application {
    private ObjectGraph objectGraph;
    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(
                new NetworkModule()
        );
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }


}
