package com.example.thenamequizapp.classes;

import android.media.Image;

public class Person {
//
//    -Finn ut hvordan man får inn bilder
//    -Ordne klassen for bilder

    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Image getImage() {
//        return image;
//    }
//
//    public void setImage(Image image) {
//        this.image = image;
//    }

}
