package com.example.android.nasapod.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.android.nasapod.MyApp;
import com.example.android.nasapod.R;
import com.example.android.nasapod.SharedPref;
import com.example.android.nasapod.models.Apod;


public class DetailActivity extends AppCompatActivity {

    private static final String APOD = "Apod";
    private static final String APOD_TRANSITION_NAME = "ApodTransitionName";
    SharedPref sharedPref = new SharedPref(MyApp.getAppContext());


    public static Intent newIntent(Context context, Apod apod, View view) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(APOD, apod);
        intent.putExtra(APOD_TRANSITION_NAME, view.getTransitionName());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkForThemeChange();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        Apod apod = intent.getParcelableExtra(APOD);
        String apodTransitionName = intent.getStringExtra(APOD_TRANSITION_NAME);

        if (apod != null) {
            String imageExtra = apod.getApodImage();
            String titleExtra = apod.getApodName();
            String dateExtra = apod.getApodDate();

            //LINK TO VIEW
            ImageView imageView = findViewById(R.id.image_view_detail);
            // setting transition name to image detail view
            imageView.setTransitionName(apodTransitionName);
            TextView textViewTitle = findViewById(R.id.text_view_title_detail);
            TextView textViewDate = findViewById(R.id.text_view_date_detail);

            //link extras to the view
            Glide.with(this).load(imageExtra).fitCenter().into(imageView);
            textViewTitle.setText(titleExtra);
            textViewDate.setText(dateExtra);
        }
    }

    private void checkForThemeChange() {
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }
}



