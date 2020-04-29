package com.example.android.nasapod;

import com.example.android.nasapod.repo.ApodRepo;

import org.joda.time.LocalDate;

public class ApodRepoAndDate {
    public ApodRepo mApodRepo;
    public LocalDate mDate;

    public ApodRepoAndDate(ApodRepo apodRepo, LocalDate date) {
        mApodRepo = apodRepo;
        mDate = date;
    }
}
