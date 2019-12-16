package com.example.android.nasapod;

import com.example.android.nasapod.models.Apod;

import java.util.Date;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("/planetary/apod?api_key=TITYeODKg1B84Xo8Lcg6Bn0hJCHnGDtSEqrqt4e8")
    Call<Apod> getApods(
            @Query("date") List<String> date);
}
