package com.example.android.nasapod;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;

import com.example.android.nasapod.activities.MainActivity;

public class ThemeDialog extends DialogFragment {

        private Button mainMenuLightButton;
        private Button mainMenuDarkButton;

        //to keep theme color after closing app
        public static final String KEY_ISNIGHTMODE = "isNightMode";

    SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(MyApp.getAppContext());

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.theme_dialog, null);

        builder.setView(view);

        mainMenuLightButton = view.findViewById(R.id.light_button);
        mainMenuDarkButton = view.findViewById(R.id.dark_button);

        checkNightModeActivated();

        mainMenuLightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveNightModeState(false);
            }
        });

        mainMenuDarkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveNightModeState(true);
            }
        });

        return builder.create();
    }

    private void saveNightModeState(boolean nightMode){
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(KEY_ISNIGHTMODE, nightMode);
        editor.apply();
    }

    public void checkNightModeActivated(){
        if(mPrefs.getBoolean(KEY_ISNIGHTMODE, false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


}
