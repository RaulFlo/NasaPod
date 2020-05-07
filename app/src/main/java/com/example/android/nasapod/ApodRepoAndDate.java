package com.example.android.nasapod;

import com.example.android.nasapod.repo.ApodRepo;

import org.joda.time.LocalDate;

public class ApodRepoAndDate {
    public ApodRepo mApodRepo;
    public LocalDate mStartDate;
    public LocalDate mEndDate;


    public ApodRepoAndDate(ApodRepo apodRepo, org.joda.time.LocalDate date, org.joda.time.LocalDate date2) {
        mApodRepo = apodRepo;
        mStartDate = date;
        mEndDate = date2;

    }
}
