package com.example.android.nasapod.activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.nasapod.ApodRepoAndDate;
import com.example.android.nasapod.GetListPicOfTheDayAsyncTask;
import com.example.android.nasapod.MyApp;
import com.example.android.nasapod.R;
import com.example.android.nasapod.SharedPref;
import com.example.android.nasapod.adapter.ApodAdapter;
import com.example.android.nasapod.models.Apod;
import com.example.android.nasapod.repo.ApodRepo;
import com.example.android.nasapod.repo.RetrofitRepo;
import com.leavjenn.smoothdaterangepicker.date.SmoothDateRangePickerFragment;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ApodAdapter.AdapterListener {

    private ApodAdapter mApodAdapter;
    private static final int SHOW_YEARS = 5;
    private static final int FROM_DAY_REQUESTED = 7;
    private static final int TO_DAY_REQUESTED = 1;
    private String rangeDate;
    private boolean isRangeSelected;
    private final SharedPref sharedPref = new SharedPref(MyApp.getAppContext());
    private ProgressBar mProgressBar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStart() {
        checkForThemeChange();
        super.onStart();
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        checkForThemeChange();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar = findViewById(R.id.main_progress_bar);

        ApodRepo retrofitRepo = new RetrofitRepo();
        //link RecyclerView with xml RecyclerView in activity_main.xml
        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);
        //if you know its not going to change in size
        mRecyclerView.setHasFixedSize(true);
        //set to LinearLayout default vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mApodAdapter = new ApodAdapter(new ArrayList<Apod>(), MainActivity.this);
        mRecyclerView.setAdapter(mApodAdapter);

        mProgressBar.setVisibility(View.VISIBLE);
         new GetListPicOfTheDayAsyncTask(new GetListPicOfTheDayAsyncTask.Listener() {
            @Override
            public void onApodsReturned(List<Apod> apods) {
                mApodAdapter.updateData(apods);
                mProgressBar.setProgress(100);
                mProgressBar.setVisibility(View.GONE);
            }
        }).execute(new ApodRepoAndDate(retrofitRepo,
                LocalDate.now().minusDays(FROM_DAY_REQUESTED), LocalDate.now().minusDays(TO_DAY_REQUESTED)));



    }


    @Override
    public void onItemClick(Apod apod, View view) {
        Intent detailIntent = DetailActivity.newIntent(this, apod, view);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
                MainActivity.this, view, view.getTransitionName());
        startActivity(detailIntent, transitionActivityOptions.toBundle());
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
                createDateRangePicker();
                return true;

            case R.id.theme_menu_item:
                startActivity(SettingsActivity.newIntent(MainActivity.this));

            case R.id.about_menu_item:
                startActivity(AboutActivity.newIntent(MainActivity.this));

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void checkForThemeChange() {
        if (sharedPref.loadNightModeState()) {
            setTheme(R.style.darkTheme);
        } else {
            setTheme(R.style.AppTheme);
        }
    }

    //method to create DateRanger picker using SmoothDateRangePicker
    private void createDateRangePicker() {
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

                        mProgressBar.setVisibility(View.VISIBLE);

                        isRangeSelected = true;
                        invalidateOptionsMenu();
                        LocalDate sDay = convertTimeToJodaTime(yearStart, monthStart, dayStart);
                        LocalDate eDay = convertTimeToJodaTime(yearEnd, monthEnd, dayEnd);

                        rangeDate = "[" + (monthStart + 1) + "/" + dayStart + "/" + yearStart +
                                "-" + (monthEnd + 1) + "/" + dayEnd + "/" + yearEnd + "]";

                        ApodRepo retrofitRepo = new RetrofitRepo();
                        new GetListPicOfTheDayAsyncTask(new GetListPicOfTheDayAsyncTask.Listener() {
                            @Override
                            public void onApodsReturned(List<Apod> apods) {
                                mApodAdapter.updateData(apods);
                                mProgressBar.setProgress(100);
                                mProgressBar.setVisibility(View.GONE);
                            }
                        }).execute(new ApodRepoAndDate(retrofitRepo, sDay, eDay));

                    }
                }, year, month, day);
        smoothDateRangePickerFragment.setMaxDate(Calendar.getInstance());
        //set the max years back
        c.add(Calendar.YEAR, -SHOW_YEARS);
        smoothDateRangePickerFragment.setMinDate(c);
        //show fragment
        smoothDateRangePickerFragment.show(getFragmentManager(), "smoothDateRangePicker");



    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private LocalDate convertTimeToJodaTime(int firstDate, int secondDate, int thirdDated) {

        //convert int days to LocalDate add one on months since its 0-11
        java.time.LocalDate parsedDate = java.time.LocalDate.of(firstDate, secondDate + 1, thirdDated);
        //convert LocalDate to string
        String stringDate = String.valueOf(parsedDate);
        //return parsed String to Locale Date joda
        return LocalDate.parse(stringDate);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.range_date_title);
        if (isRangeSelected) {
            item.setVisible(true);
            item.setTitle(rangeDate);
        }

        return super.onPrepareOptionsMenu(menu);
    }



}
