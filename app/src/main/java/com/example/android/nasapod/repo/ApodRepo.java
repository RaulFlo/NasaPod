package com.example.android.nasapod.repo;

import com.example.android.nasapod.models.Apod;
import org.joda.time.LocalDate;
import java.util.List;

public interface ApodRepo {

    List<Apod> getListPicOfTheDay(LocalDate start, LocalDate end);

}
