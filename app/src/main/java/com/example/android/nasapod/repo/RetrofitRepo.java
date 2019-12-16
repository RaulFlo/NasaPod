package com.example.android.nasapod.repo;



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
    public Apod getPicOfTheDay() throws IOException {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Apod> call = service.getApods(null);

        Apod apod = call.execute().body();
        return apod;

    }

    @Override
    public List<Apod> getListPicOfTheDay() throws IOException {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        Call<Apod> call = service.getApods("2019-12-15");



        Apod apod = call.execute().body();
        List<Apod> listOfApods = new ArrayList<>();
        listOfApods.add(apod);

        return listOfApods;
    }


}
