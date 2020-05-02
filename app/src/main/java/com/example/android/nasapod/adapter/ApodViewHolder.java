package com.example.android.nasapod.adapter;

import android.content.Context;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android.nasapod.MyApp;
import com.example.android.nasapod.R;
import com.example.android.nasapod.activities.MainActivity;
import com.example.android.nasapod.models.Apod;

public class ApodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String TAG = "ApodViewHolder";

    interface Listener {
        void onItemClick(int adapterPosition);
    }

    public ImageView mImageView;
    public TextView mTitleName;
    public TextView mDate;
    public Listener mListener;
    public CardView mContainer;


    public ApodViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);


       mContainer = itemView.findViewById(R.id.container);
        mListener = listener;
        mImageView = itemView.findViewById(R.id.image_view_image);
        mTitleName = itemView.findViewById(R.id.text_view_titleName);
        mDate = itemView.findViewById(R.id.text_view_date);

        //link the click listener
        itemView.setOnClickListener(this);
    }


    public void bind(Apod anApod) {

        mContainer.setAnimation(AnimationUtils.loadAnimation(MyApp.getAppContext(),R.anim.fade_transition_animation));

        String imageUrl = anApod.getApodImage();
        //Glide for ImageView
        Glide.with(itemView.getContext()).load(imageUrl).fitCenter().into(mImageView);
        mTitleName.setText(anApod.getApodName());
        mDate.setText(anApod.getApodDate());


    }


    @Override
    public void onClick(View view) {
        if (mListener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                mListener.onItemClick(position);
            }
        }
    }
}


