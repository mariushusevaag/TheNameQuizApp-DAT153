package com.example.thenamequizapp.classes;

import android.net.Uri;

public class Person {

    private String name;
    private int intImg;
    private Uri uri;

    public Person(String name, int intImg) {
        this.name = name;
        this.intImg = intImg;
    }

    public Person(String name, Uri uri) {
        this.name = name;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIntImg() {
        return intImg;
    }

    public Uri getUri() {
        return uri;
    }

}
