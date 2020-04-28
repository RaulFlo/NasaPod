package com.example.android.nasapod.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.android.nasapod.R;
import com.example.android.nasapod.SharedPref;

public class SetActivity extends AppCompatActivity {

    private Switch mSwitch;
    SharedPref sharedPref;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, SetActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true){
            setTheme(R.style.darkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);


        mSwitch = (Switch)findViewById(R.id.switch_theme);

        if(sharedPref.loadNightModeState() == true){
            mSwitch.setChecked(true);
        }

        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    sharedPref.setNightModeState(true);
                    restartApp();
                }else {
                    sharedPref.setNightModeState(false);
                    restartApp();
                }
            }
        });
    }

    private void restartApp() {
        Intent i = new Intent(getApplicationContext(), SetActivity.class);
        startActivity(i);
        finish();
    }
}
