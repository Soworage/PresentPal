package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.presentpal.db.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    long insert(Person person);

    @Update
    void update(Person person);

    @Delete
    void delete(Person person);

    @Query("SELECT * FROM person")
    List<Person> getAllPersons();

    // person nach ID finden
    @Query("SELECT * FROM person WHERE id = :id")
    Person getPersonById(int id);


}
