package com.example.thenamequizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.helpers.AppHelper;

public class AddActivity extends AppCompatActivity {

    ImageView mImageView;
    Button mChooseBtn;
    Button saveBtn;
    EditText textField;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Views
        mImageView = findViewById(R.id.image_view);
        mChooseBtn = findViewById(R.id.addPhotoBtn);
        saveBtn = findViewById(R.id.savePersonBtn);
        textField = findViewById(R.id.addPersonNameEdit);

        //handle button click
        mChooseBtn.setOnClickListener(v -> {
            pickPhoto();
        });

        //handle button click
        saveBtn.setOnClickListener(v -> {
            addPerson();
        });
    }

    //Function for pickingPhoto
    public void pickPhoto() {
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
    }

    //Function for adding person to db
    public void addPerson() {
        //check if image is chosen & text field != null
        if (mImageView.getDrawable() != null && !textField.getText().toString().matches("")) {

            //Creates new person from selected pic & name
            //Person person = new Person(textField.getText().toString(), mImageView.getDrawable());
            Person person = new Person(textField.getText().toString(), ((AppHelper) this.getApplication()).getCurrentSelectedPic());

            try {
                //Adds newly created person to the db
                ((AppHelper) this.getApplication()).addPersons(person);

                //Clearing the imageView and editText
                mImageView.setImageResource(0);
                textField.getText().clear();

                //Sending success message to user
                Toast.makeText(this, "Added person to the database", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();

                //Sending error message to user
                Toast.makeText(this, "Something went wrong...", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            //Sending error message to user
            Toast.makeText(this, "You need to pick a image & fill the text field", Toast.LENGTH_SHORT).show();
        }
    }

    //Function for opening gallery
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

            //Set image as current picked image in db
            ((AppHelper) this.getApplication()).addCurrentSelectedPic(data.getData());

            Toast.makeText(this, "Image picked!", Toast.LENGTH_SHORT).show();
        }
    }

    public void backToDatabase(View View) {
        Intent i = new Intent(this, DatabaseActivity.class);

        startActivity(i);
    }
}