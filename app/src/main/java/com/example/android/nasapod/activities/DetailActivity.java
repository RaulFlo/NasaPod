package com.example.android.nasapod.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.nasapod.R;
import com.example.android.nasapod.models.Apod;


public class DetailActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, DetailActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        Intent intent = getIntent();
        Apod apod = intent.getParcelableExtra("Apod");

        String imageExtra = apod.getApodImage();
        String titleExtra = apod.getApodName();
        String dateExtra = apod.getApodDate();

        //LINK TO VIEW
        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewTitle = findViewById(R.id.text_view_title_detail);
        TextView textViewDate = findViewById(R.id.text_view_date_detail);

        //link extras to the view
        Glide.with(this).load(imageExtra).fitCenter().into(imageView);
        textViewTitle.setText(titleExtra);
        textViewDate.setText(dateExtra);

    }
}



