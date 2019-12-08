package com.example.android.nasapod;

import java.util.List;

public interface ApodRepo {
    Apod getPicOfTheDay();

    List<Apod> getListPicOfTheDay();
}
