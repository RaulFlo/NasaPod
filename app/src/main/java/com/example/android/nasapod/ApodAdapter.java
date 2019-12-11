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
import java.util.List;

public class ApodAdapter extends RecyclerView.Adapter<ApodViewHolder> implements ApodViewHolder.Listener {

    interface AdapterListener {
        void onItemClick(Apod apod);
    }

    private static final String TAG = "ApodAdapter";

    private List<Apod> mApodList;
    private AdapterListener mListener;

    public ApodAdapter(List<Apod> apodList, AdapterListener listener) {
        mApodList = apodList;
        mListener = listener;
    }


    @NonNull
    @Override
    public ApodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_apod, parent, false);
        return new ApodViewHolder(v,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ApodViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder");
        Apod currentApod = mApodList.get(position);

        holder.bind(currentApod);

    }


    @Override
    public int getItemCount() {
        return mApodList.size();
    }


    @Override
    public void onItemClick(int adapterPosition) {
        Log.d(TAG, "onItemClick: +++++++++++++++++++++");
        Apod apod = mApodList.get(adapterPosition);
        mListener.onItemClick(apod);
    }
}
