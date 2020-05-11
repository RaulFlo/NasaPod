package com.example.android.nasapod;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences mySharedPref;

    public SharedPref (Context context){
        mySharedPref = context.getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE);
    }

    //method will save the nightMode state : true or false
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
