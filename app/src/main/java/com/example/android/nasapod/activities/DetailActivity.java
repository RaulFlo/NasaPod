package com.example.android.nasapod.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.android.nasapod.MyApp;
import com.example.android.nasapod.R;
import com.example.android.nasapod.SharedPref;
import com.example.android.nasapod.models.Apod;


public class DetailActivity extends AppCompatActivity {

    private static final String APOD = "Apod";
    private static final String APOD_TRANSITION_NAME = "ApodTransitionName";
    SharedPref sharedPref = new SharedPref(MyApp.getAppContext());
    private LinearLayout linearLayout;


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
            final ImageView imageView = findViewById(R.id.image_view_detail);
            linearLayout = findViewById(R.id.linear_layout_detail_page);
            // setting transition name to image detail view
            imageView.setTransitionName(apodTransitionName);
            TextView textViewTitle = findViewById(R.id.text_view_title_detail);
            TextView textViewDate = findViewById(R.id.text_view_date_detail);

            //link extras to the view
            //Glide.with(this).load(imageExtra).fitCenter().into(imageView);
            Glide.with(this)
                    .asBitmap()
                    .load(imageExtra)
                    .fitCenter()
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                            // you now have a bitmap
                            imageView.setImageBitmap(resource);
                            createPaletteAsync(resource);
                        }

                        @Override
                        public void onLoadCleared(Drawable placeholder) {

                        }
                    });

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

    // Generate palette asynchronously and use it on a different, thread using onGenerated()
    private void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onGenerated(Palette palette) {
                // Use generated instance
                int color = MyApp.getAppContext().getResources().getColor(R.color.colorPrimaryDark);
                int dominantColor = palette.getDominantColor(color);

                linearLayout.setBackgroundColor(dominantColor);

            }
        });
    }
}



