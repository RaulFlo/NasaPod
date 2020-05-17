package com.example.android.nasapod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private final SharedPreferences mySharedPref;

    public SharedPref (Context context){
        mySharedPref = context.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE);
    }

    //method will save the nightMode state : true or false
    @SuppressLint("ApplySharedPref")
    public void setNightModeState(Boolean state){
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.commit();
    }

    //method will load the night mode state
    public Boolean loadNightModeState(){
        return mySharedPref.getBoolean("NightMode",false);
    }
}
