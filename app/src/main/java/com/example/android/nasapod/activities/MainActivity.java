package com.example.android.nasapod.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.example.android.nasapod.ApodRepoAndDate;
import com.example.android.nasapod.DatePickerFragment;
import com.example.android.nasapod.GetCurrentDateMinus7DaysAsyncTask;
import com.example.android.nasapod.R;

import com.example.android.nasapod.SharedPref;
import com.example.android.nasapod.adapter.ApodAdapter;
import com.example.android.nasapod.models.Apod;
import com.example.android.nasapod.repo.ApodRepo;

import com.example.android.nasapod.repo.RetrofitRepo;


import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ApodAdapter.AdapterListener, DatePickerDialog.OnDateSetListener {

    private static final String TAG = "MainActivity";


    private RecyclerView mRecyclerView;
    private ApodAdapter mApodAdapter;

    SharedPref sharedPref;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPref = new SharedPref(this);
        if(sharedPref.loadNightModeState() == true){
            setTheme(R.style.darkTheme);
        }else {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link RecyclerView with xml RecyclerView in activity_main.xml
        mRecyclerView = findViewById(R.id.recycler_view);
        //if you know its not going to change in size
        mRecyclerView.setHasFixedSize(true);
        //set to LinearLayout default vertical
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        ApodRepo retrofitRepo = new RetrofitRepo();

        new GetCurrentDateMinus7DaysAsyncTask(new GetCurrentDateMinus7DaysAsyncTask.Listener() {
            @Override
            public void onApodsReturned(List<Apod> apods) {
                mApodAdapter = new ApodAdapter(apods, MainActivity.this);
                mRecyclerView.setAdapter(mApodAdapter);
            }
        }).execute(new ApodRepoAndDate(retrofitRepo, LocalDate.now().minusDays(1)));

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
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                return true;

            case R.id.theme_menu_item:
                startActivity(SetActivity.newIntent(MainActivity.this));
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        datePicker.setMaxDate(c.getTimeInMillis());
        //String pickedDate = DateFormat.getDateInstance().format(c.getTime());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String pikDate = sdf.format(c.getTime());
        Log.d("jxf", "onDateSet: " + pikDate);


        LocalDate chosenDate = LocalDate.parse(pikDate);
        Log.d("jxf", "parsed: " + chosenDate);


        ApodRepo retrofitRepo = new RetrofitRepo();


        new GetCurrentDateMinus7DaysAsyncTask(new GetCurrentDateMinus7DaysAsyncTask.Listener() {
            @Override
            public void onApodsReturned(List<Apod> apods) {
                mApodAdapter.updateData(apods);
            }
        }).execute(new ApodRepoAndDate(retrofitRepo, chosenDate));


    }

}
