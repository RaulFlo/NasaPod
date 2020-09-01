package com.example.android.nasapod.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Apod implements Parcelable {

    @SerializedName("url")
    private final String mApodImage;
    @SerializedName("hdurl")
    private final String mHdImage;
    @SerializedName("title")
    private final String mApodName;
    @SerializedName("date")
    private final String mApodDate;
    @SerializedName("explanation")
    private final String mExplanation;
    @SerializedName("copyright")
    private final String mCopyright;

    private Apod(Parcel in) {
        mApodImage = in.readString();
        mHdImage = in.readString();
        mApodName = in.readString();
        mApodDate = in.readString();
        mExplanation = in.readString();
        mCopyright = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mApodImage);
        dest.writeString(mHdImage);
        dest.writeString(mApodName);
        dest.writeString(mApodDate);
        dest.writeString(mExplanation);
        dest.writeString(mCopyright);
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

    public String getHdImage(){return mHdImage;}

    public String getApodName() {
        return mApodName;
    }

    public String getApodDate() {
        return mApodDate;
    }

    public String getApodExplanation(){return mExplanation;}

    public String getCopyright(){return mCopyright;}
}
