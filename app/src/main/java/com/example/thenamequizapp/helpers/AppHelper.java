package com.example.thenamequizapp.helpers;

import android.app.Application;

import com.example.thenamequizapp.classes.Person;

import java.util.ArrayList;

public class AppHelper extends Application {
    private ArrayList<Person> persons = new ArrayList<Person>();

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void addPersons(Person person) {
        this.persons.add(person);
    }
}
