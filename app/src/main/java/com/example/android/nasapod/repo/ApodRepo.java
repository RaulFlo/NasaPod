package com.example.android.nasapod.repo;

import com.example.android.nasapod.models.Apod;

import java.io.IOException;
import java.util.List;

public interface ApodRepo {
    Apod getPicOfTheDay() throws IOException;

    List<Apod> getListPicOfTheDay() throws IOException;
}
