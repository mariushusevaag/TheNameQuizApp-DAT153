package com.example.thenamequizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thenamequizapp.adapters.CustomAdapter;
import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.database.AppDatabase;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    // View
    ListView mListView;

    AppDatabase appDb;
    public int persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        appDb = AppDatabase.getInstance(this);
        persons = appDb.personDao().getPersons().size();

        // Fills the list view with persons
        populateList();
    }

    //Function to fill the listView with persons from db
    public void populateList() {
        mListView = (ListView) findViewById(R.id.list_view);

        //Gets persons from db
        List<Person> personList = appDb.personDao().getPersons();

        //Create array adapter
        CustomAdapter customAdapter = new CustomAdapter(DatabaseActivity.this, personList);

        //Lists all the persons
        mListView.setAdapter(customAdapter);
    }

    // Redirecting to add person view
    public void addPerson(View View) {
        Intent i = new Intent(this, AddActivity.class);

        startActivity(i);
    }

    // Handling back button press
    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }
}