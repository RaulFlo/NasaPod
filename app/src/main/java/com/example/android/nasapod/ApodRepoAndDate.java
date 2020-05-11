package com.example.android.nasapod;

import com.example.android.nasapod.repo.ApodRepo;
import org.joda.time.LocalDate;

public class ApodRepoAndDate {
    ApodRepo mApodRepo;
    LocalDate mStartDate;
    LocalDate mEndDate;


    public ApodRepoAndDate(ApodRepo apodRepo, org.joda.time.LocalDate date, org.joda.time.LocalDate date2) {
        mApodRepo = apodRepo;
        mStartDate = date;
        mEndDate = date2;
    }
}
