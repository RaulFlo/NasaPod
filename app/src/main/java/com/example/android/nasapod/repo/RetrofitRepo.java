package com.example.android.nasapod.repo;


import android.util.Log;
import com.example.android.nasapod.internal.GetDataService;
import com.example.android.nasapod.internal.RetrofitClientInstance;
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
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public List<Apod> getListPicOfTheDay(LocalDate start, LocalDate end) {

        List<Apod> listOfApods = new ArrayList<>();

        for (LocalDate currentDate = start; currentDate.isBefore(end); currentDate = currentDate.plusDays(1)) {
            Apod apod = makeCall(currentDate);
            if (apod != null) {
                listOfApods.add(apod);
            }
        }

        return listOfApods;
    }



    private Apod makeCall(LocalDate date) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(DATE_FORMAT);

        Call<Apod> call = service.getApods(date.toString(dateTimeFormatter));
        Apod apod = null;
        try {
            Response<Apod> response = call.execute();
            if (response.isSuccessful()) {
                apod = response.body();
            } else {
                Log.d("RetrofitRepo", "Response not successful");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.d("RetrofitRepo", "Not successful" + e);
        }

        return apod;
    }
}
