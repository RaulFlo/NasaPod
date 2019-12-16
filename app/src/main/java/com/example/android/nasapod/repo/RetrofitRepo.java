package com.example.android.nasapod.repo;


import android.util.Log;

import com.example.android.nasapod.GetDataService;
import com.example.android.nasapod.RetrofitClientInstance;
import com.example.android.nasapod.models.Apod;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;


public class RetrofitRepo implements ApodRepo {

    @Override
    public Apod getPicOfTheDay(LocalDate date) {


            GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
            Call<Apod> call = service.getApods(null);

            Apod apod = null;
            try {
                apod = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return apod;
    }



    @Override
    public List<Apod> getListPicOfTheDay(LocalDate start, LocalDate end) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");


        Call<Apod>  call = service.getApods(start.toString(dtf));

        Apod apod = null;
        try {
            Response<Apod> response = call.execute();
            if(response.isSuccessful()) {
                apod = response.body();
            } else {
                Log.d("RetrofitRepo", "Response not successful");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("RetrofitRepo", "Not successful" + e);
        }


        List<Apod> listOfApods = new ArrayList<>();

        if (apod != null) {
            listOfApods.add(apod);
        }

        return listOfApods;
    }
}
