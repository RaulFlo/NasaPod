package com.example.android.nasapod.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.nasapod.GetListPicOfTheDayAsyncTask;
import com.example.android.nasapod.R;
import com.example.android.nasapod.activities.DetailActivity;
import com.example.android.nasapod.adapter.ApodAdapter;
import com.example.android.nasapod.models.Apod;
import com.example.android.nasapod.repo.ApodRepo;
import com.example.android.nasapod.repo.FakeApodRepo;
import com.example.android.nasapod.repo.RetrofitRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ApodAdapter.AdapterListener {

    public static final String EXTRA_IMAGE = "image";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_DATE = "date";

    private static final String TAG = "MainActivity";


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

//        //fake Apod repo get data
//        ApodRepo fakeApodRepo = new FakeApodRepo();
//        //create a new List that we fill with our data
//        mApodList = fakeApodRepo.getListPicOfTheDay();

        ApodRepo retrofitRepo = new RetrofitRepo();

        new GetListPicOfTheDayAsyncTask(new GetListPicOfTheDayAsyncTask.Listener() {
            @Override
            public void onApodsReturned(List<Apod> apods) {
                mApodList = apods;
                mApodAdapter = new ApodAdapter(mApodList,MainActivity.this);
                mRecyclerView.setAdapter(mApodAdapter);
            }
        }).execute(retrofitRepo);



//        mApodAdapter = new ApodAdapter(mApodList, this);
//        mRecyclerView.setAdapter(mApodAdapter);
    }


    @Override
    public void onItemClick(Apod apod) {
        Intent detailIntent = new Intent(this, DetailActivity.class);

        detailIntent.putExtra(EXTRA_IMAGE, apod.getApodImage());
        detailIntent.putExtra(EXTRA_TITLE, apod.getApodName());
        detailIntent.putExtra(EXTRA_DATE, apod.getApodDate());

        startActivity(detailIntent);
    }
}
