package com.example.android.nasapod.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.nasapod.R;

import static com.example.android.nasapod.activities.MainActivity.EXTRA_IMAGE;
import static com.example.android.nasapod.activities.MainActivity.EXTRA_TITLE;
import static com.example.android.nasapod.activities.MainActivity.EXTRA_DATE;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        String imageExtra = intent.getStringExtra(EXTRA_IMAGE);
        String titleExtra = intent.getStringExtra(EXTRA_TITLE);
        String dateExtra = intent.getStringExtra(EXTRA_DATE);

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



