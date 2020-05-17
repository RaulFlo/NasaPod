package com.example.android.nasapod.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android.nasapod.R;
import com.example.android.nasapod.models.Apod;
import java.util.List;

public class ApodAdapter extends RecyclerView.Adapter<ApodViewHolder> implements ApodViewHolder.Listener {

    private static final String TAG = "ApodAdapter";
    private final List<Apod> mApodList;
    private final AdapterListener mListener;

    public interface AdapterListener {
        void onItemClick(Apod apod, View view);
    }

    public ApodAdapter(List<Apod> apodList, AdapterListener listener) {
        mApodList = apodList;
        mListener = listener;
    }

    public void updateData(List<Apod> apods) {
        mApodList.clear();
        mApodList.addAll(apods);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ApodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.d(TAG, "onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_apod, parent, false);
        return new ApodViewHolder(v, this);
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
    public void onItemClick(int adapterPosition, ImageView imageViewClicked) {
        Apod apod = mApodList.get(adapterPosition);
        mListener.onItemClick(apod, imageViewClicked);
    }
}
