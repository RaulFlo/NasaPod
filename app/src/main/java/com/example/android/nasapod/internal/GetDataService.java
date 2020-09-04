package com.example.android.nasapod.internal;

import com.example.android.nasapod.models.Apod;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("/planetary/apod?api_key=TITYeODKg1B84Xo8Lcg6Bn0hJCHnGDtSEqrqt4e8")
    Call<Apod> getApods(
            @Query("date") String date);
}
