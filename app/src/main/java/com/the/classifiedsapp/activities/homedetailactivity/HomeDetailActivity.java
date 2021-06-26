package com.the.classifiedsapp.activities.homedetailactivity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.the.classifiedsapp.R;
import com.the.classifiedsapp.activities.homeactivity.dto.ClassifiedData;
import com.the.classifiedsapp.utils.GlobalConstants;

import java.net.UnknownHostException;

public class HomeDetailActivity extends AppCompatActivity {

    private ClassifiedData classifiedData;
    private TextView tvPrice;
    private TextView tvHeaderText;
    private ImageView ivProductDetailImage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);

        initViews();
        getIntentData();
        fillViews();
    }

    private void initViews() {
        progressBar= findViewById(R.id.progressBar);
        tvPrice = findViewById(R.id.tvPrice);
        tvHeaderText = findViewById(R.id.tvHeaderText);
        ivProductDetailImage = findViewById(R.id.ivProductDetailImage);
    }

    @SuppressLint("CheckResult")
    private void fillViews() {

        if(classifiedData == null)
            return;

        tvHeaderText.setText(classifiedData.getName());
        tvPrice.setText(classifiedData.getPrice());


        if(classifiedData.getImageUrls() != null && classifiedData.getImageUrls().size() >=1)
            Glide.with(this)
                .load(classifiedData.getImageUrls().get(0))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable @org.jetbrains.annotations.Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }
                }).into(ivProductDetailImage);

    }

    private void getIntentData() {

        Intent intent = getIntent();

        if(intent != null && intent.getBundleExtra(GlobalConstants.INTENT_BUNDLE) != null){
            Bundle bundleData = intent.getBundleExtra(GlobalConstants.INTENT_BUNDLE);

            if(bundleData.getParcelable(GlobalConstants.INTENT_CLASSIFIED_DATA) != null){
                classifiedData = bundleData.getParcelable(GlobalConstants.INTENT_CLASSIFIED_DATA);
            }


        }
    }
}