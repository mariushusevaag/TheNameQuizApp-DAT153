package com.example.thenamequizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class AddActivity extends AppCompatActivity {

    ImageView mImageView;
    Button mChooseBtn;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Views
        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.addPhotoBtn);

        //handle button click
        mChooseBtn.setOnClickListener(v -> {
            //check runtime permission
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                //permission not granted, request it
                String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};

                //show popup for runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            }
            else {
                //permission already granted
                pickImageFromGallery();
            }
        });
    }

    private void pickImageFromGallery() {
        //intent to pick image
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    //handle result of runtime permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //permission was granted
                pickImageFromGallery();
            } else {
                //permission was denied
                Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //handle result of picked image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            //Set image to image view
            mImageView.setImageURI(data.getData());
        }
    }

    public void backToDatabase(View View) {
        Intent i = new Intent(this, DatabaseActivity.class);

        startActivity(i);
    }
//
//    public void selectImage() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");
//        startActivityForResult(intent, IMAGE_PICK_CODE);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE) {
//            mImageView.setImageURI(data.getData());
//        }
//    }

    public void onSubmit() {
//      TODO:
//        -Make person from name and image
//        -Put person in correct db
//        -View short msg telling "person added to db" / toast
//        -Clear input field & image slot
    }
}