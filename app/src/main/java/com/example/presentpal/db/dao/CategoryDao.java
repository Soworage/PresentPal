package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.presentpal.db.Category;

import java.util.List;

/**
 * Das Data Access Object (DAO) für die Kategorie-Entität.
 * Bietet Methoden zur Interaktion mit der Kategorie-Datenbanktabelle.
 */
@Dao
public interface CategoryDao {

    /**
     * Fügt eine neue Kategorie in die Datenbank ein.
     *
     * @param category Das Kategorie-Objekt, das eingefügt werden soll.
     */
    @Insert
    void insert(Category category);

    /**
     * Löscht eine Kategorie anhand ihres Namens aus der Datenbank.
     *
     * @param name Der Name der zu löschenden Kategorie.
     */
    @Query("DELETE FROM category WHERE name = :name")
    void deleteByName(String name);

    /**
     * Ruft alle Kategorien aus der Datenbank ab.
     *
     * @return Eine Liste aller Kategorien.
     */
    @Query("SELECT * FROM category")
    List<Category> getAllCategories();

    /**
     * Ruft alle Kategorien ab, die einer bestimmten Person zugeordnet sind,
     * sortiert nach der Kategorie-ID in absteigender Reihenfolge.
     *
     * @param personId Die ID der Person, deren Kategorien abgerufen werden sollen.
     * @return Eine LiveData-Liste von Kategorien, die der angegebenen Person zugeordnet sind.
     */
    @Query("SELECT categoryId as name FROM personCategory WHERE personId = :personId ORDER BY categoryId DESC")
    LiveData<List<Category>> getAllCategoriesByPerson(int personId);
}
