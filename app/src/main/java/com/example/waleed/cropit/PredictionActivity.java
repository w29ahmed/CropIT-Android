package com.example.waleed.cropit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class PredictionActivity extends AppCompatActivity {

    private Button saveButton;
    private Button moreInfoButton;

    private ImageView testImageView;



    // UI Event Handlers
    private Button.OnClickListener saveListener = new Button.OnClickListener() {
        public void onClick(View v) {
        }
    };

    private Button.OnClickListener moreInfoListener = new Button.OnClickListener() {
        public void onClick(View v) {
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prediction_layout);

        // Instantiate references to the UI objects
        // Instantiate references to the UI objects

        testImageView = (ImageView)findViewById(R.id.TestImageView);
        saveButton = (Button)findViewById(R.id.SaveButton);
        moreInfoButton = (Button)findViewById(R.id.MoreInfoButton);

        // Connect event handlers

        saveButton.setOnClickListener(saveListener);
        moreInfoButton.setOnClickListener(moreInfoListener);

        // In PredictionActivity
        Bundle extras = getIntent().getExtras().getBundle("image");
        Bitmap imageBitmap = (Bitmap) extras.get("data");
        testImageView.setImageBitmap(imageBitmap);

    }
}