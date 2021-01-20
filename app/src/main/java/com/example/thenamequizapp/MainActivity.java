package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.thenamequizapp.classes.Database;
import com.example.thenamequizapp.classes.Person;

import java.io.IOException;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {

    public static final String AppPREFERENCES = "AppPrefs";
    public static final String DATABASE = null;
    public static final Database mainDb = new Database("mainDb");
    public static final Person p1 = new Person("Erna");
    public static final Person p2 = new Person("Jens");

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        sharedPreferences = getSharedPreferences(AppPREFERENCES, Context.MODE_PRIVATE);
//
//        mainDb.addPerson(p1);
//        mainDb.addPerson(p2);
//
//        Gson gson = new Gson();
//        SharedPreferences.Editor editor = sharedPreferences.edit();

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