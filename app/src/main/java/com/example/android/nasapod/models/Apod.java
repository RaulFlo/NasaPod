package com.example.android.nasapod.models;

import com.google.gson.annotations.SerializedName;

public class Apod {

    @SerializedName("url")
    private String mApodImage;
    @SerializedName("title")
    private String mApodName;
    @SerializedName("date")
    private String mApodDate;


    public Apod(String aPodImage, String aPodName, String aPodDate){

        this.mApodImage = aPodImage;
        this.mApodName = aPodName;
        this.mApodDate = aPodDate;

    }

    public String getApodImage() {
        return mApodImage;
    }

    public String getApodName() {
        return mApodName;
    }

    public String getApodDate() {
        return mApodDate;
    }
}
