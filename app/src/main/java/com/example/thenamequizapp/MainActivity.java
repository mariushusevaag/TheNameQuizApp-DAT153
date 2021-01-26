package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.helpers.AppHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkDb();

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        String titleString = extras.getString("titleString");

        final TextView textViewTitle = findViewById(R.id.mainTextTitle);

        textViewTitle.setText(titleString);
    }

    //Function for checking if db is empty & adding stock persons if empty
    public void checkDb() {
        //Brings persons from db
        ArrayList<Person> database = ((AppHelper) this.getApplication()).getPersons();
        boolean isStart = ((AppHelper) this.getApplication()).getIsStart();
        //Checks if the  db is empty
        if(database.isEmpty() && isStart) {
            //Makes persons
            Person p1 = new Person("Jens", R.drawable.jens);
            Person p2 = new Person("Erna", R.drawable.erna);

            //Adds persons to db
            ((AppHelper) this.getApplication()).addPersons(p1);
            ((AppHelper) this.getApplication()).addPersons(p2);

            //Sets start boolean to false
            ((AppHelper) this.getApplication()).startDone();
        }
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