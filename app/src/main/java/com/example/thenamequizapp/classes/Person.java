package com.example.thenamequizapp.classes;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Person {
//
//    -Finn ut hvordan man får inn bilder
//    -Ordne klassen for bilder

    private String name;
    private Drawable img;

    public Person(String name, Drawable img) {
        this.name = name;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

}
