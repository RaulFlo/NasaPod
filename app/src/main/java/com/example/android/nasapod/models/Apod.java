package com.example.android.nasapod.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Apod implements Parcelable {

    @SerializedName("url")
    private String mApodImage;
    @SerializedName("title")
    private String mApodName;
    @SerializedName("date")
    private String mApodDate;
    @SerializedName("explanation")
    private String mExplanation;

    private Apod(Parcel in) {
        mApodImage = in.readString();
        mApodName = in.readString();
        mApodDate = in.readString();
        mExplanation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mApodImage);
        dest.writeString(mApodName);
        dest.writeString(mApodDate);
        dest.writeString(mExplanation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Apod> CREATOR = new Creator<Apod>() {
        @Override
        public Apod createFromParcel(Parcel in) {
            return new Apod(in);
        }

        @Override
        public Apod[] newArray(int size) {
            return new Apod[size];
        }
    };

    public String getApodImage() {
        return mApodImage;
    }

    public String getApodName() {
        return mApodName;
    }

    public String getApodDate() {
        return mApodDate;
    }

    public String getApodExplanation(){return mExplanation;}
}
