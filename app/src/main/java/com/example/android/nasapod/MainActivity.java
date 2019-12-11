package com.example.android.nasapod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ApodAdapter.AdapterListener {

    public static final String EXTRA_IMAGE = "imageView";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DATE = "date";


    private RecyclerView mRecyclerView;
    private ApodAdapter mApodAdapter;
    private List<Apod> mApodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link RecyclerView with xml RecyclerView in activity_main.xml
        mRecyclerView = findViewById(R.id.recycler_view);
        //if you know its not going to change in size
        mRecyclerView.setHasFixedSize(true);
        //set to LinearLayout default vertical
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        ApodRepo fakeApodRepo = new FakeApodRepo();
        //create a new List that we fill with our data
        mApodList = fakeApodRepo.getListPicOfTheDay();



        mApodAdapter = new ApodAdapter(mApodList,this);
        mRecyclerView.setAdapter(mApodAdapter);
    }




    @Override
    public void onItemClick(Apod item) {
        Intent detailIntent = new Intent(this, DetailActivity.class);

        detailIntent.putExtra(EXTRA_IMAGE, item.getApodImage());
        detailIntent.putExtra(EXTRA_TITLE, item.getApodName());
        detailIntent.putExtra(EXTRA_DATE, item.getApodDate());

        startActivity(detailIntent);
    }
}
