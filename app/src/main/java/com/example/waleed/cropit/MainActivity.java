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

public class MainActivity extends AppCompatActivity {

    private Button takePictureButton;
    private Button loadPictureButton;

    private ImageView testImageView;

    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int REQUEST_IMAGE_PICK = 2;

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
            // Create intent for picking a photo from the gallery
            Intent intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
            // So as long as the result is not null, it's safe to use the intent.
            if (intent.resolveActivity(getPackageManager()) != null) {
                // Bring up gallery to select a photo
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            }
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
        else if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK) {
            //Uri photoUri = data.getData();
            // Do something with the photo based on Uri
            //Bitmap selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
            // Load the selected image into a preview
            //testImageView.setImageURI(photoUri);
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                testImageView.setImageBitmap(selectedImage);
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
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
