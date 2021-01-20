package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
    }

    public void backToMenu(View View) {
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }

    public void addPerson(View View) {
        Intent i = new Intent(this, AddActivity.class);

        startActivity(i);
    }
}