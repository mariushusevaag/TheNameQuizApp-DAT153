package com.example.thenamequizapp.classes;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Person {

    private String name;
    private int intImg;
    private Drawable drawImg;

    public Person(String name, int intImg) {
        this.name = name;
        this.intImg = intImg;
    }

    public Person(String name, Drawable drawImg) {
        this.name = name;
        this.drawImg = drawImg;
    }

    public Person(String name) {
        this.name = name;
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

    public void setIntImg(int intImg) {
        this.intImg = intImg;
    }

    public Drawable getDrawImg() {
        return drawImg;
    }

    public void setDrawImg(Drawable drawImg) {
        this.drawImg = drawImg;
    }

}
