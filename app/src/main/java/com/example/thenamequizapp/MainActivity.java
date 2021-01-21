package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.helpers.AppHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Person> database = ((AppHelper) this.getApplication()).getPersons();

        if(database.isEmpty()) {
            Person p1 = new Person("Jens", R.drawable.jens);
            Person p2 = new Person("Erna", R.drawable.erna);

            ((AppHelper) this.getApplication()).addPersons(p1);
            ((AppHelper) this.getApplication()).addPersons(p2);
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