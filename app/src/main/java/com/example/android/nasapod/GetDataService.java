package com.example.android.nasapod;

import com.example.android.nasapod.models.Apod;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("/planetary/apod")
    Call<Apod> getApods(
            @Query("date") String date);
}
