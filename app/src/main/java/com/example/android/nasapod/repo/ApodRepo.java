package com.example.android.nasapod.repo;

import com.example.android.nasapod.models.Apod;

import org.joda.time.LocalDate;

import java.io.IOException;
import java.util.List;

public interface ApodRepo {
    Apod getPicOfTheDay(LocalDate date);

    List<Apod> getListPicOfTheDay(LocalDate start, LocalDate end);

}
