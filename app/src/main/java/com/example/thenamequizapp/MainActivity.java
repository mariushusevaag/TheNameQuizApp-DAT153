package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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

        // Checks if the app already been started
        checkDb();

        final TextView textViewTitle = findViewById(R.id.mainTextTitle);

        // Gets numbers from last quiz
        int lastScore = ((AppHelper) this.getApplication()).getLastScore();
        int possibleLastScore = ((AppHelper) this.getApplication()).getTotalScorePossible();

        // Checking if there has been any quiz
        if (possibleLastScore != 0) {
            // Views the last result
            String lastScoreTxt = "You're last score was: " + lastScore + "/" + possibleLastScore;
            textViewTitle.setText(lastScoreTxt);
        }
    }

    //Function for checking if db is empty & adding stock persons if empty
    public void checkDb() {
        //Brings persons from db
        ArrayList<Person> database = ((AppHelper) this.getApplication()).getPersons();
        boolean isStart = ((AppHelper) this.getApplication()).getIsStart();
        //Checks if the  db is empty
        if(database.isEmpty() && isStart) {
            //Make URI's out of pre-saved images
            Uri p1Uri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.jens);
            Uri p2Uri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.erna);

            //Makes persons
            Person p1 = new Person("Jens", p1Uri);
            Person p2 = new Person("Erna", p2Uri);

            //Adds persons to db
            ((AppHelper) this.getApplication()).addPersons(p1);
            ((AppHelper) this.getApplication()).addPersons(p2);

            //Sets start boolean to false
            ((AppHelper) this.getApplication()).startDone();
        }
    }

    // Redirecting to the database view
    public void exploreDatabase(View View) {
        Intent i = new Intent(this, DatabaseActivity.class);

        startActivity(i);
    }

    // Redirecting to the quiz view
    public void startQuiz(View View) {
        Intent i = new Intent(this, QuizActivity.class);

        startActivity(i);
    }

    // Handling back button press
    @Override
    public void onBackPressed() {
        finish();
    }
}