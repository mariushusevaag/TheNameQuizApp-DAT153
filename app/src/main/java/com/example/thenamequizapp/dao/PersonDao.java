package com.example.thenamequizapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.thenamequizapp.classes.Person;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM person")
    List<Person> getPersons();

    @Insert
    void addPerson(Person person);

    @Delete
    void removePerson(Person person);
}
