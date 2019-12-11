package com.example.android.nasapod;

import android.content.Context;
import android.net.sip.SipAudioCall;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ApodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    interface Listener {
        void onItemClick(int adapterPosition);
    }

    public ImageView mImageView;
    public TextView mTitleName;
    public TextView mDate;

    private Listener myListener;



    public ApodViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);

        myListener = listener;
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


    @Override
    public void onClick(View view) {
        if(myListener != null){
            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION){
                myListener.onItemClick(position);
            }
        }
    }
}


