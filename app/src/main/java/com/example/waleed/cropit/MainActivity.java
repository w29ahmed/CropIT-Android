package com.example.waleed.cropit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private Button takePictureButton;
    private Button loadPictureButton;

    private ImageView testImageView;

    static final int REQUEST_IMAGE_CAPTURE = 1;

    // UI Event Handlers
    private Button.OnClickListener takePictureListener = new Button.OnClickListener() {
        public void onClick(View v) {
            // Create a image capture intent to prompt user to take a picture
            // Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            // startActivity(intent);
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    };

    private Button.OnClickListener loadPictureListener = new Button.OnClickListener() {
        public void onClick(View v) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO: This code could possibly cause slowdowns - look into optimizing loading in the data
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            testImageView.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate references to the UI objects
        takePictureButton = (Button)findViewById(R.id.TakePictureButton);
        loadPictureButton = (Button)findViewById(R.id.LoadPictureButton);
        testImageView = (ImageView)findViewById(R.id.TestImageView);

        // Connect event handlers
        takePictureButton.setOnClickListener(takePictureListener);
        loadPictureButton.setOnClickListener(loadPictureListener);

    }
}
