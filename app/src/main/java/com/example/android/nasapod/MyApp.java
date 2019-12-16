package com.example.android.nasapod;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}