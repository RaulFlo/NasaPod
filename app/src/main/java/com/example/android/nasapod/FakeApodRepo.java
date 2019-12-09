package com.example.android.nasapod;

import java.util.ArrayList;
import java.util.List;

public class FakeApodRepo implements ApodRepo {
    @Override
    public Apod getPicOfTheDay() {
        Apod fakeApod = new Apod(
                "FakeApodRepoUrlImage",
                "FakeApodName",
                "2019-12-17"

        );
        return fakeApod;
    }

    @Override
    public List<Apod> getListPicOfTheDay() {
        List<Apod> listOfFakeApod = new ArrayList<>();
        listOfFakeApod.add(new Apod("firstListFakeApodImage","firstListFakeApodName","2019-12-18"));
        listOfFakeApod.add(new Apod("secondListFakeApodImage","secondListFakeApodName","2019-12-19"));
        listOfFakeApod.add(new Apod("thirdListFakeApodImage","thirdListFakeApodName","2019-12-19"));

        return listOfFakeApod;
    }
}
