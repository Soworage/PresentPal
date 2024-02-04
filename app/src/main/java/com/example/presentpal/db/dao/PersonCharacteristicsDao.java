package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import com.example.presentpal.db.Characteristics;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PersonCharacteristics;

import java.util.List;

@Dao
public interface PersonCharacteristicsDao {

    @Insert
    void insert(PersonCharacteristics personCharacteristics);

    @Delete
    void delete(PersonCharacteristics personCharacteristics);

    // um alle Charakteristiken für eine Person zu erhalten
    @Query("SELECT * FROM characteristics INNER JOIN personCharacteristics ON characteristics.id = personCharacteristics.characteristicsId WHERE personCharacteristics.personId = :personId")
    List<Characteristics> getCharacteristicsForPerson(int personId);

    // um alle Personen für eine bestimmte Charakteristik zu erhalten
    @Query("SELECT * FROM person INNER JOIN personCharacteristics ON person.id = personCharacteristics.personId WHERE personCharacteristics.characteristicsId = :characteristicsId")
    List<Person> getPersonsForCharacteristics(int characteristicsId);
}
