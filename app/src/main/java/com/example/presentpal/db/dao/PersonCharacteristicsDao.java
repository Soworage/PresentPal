package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.presentpal.db.Characteristics;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PersonCharacteristics;

import java.util.List;

/**
 * Data Access Object (DAO) für den Zugriff auf die Zuordnung zwischen Personen und Charakteristiken.
 */
@Dao
public interface PersonCharacteristicsDao {

    /**
     * Fügt eine neue Zuordnung zwischen einer Person und einer Charakteristik in die Datenbank ein.
     *
     * @param personCharacteristics Das einzufügende PersonCharacteristics-Objekt.
     */
    @Insert
    void insert(PersonCharacteristics personCharacteristics);

    /**
     * Löscht eine Zuordnung zwischen einer Person und einer Charakteristik aus der Datenbank.
     *
     * @param personCharacteristics Das zu löschende PersonCharacteristics-Objekt.
     */
    @Delete
    void delete(PersonCharacteristics personCharacteristics);


}
