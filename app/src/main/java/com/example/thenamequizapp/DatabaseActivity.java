package com.example.thenamequizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.thenamequizapp.classes.Person;
import com.example.thenamequizapp.helpers.AppHelper;

import java.util.ArrayList;

public class DatabaseActivity extends AppCompatActivity {

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        mListView = (ListView) findViewById(R.id.list_view);
//        TODO:
//          -Get all entries in database
//          -Show all entries that are collected


        //Gets all persons and puts them in a local variable
        ArrayList<Person> database = ((AppHelper) this.getApplication()).getPersons();


        //Taking only persons names for now
        ArrayList<String> personNames = new ArrayList<String>();
        for (Person person : database) {
            personNames.add(person.getName());
        }

        //Create array adapter
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, personNames);

        //Lists all the persons
        mListView.setAdapter(itemsAdapter);
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