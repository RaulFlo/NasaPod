package com.example.android.nasapod;

import android.os.AsyncTask;

import com.example.android.nasapod.models.Apod;
import com.example.android.nasapod.repo.ApodRepo;
import com.example.android.nasapod.repo.RetrofitRepo;


import java.util.List;

public class GetListPicOfTheDayAsyncTask extends AsyncTask<ApodRepo,Integer, List<Apod>> {

    public interface Listener{
        public void onApodsReturned(List<Apod> apods);
    }

    private Listener mListener;

    public GetListPicOfTheDayAsyncTask(Listener listener){
        mListener = listener;
    }





    @Override
    protected List<Apod> doInBackground(ApodRepo... apodRepos) {
        return apodRepos[0].getListPicOfTheDay();
    }

    @Override
    protected void onPostExecute(List<Apod> apods) {
        super.onPostExecute(apods);
        mListener.onApodsReturned(apods);
    }
}
