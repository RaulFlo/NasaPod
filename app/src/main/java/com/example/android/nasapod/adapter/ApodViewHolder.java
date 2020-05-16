package com.example.android.nasapod.adapter;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.android.nasapod.MyApp;
import com.example.android.nasapod.R;
import com.example.android.nasapod.models.Apod;

class ApodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    interface Listener {
        void onItemClick(int adapterPosition, ImageView imageViewClicked);
    }

    private final ImageView mImageView;
    private final TextView mTitleName;
    private final TextView mDate;
    private final Listener mListener;
    private final CardView mContainer;
    private final View mLayoutWrapper;


    ApodViewHolder(@NonNull View itemView, Listener listener) {
        super(itemView);
        mContainer = itemView.findViewById(R.id.container);
        mListener = listener;
        mImageView = itemView.findViewById(R.id.image_view_image);
        mTitleName = itemView.findViewById(R.id.text_view_titleName);
        mDate = itemView.findViewById(R.id.text_view_date);
        mLayoutWrapper = itemView.findViewById(R.id.linear_layout_wrapper);
        //link the click listener
        itemView.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void bind(Apod anApod) {
        mContainer.setAnimation(AnimationUtils.loadAnimation(MyApp.getAppContext(), R.anim.fade_transition_animation));

        String imageUrl = anApod.getApodImage();

        Glide.with(itemView.getContext()).asBitmap().load(imageUrl).fitCenter().into(new CustomTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, Transition<? super Bitmap> transition) {
                // you now have a bitmap
                mImageView.setImageBitmap(resource);
                createPaletteAsync(resource);
            }

            @Override
            public void onLoadCleared(Drawable placeholder) {

            }
        });

        mTitleName.setText(anApod.getApodName());
        mDate.setText(anApod.getApodDate());


        String transitionName = itemView.getContext().getString(R.string.args_transition_apod_name, anApod.getApodName());
        mImageView.setTransitionName(transitionName);

    }


    @Override
    public void onClick(View view) {
        if (mListener != null) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                mListener.onItemClick(position, mImageView);
            }
        }
    }

    // Generate palette asynchronously and use it on a different, thread using onGenerated()
    private void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            public void onGenerated(Palette palette) {
                // Use generated instance
                int color = MyApp.getAppContext().getResources().getColor(R.color.colorPrimaryDark);
                int dominantColor = palette.getDominantColor(color);
                int cardColor = MyApp.getAppContext().getResources().getColor(R.color.colorPrimaryDark);
                int darkMutedColor = palette.getDarkMutedColor(cardColor);

                mImageView.setBackgroundColor(dominantColor);
                mLayoutWrapper.setBackgroundColor(darkMutedColor);
            }
        });
    }
}


