package com.example.android.nasapod.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.palette.graphics.Palette;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.android.nasapod.ImageUrlDownloadUtil;
import com.example.android.nasapod.MyApp;
import com.example.android.nasapod.R;
import com.example.android.nasapod.SharedPref;
import com.example.android.nasapod.models.Apod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import static com.example.android.nasapod.ImageUrlDownloadUtil.DEFAULT_REQUEST_CODE_FOR_WRITE_PERMISSION;


public class DetailActivity extends AppCompatActivity {

    private static final String APOD = "Apod";
    private static final String APOD_TRANSITION_NAME = "ApodTransitionName";
    private final SharedPref sharedPref = new SharedPref(MyApp.getAppContext());
    private CoordinatorLayout coordinatorLayout;
    private String mWallpaperUrl;


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
        //back button
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Apod apod = intent.getParcelableExtra(APOD);
        String apodTransitionName = intent.getStringExtra(APOD_TRANSITION_NAME);

        if (apod != null) {
            mWallpaperUrl = apod.getApodImage();
            String imageExtra = apod.getApodImage();
            String titleExtra = apod.getApodName();
            String dateExtra = apod.getApodDate();
            String explanationExtra = apod.getApodExplanation();

            String getConvertedDate = null;
            try {
                getConvertedDate = convertDate(dateExtra);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //LINK TO VIEW
            final ImageView imageView = findViewById(R.id.image_view_detail);
            coordinatorLayout = findViewById(R.id.layout_detail_page);
            // setting transition name to image detail view
            imageView.setTransitionName(apodTransitionName);
            TextView textViewTitle = findViewById(R.id.text_view_title_detail);
            TextView textViewDate = findViewById(R.id.text_view_date_detail);
            TextView textViewExplanation = findViewById(R.id.text_view_explanation);

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
            textViewDate.setText(getConvertedDate);
            textViewExplanation.setText(explanationExtra);
        }
    }

    private String convertDate(String dateExtra) throws ParseException {
        @SuppressLint("SimpleDateFormat") DateFormat iFormatter = new SimpleDateFormat("yyyy-MM-dd");
        @SuppressLint("SimpleDateFormat") DateFormat oFormatter = new SimpleDateFormat("MM-dd-yyyy");
        return oFormatter.format(Objects.requireNonNull(iFormatter.parse(dateExtra)));
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

                coordinatorLayout.setBackgroundColor(dominantColor);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.save_menu_item:
                ImageUrlDownloadUtil.attemptToDownload(this, mWallpaperUrl, DEFAULT_REQUEST_CODE_FOR_WRITE_PERMISSION);
                Toast.makeText(this, "Image Saved", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}



