package com.example.thenamequizapp.classes;

import android.net.Uri;

public class Person {
    private String name;
    private Uri uri;

    // Constructor
    public Person(String name, Uri uri) {
        this.name = name;
        this.uri = uri;
    }

    // Gets person name
    public String getName() {
        return name;
    }

    // Sets person name
    public void setName(String name) {
        this.name = name;
    }

    // Gets person photo
    public Uri getUri() {
        return uri;
    }

}
