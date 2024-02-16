package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.presentpal.db.Relationship;

import java.util.List;

/**
 * Data Access Object (DAO) für den Zugriff auf Beziehungen zwischen Personen in der Datenbank.
 */
@Dao
public interface RelationshipDao {

    /**
     * Fügt eine neue Beziehung in die Datenbank ein.
     *
     * @param relationship Die einzufügende Beziehung.
     */
    @Insert
    void insert(Relationship relationship);

    /**
     * Löscht eine Beziehung aus der Datenbank.
     *
     * @param relationship Die zu löschende Beziehung.
     */
    @Delete
    void delete(Relationship relationship);

    /**
     * Ruft alle Beziehungen aus der Datenbank ab.
     *
     * @return Eine Liste aller Beziehungen.
     */
    @Query("SELECT * FROM relationship")
    List<Relationship> getAllRelationships();
}

