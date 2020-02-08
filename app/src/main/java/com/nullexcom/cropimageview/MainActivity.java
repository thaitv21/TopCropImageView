package com.nullexcom.cropimageview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgCrop = findViewById(R.id.imgCrop);
        ImageView imgNoCrop = findViewById(R.id.imgNoCrop);
        String url = "https://image.shutterstock.com/image-photo/colorful-flower-on-dark-tropical-260nw-721703848.jpg";
        Glide.with(this).load(url).into(imgCrop);
        Glide.with(this).load(url).into(imgNoCrop);
    }
}
