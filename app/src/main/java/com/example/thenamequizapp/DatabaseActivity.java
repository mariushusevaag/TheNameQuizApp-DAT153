package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thenamequizapp.adapters.CustomAdapter;
import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.helpers.AppHelper;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        populateList();
    }

    //Function to fill the listView with persons from db
    public void populateList() {
        mListView = (ListView) findViewById(R.id.list_view);

        //Gets all persons and puts them in a local variable
        ArrayList<Person> database = ((AppHelper) this.getApplication()).getPersons();

        //Create array adapter
        CustomAdapter customAdapter = new CustomAdapter(DatabaseActivity.this, database);

        //Lists all the persons
        mListView.setAdapter(customAdapter);
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