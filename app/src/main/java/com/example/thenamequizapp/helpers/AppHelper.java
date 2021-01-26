package com.example.thenamequizapp.helpers;

import android.app.Application;
import android.net.Uri;

import com.example.thenamequizapp.classes.Person;

import java.util.ArrayList;

public class AppHelper extends Application {
    private ArrayList<Person> persons = new ArrayList<>();
    private boolean isStart = true;
    private Uri currentSelectedPic;
    private int lastScore;
    private int totalScorePossible;

    // Gets all persons from the person "database"
    public ArrayList<Person> getPersons() {
        return persons;
    }

    // Adds a person to the person "database"
    public void addPersons(Person person) {
        this.persons.add(person);
    }

    // Gets a photo from the "placeholder"
    public Uri getCurrentSelectedPic() {
        return currentSelectedPic;
    }

    // Adds a photo to a "placeholder"
    public void addCurrentSelectedPic(Uri uri) {
        this.currentSelectedPic = uri;
    }

    // Gets boolean to check if app already started
    public boolean getIsStart() {
        return isStart;
    }

    // Changes from true to false on call
    public void startDone() {
        this.isStart = false;
    }

    // Gets last quiz score
    public void setLastScore(int i) {
        this.lastScore = i;
    }

    // Sets last quiz score
    public int getLastScore() {
        return lastScore;
    }

    // Sets the number of what the biggest score you could get last quiz was
    public void setTotalScorePossible(int i) {
        this.totalScorePossible = i;
    }

    // Gets number of what the biggest score you could get last quiz was
    public int getTotalScorePossible() {
        return totalScorePossible;
    }

}
