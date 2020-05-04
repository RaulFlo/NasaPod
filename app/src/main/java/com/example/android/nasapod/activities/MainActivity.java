package com.example.android.nasapod.activities;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.android.nasapod.ApodRepoAndDate;
import com.example.android.nasapod.GetListPicOfTheDayAsyncTask;
import com.example.android.nasapod.R;

import com.example.android.nasapod.SharedPref;
import com.example.android.nasapod.adapter.ApodAdapter;
import com.example.android.nasapod.models.Apod;
import com.example.android.nasapod.repo.ApodRepo;

import com.example.android.nasapod.repo.RetrofitRepo;
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;


import java.util.Calendar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ApodAdapter.AdapterListener {

    private static final String TAG = "MainActivity";


    private RecyclerView mRecyclerView;
    private ApodAdapter mApodAdapter;

    SharedPref sharedPref;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        checkForThemeChange();
        super.onStart();

        //link RecyclerView with xml RecyclerView in activity_main.xml
        mRecyclerView = findViewById(R.id.recycler_view);
        //if you know its not going to change in size
        mRecyclerView.setHasFixedSize(true);
        //set to LinearLayout default vertical
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        ApodRepo retrofitRepo = new RetrofitRepo();


        new GetListPicOfTheDayAsyncTask(new GetListPicOfTheDayAsyncTask.Listener() {
            @Override
            public void onApodsReturned(List<Apod> apods) {
                mApodAdapter = new ApodAdapter(apods, MainActivity.this);
                mRecyclerView.setAdapter(mApodAdapter);
            }
        }).execute(new ApodRepoAndDate(retrofitRepo,
                org.joda.time.LocalDate.now().minusDays(14),org.joda.time.LocalDate.now().minusDays(1)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkForThemeChange();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    public void onItemClick(Apod apod) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        detailIntent.putExtra("Apod", apod);
        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.date_picker_menu_item:

                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                SmoothDateRangePickerFragment smoothDateRangePickerFragment = SmoothDateRangePickerFragment.newInstance(
                        new SmoothDateRangePickerFragment.OnDateRangeSetListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onDateRangeSet(SmoothDateRangePickerFragment view,
                                                       int yearStart, int monthStart,
                                                       int dayStart, int yearEnd,
                                                       int monthEnd, int dayEnd) {
                                // grab the date range, do what you want

                                java.time.LocalDate startDay = java.time.LocalDate.of(yearStart, monthStart, dayStart);
                                java.time.LocalDate endDay = java.time.LocalDate.of(yearEnd, monthEnd, dayEnd);

                                String startingDay = String.valueOf(startDay);
                                String endingDay = String.valueOf(endDay);

                                org.joda.time.LocalDate sDay = org.joda.time.LocalDate.parse(startingDay);
                                org.joda.time.LocalDate eDay = org.joda.time.LocalDate.parse(endingDay);


                                ApodRepo retrofitRepo = new RetrofitRepo();
                                new GetListPicOfTheDayAsyncTask(new GetListPicOfTheDayAsyncTask.Listener() {
                                    @Override
                                    public void onApodsReturned(List<Apod> apods) {
                                        mApodAdapter.updateData(apods);
                                    }
                                }).execute(new ApodRepoAndDate(retrofitRepo, sDay, eDay));

                            }
                        }, year, month, day);
                smoothDateRangePickerFragment.setMaxDate(Calendar.getInstance());
                c.add(Calendar.YEAR, -5);
                smoothDateRangePickerFragment.setMinDate(c);

                smoothDateRangePickerFragment.show(getFragmentManager(), "smoothDateRangePicker");


                return true;

            case R.id.theme_menu_item:
                startActivity(SetActivity.newIntent(MainActivity.this));
            default:
                return super.onOptionsItemSelected(item);
        }


    }


    public void checkForThemeChange() {
        sharedPref = new SharedPref(this);
        if (sharedPref.loadNightModeState() == true) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }



}
