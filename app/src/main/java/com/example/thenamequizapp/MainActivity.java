package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.thenamequizapp.classes.Database;
import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.helpers.DatabaseHelper;

import java.io.IOException;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    public static final String AppPREFERENCES = "AppPrefs";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        if(DatabaseHelper.db.getPersons() == null) {
            Person p1 = new Person
        }*/
    }

    public void exploreDatabase(View View) {
        Intent i = new Intent(this, DatabaseActivity.class);

        startActivity(i);
    }

    public void startQuiz(View View) {
        Intent i = new Intent(this, QuizActivity.class);

        startActivity(i);
    }
}