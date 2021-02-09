package com.example.thenamequizapp.classes;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person")
public class Person {
    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "name")
    @NonNull
    public String name;

    @ColumnInfo(name = "uri")
    @NonNull
    public Uri uri;

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
