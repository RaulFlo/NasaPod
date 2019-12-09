package com.example.android.nasapod;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ApodViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageUrl;
    public TextView mTitleName;
    public TextView mDate;


    public ApodViewHolder(@NonNull View itemView) {
        super(itemView);


        mImageUrl = itemView.findViewById(R.id.imageview_imageUrl);
        mTitleName = itemView.findViewById(R.id.textview_titleName);
        mDate = itemView.findViewById(R.id.textview_date);
    }

    void bind(Apod anApod) {

    }


}



