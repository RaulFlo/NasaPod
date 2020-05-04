package com.example.android.nasapod;

import android.os.AsyncTask;

import com.example.android.nasapod.models.Apod;
import com.example.android.nasapod.repo.ApodRepo;


import org.joda.time.LocalDate;

import java.util.List;

public class GetListPicOfTheDayAsyncTask extends AsyncTask<ApodRepoAndDate, Integer, List<Apod>> {

    public interface Listener {
        void onApodsReturned(List<Apod> apods);
    }

    private Listener mListener;


    public GetListPicOfTheDayAsyncTask(Listener listener) {
        mListener = listener;
    }


    @Override
    protected List<Apod> doInBackground(ApodRepoAndDate... apodRepoAndDates) {
        ApodRepoAndDate apodRepoAndDate = apodRepoAndDates[0];
        LocalDate dateRequested = apodRepoAndDate.mStartDate;
        LocalDate dateRequested2 = apodRepoAndDate.mEndDate.plusDays(1);

        return apodRepoAndDate.mApodRepo.getListPicOfTheDay(dateRequested,dateRequested2);
    }


    @Override
    protected void onPostExecute(List<Apod> apods) {
        super.onPostExecute(apods);
        mListener.onApodsReturned(apods);
    }
}
