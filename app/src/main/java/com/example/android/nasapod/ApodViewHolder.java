package com.example.android.nasapod;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ApodViewHolder extends RecyclerView.ViewHolder {

    public ImageView mImageUrl;
    public TextView mTitleName;
    public TextView mDate;
    private Context mContext;


    public ApodViewHolder(@NonNull View itemView) {
        super(itemView);


        mImageUrl = itemView.findViewById(R.id.imageview_imageUrl);
        mTitleName = itemView.findViewById(R.id.textview_titleName);
        mDate = itemView.findViewById(R.id.textview_date);
    }

    public void bind(Apod anApod) {

        String imageUrl = anApod.getApodImage();
        //Glide for ImageView
        Glide.with(mContext).load(imageUrl).fitCenter().into(mImageUrl);
        mTitleName.setText(anApod.getApodName());
        mDate.setText(anApod.getApodDate());

    }


}



