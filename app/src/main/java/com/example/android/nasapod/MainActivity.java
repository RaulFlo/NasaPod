package com.example.android.nasapod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    private RecyclerView mRecyclerView;
    private ApodAdapter mApodAdapter;
    private ArrayList<Apod> mApodList;


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


        //create a new ArrayList that we fill with our data
        mApodList = new ArrayList<>();



        FakeApodRepo fakeApodRepo = new FakeApodRepo();

        ArrayList<Apod> mApodList= (ArrayList) fakeApodRepo.getListPicOfTheDay();


        mApodAdapter = new ApodAdapter(MainActivity.this, mApodList);
        mRecyclerView.setAdapter(mApodAdapter);
    }
}
