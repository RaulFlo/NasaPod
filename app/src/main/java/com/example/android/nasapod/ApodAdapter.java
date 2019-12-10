package com.example.android.nasapod;

import android.content.Context;
import android.icu.text.IDNA;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ApodAdapter extends RecyclerView.Adapter<ApodViewHolder> {

    private static final String TAG = "NASAapod";
    private Context mContext;
    private ArrayList<Apod> mApodList;

    public ApodAdapter(Context context, ArrayList<Apod> apodList) {
        mContext = context;
        mApodList = apodList;
    }


    @NonNull
    @Override
    public ApodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_apod, parent, false);
        return new ApodViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ApodViewHolder holder, int position) {

        Log.d(TAG,"onBindViewHolder");
        Apod currentApod = mApodList.get(position);

        holder.bind(currentApod);


    }


    @Override
    public int getItemCount() {
        return mApodList.size();
    }


}
