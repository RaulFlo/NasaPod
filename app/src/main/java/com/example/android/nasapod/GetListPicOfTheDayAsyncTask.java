package com.example.android.nasapod;

import android.os.AsyncTask;

import com.example.android.nasapod.models.Apod;
import com.example.android.nasapod.repo.ApodRepo;


import org.joda.time.LocalDate;

import java.util.List;

public class GetListPicOfTheDayAsyncTask extends AsyncTask<ApodRepo, Integer, List<Apod>> {

    public interface Listener {
       void onApodsReturned(List<Apod> apods);
    }

    private Listener mListener;

    public GetListPicOfTheDayAsyncTask(Listener listener) {
        mListener = listener;
    }

    LocalDate yesterday = LocalDate.now().minusDays(1);
    LocalDate sevenDaysAgo = LocalDate.now().minusDays(7);

    @Override
    protected List<Apod> doInBackground(ApodRepo... apodRepos) {
        return apodRepos[0].getListPicOfTheDay(sevenDaysAgo,yesterday);
    }

    @Override
    protected void onPostExecute(List<Apod> apods) {
        super.onPostExecute(apods);
        mListener.onApodsReturned(apods);
    }
}
