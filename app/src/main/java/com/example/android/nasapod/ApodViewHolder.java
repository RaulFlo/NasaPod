package com.example.android.nasapod;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ApodViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageView;
    public TextView mTitleName;
    public TextView mDate;



    public ApodViewHolder(@NonNull View itemView) {
        super(itemView);


        mImageView = itemView.findViewById(R.id.image_view_image);
        mTitleName = itemView.findViewById(R.id.text_view_titleName);
        mDate = itemView.findViewById(R.id.text_view_date);
    }

    public void bind(Apod anApod) {

        String imageUrl = anApod.getApodImage();
        //Glide for ImageView
        Glide.with(itemView.getContext()).load(imageUrl).fitCenter().into(mImageView);
        mTitleName.setText(anApod.getApodName());
        mDate.setText(anApod.getApodDate());

    }


}


