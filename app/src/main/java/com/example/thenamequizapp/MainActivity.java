package com.example.thenamequizapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thenamequizapp.classes.Helper;
import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.database.AppDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDatabase appDb;
    private Helper helper;
    public int lastScore;
    public int possibleLastScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appDb = AppDatabase.getInstance(this);

        // Checks if the app already been started
        helper = appDb.helperDao().getHelper();
        if (helper == null) {checkDb();}

        // Connects text view
        final TextView textViewTitle = findViewById(R.id.mainTextTitle);

        // Gets numbers from last quiz
        if(helper != null) {
            lastScore = helper.getLastScore();
            possibleLastScore = helper.getTotalScorePossible();

            // Checking if there has been any quiz
            if (possibleLastScore != 0) {
                // Views the last result
                String lastScoreTxt = "You're last score was: " + lastScore + "/" + possibleLastScore;
                textViewTitle.setText(lastScoreTxt);
            }
        }
    }

    //Function for checking if db is empty & adding stock persons if empty
    public void checkDb() {
        //Brings persons from db
        List<Person> persons = appDb.personDao().getPersons();

        //Creates first enty to helper db
        helper = new Helper();

        Boolean hasStarted = helper.getHasStarted();

        //Checks if the  db is empty
        if(!hasStarted) {
            //Make URI's out of pre-saved images
            Uri p1Uri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.jens);
            Uri p2Uri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.erna);

            //Makes persons
            Person p1 = new Person("Jens", p1Uri);
            Person p2 = new Person("Erna", p2Uri);

            //Adds persons to db
            appDb.personDao().addPerson(p1);
            appDb.personDao().addPerson(p2);

            //Sets start boolean to false
            helper.setHasStarted(true);
            appDb.helperDao().updateHelper(helper);
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