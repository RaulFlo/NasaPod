package com.example.android.nasapod.internal;

import com.example.android.nasapod.repo.ApodRepo;
import org.joda.time.LocalDate;

public class ApodRepoAndDate {
    final ApodRepo mApodRepo;
    final LocalDate mStartDate;
    final LocalDate mEndDate;


    public ApodRepoAndDate(ApodRepo apodRepo, org.joda.time.LocalDate date, org.joda.time.LocalDate date2) {
        mApodRepo = apodRepo;
        mStartDate = date;
        mEndDate = date2;
    }
}
