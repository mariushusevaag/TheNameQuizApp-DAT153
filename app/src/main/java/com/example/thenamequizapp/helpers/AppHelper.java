package com.example.thenamequizapp.helpers;

import android.app.Application;
import android.net.Uri;

import com.example.thenamequizapp.classes.Person;

import java.util.ArrayList;

public class AppHelper extends Application {
    private ArrayList<Person> persons = new ArrayList<Person>();
    private Uri currentSelectedPic;

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void addPersons(Person person) {
        this.persons.add(person);
    }

    public Uri getCurrentSelectedPic() {
        return currentSelectedPic;
    }

    public void addCurrentSelectedPic(Uri uri) {
        this.currentSelectedPic = uri;
    }
}