package com.example.android.nasapod.repo;

import com.example.android.nasapod.models.Apod;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FakeApodRepo implements ApodRepo {
    @Override
    public Apod getPicOfTheDay(LocalDate date) {
        Apod fakeApod = new Apod(
                "FakeApodRepoUrlImage",
                "FakeApodName",
                "2019-12-17"

        );
        return fakeApod;
    }

    @Override
    public List<Apod> getListPicOfTheDay(LocalDate start, LocalDate end) {
        List<Apod> listOfFakeApod = new ArrayList<>();
        listOfFakeApod.add(new Apod("firstListFakeApodImage", "firstListFakeApodName", "2019-12-18"));
        listOfFakeApod.add(new Apod("secondListFakeApodImage", "secondListFakeApodName", "2019-12-19"));
        listOfFakeApod.add(new Apod("thirdListFakeApodImage", "thirdListFakeApodName", "2019-12-19"));

        for (int i = 4; i <= 1000; i++) {
            Random rand = new Random();
            listOfFakeApod.add(new Apod("Image ", "Name " + rand.nextInt(1000), "Date"));

        }

        return listOfFakeApod;
    }

}


