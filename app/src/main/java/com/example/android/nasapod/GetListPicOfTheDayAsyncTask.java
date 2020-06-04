package com.example.android.nasapod;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.ProgressBar;

import com.example.android.nasapod.models.Apod;
import org.joda.time.LocalDate;
import java.util.List;

public class GetListPicOfTheDayAsyncTask extends AsyncTask<ApodRepoAndDate, Integer, List<Apod>> {

    private static final int ADD_DAY_REQUESTED = 1;

    public interface Listener {
        void onApodsReturned(List<Apod> apods);
    }

    private final Listener mListener;
    ProgressBar bar;

    public GetListPicOfTheDayAsyncTask(Listener listener,ProgressBar bar) {
        mListener = listener;
        this.bar = bar;

    }



    @Override
    protected List<Apod> doInBackground(ApodRepoAndDate... apodRepoAndDates) {
        ApodRepoAndDate apodRepoAndDate = apodRepoAndDates[0];
        LocalDate dateRequested = apodRepoAndDate.mStartDate;
        LocalDate dateRequested2 = apodRepoAndDate.mEndDate.plusDays(ADD_DAY_REQUESTED);


        return apodRepoAndDate.mApodRepo.getListPicOfTheDay(dateRequested,dateRequested2);
    }


    @Override
    protected void onPostExecute(List<Apod> apods) {
        super.onPostExecute(apods);
        mListener.onApodsReturned(apods);
        bar.setProgress(100);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        if (this.bar != null) {
            bar.setProgress(values[0]);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bar.setProgress(0);

    }
}
