package com.example.thenamequizapp.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.thenamequizapp.classes.Helper;

@Dao
public interface HelperDao {
    @Query("SELECT * FROM helper ORDER BY id DESC LIMIT 1")
    Helper getHelper();

    @Insert
    void updateHelper(Helper helper);
}
