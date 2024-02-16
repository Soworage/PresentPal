package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.presentpal.db.Category;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PersonCategory;
import java.util.List;

/**
 * Data Access Object (DAO) für den Zugriff auf die Zuordnung zwischen Personen und Kategorien.
 */
@Dao
public interface PersonCategoryDao {

    /**
     * Fügt eine neue Zuordnung zwischen einer Person und einer Kategorie in die Datenbank ein.
     *
     * @param personCategory Das einzufügende PersonCategory-Objekt.
     * @return Die ID des eingefügten Objekts.
     */
    @Insert
    long insert(PersonCategory personCategory);

    /**
     * Löscht eine Zuordnung zwischen einer Person und einer Kategorie aus der Datenbank.
     *
     * @param personCategory Das zu löschende PersonCategory-Objekt.
     */
    @Delete
    void delete(PersonCategory personCategory);




}
