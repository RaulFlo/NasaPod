package com.example.android.nasapod.repo;

import com.example.android.nasapod.models.Apod;

import java.util.List;

public interface ApodRepo {
    Apod getPicOfTheDay();

    List<Apod> getListPicOfTheDay();
}
