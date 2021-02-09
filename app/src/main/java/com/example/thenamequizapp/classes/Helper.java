package com.example.thenamequizapp.classes;

import android.net.Uri;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "helper")
public class Helper {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "started")
    private boolean hasStarted;

    @ColumnInfo(name = "last score")
    private int lastScore;

    @ColumnInfo(name = "possible score")
    private int totalScorePossible;

    public Helper() {
        this.hasStarted = false;
        this.lastScore = 0;
        this.totalScorePossible = 0;
    }

    public Helper(Boolean bool, int lastScore, int possibleScore) {
        this.hasStarted = bool;
        this.lastScore = lastScore;
        this.totalScorePossible = possibleScore;
    }

    public void setHasStarted(Boolean bool) {
        this.hasStarted = bool;
    }

    public Boolean getHasStarted() {
        return hasStarted;
    }

    public void setLastScore(int score) {
        this.lastScore = score;
    }

    public int getLastScore() {
        return lastScore;
    }

    public void setTotalScorePossible(int score) {
        this.totalScorePossible = score;
    }

    public int getTotalScorePossible() {
        return totalScorePossible;
    }

}
