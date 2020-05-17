package com.example.android.nasapod.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //start main activity
        startActivity(MainActivity.newIntent(this));

        //close splash activity
        finish();
    }
}
