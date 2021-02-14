package com.example.thenamequizapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thenamequizapp.classes.Helper;
import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.converters.Converter;
import com.example.thenamequizapp.database.AppDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AppDatabase appDb;
    private Helper helper;
    public int lastScore;
    public int possibleLastScore;
    private String imageSourceJens;
    private String imageSourceErna;

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

        //Creates first entry to helper db
        helper = new Helper();

        Boolean hasStarted = helper.getHasStarted();

        //Checks if the  db is empty
        if(!hasStarted) {
            //Make URI's out of pre-saved images
            Uri p1Uri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.jens);
            Uri p2Uri = Uri.parse("android.resource://com.example.thenamequizapp/drawable/" + R.drawable.erna);

            try {
                InputStream is1 = getContentResolver().openInputStream(p1Uri);
                InputStream is2 = getContentResolver().openInputStream(p2Uri);
                Bitmap bitmap1 = BitmapFactory.decodeStream(is1);
                Bitmap bitmap2 = BitmapFactory.decodeStream(is2);
                imageSourceJens = Converter.BitMapToString(bitmap1);
                imageSourceErna = Converter.BitMapToString(bitmap2);
            } catch (FileNotFoundException e) {

            }

            //Makes persons
            Person p1 = new Person("Jens", imageSourceJens);
            Person p2 = new Person("Erna", imageSourceErna);

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