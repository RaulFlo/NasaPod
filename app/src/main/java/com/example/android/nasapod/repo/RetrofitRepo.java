package com.example.android.nasapod.repo;


import android.util.Log;

import com.example.android.nasapod.GetDataService;
import com.example.android.nasapod.RetrofitClientInstance;
import com.example.android.nasapod.models.Apod;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitRepo implements ApodRepo {

    @Override
    public Apod getPicOfTheDay() {
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
    public List<Apod> getListPicOfTheDay() {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Apod> call = service.getApods("2019-12-15");
        Call<Apod> call2 = service.getApods("2019-12-14");
        Call<Apod> call3 = service.getApods("2019-12-13");

        Apod apod = null;
        try {
            apod = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("===============", "getListPicOfTheDay: ");
        }

        Apod apod2 = null;
        try {
            apod2 = call2.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("===============", "getListPicOfTheDay: ");
        }

        Apod apod3 = null;
        try {
            apod3 = call3.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("===============", "getListPicOfTheDay: ");
        }

        List<Apod> listOfApods = new ArrayList<>();

        if (apod != null) {
            listOfApods.add(apod);
        }
        if (apod2 != null) {
            listOfApods.add(apod2);
        }
        if (apod3 != null) {
            listOfApods.add(apod3);
        }


        return listOfApods;
    }


}
