package com.example.android.nasapod;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import net.danlew.android.joda.JodaTimeAndroid;

public class MyApp extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        MyApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApp.context;
    }
}
