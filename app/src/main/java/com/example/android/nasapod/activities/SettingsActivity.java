package com.example.android.nasapod.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.android.nasapod.MyApp;
import com.example.android.nasapod.R;
import com.example.android.nasapod.SharedPref;

public class SettingsActivity extends AppCompatActivity {

    SharedPref sharedPref = new SharedPref(MyApp.getAppContext());

    public static Intent newIntent(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkForThemeChange();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        Switch mSwitch = findViewById(R.id.switch_theme);
        mSwitch.setChecked(sharedPref.loadNightModeState());
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                sharedPref.setNightModeState(isChecked);
                restartApp();
            }
        });
    }

    private void restartApp() {
        Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(i);
        finish();
    }

    private void checkForThemeChange() {
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }
}
