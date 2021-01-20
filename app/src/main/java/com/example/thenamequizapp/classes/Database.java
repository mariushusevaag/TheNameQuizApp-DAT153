package com.example.thenamequizapp.classes;

import com.example.thenamequizapp.classes.Person;

import java.util.ArrayList;

public class Database {
//    String dbName;
    ArrayList<Person> persons;

    public Database() {
    //    this.dbName = dbName;
    }

  /*  public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
*/
    public ArrayList<Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
    }
}
