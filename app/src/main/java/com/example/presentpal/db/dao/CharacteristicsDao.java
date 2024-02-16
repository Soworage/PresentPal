package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.presentpal.db.Characteristics;
import java.util.List;

/**
 * Data Access Object (DAO) für den Zugriff auf die Merkmale (Characteristics) in der Datenbank.
 */
@Dao
public interface CharacteristicsDao {

    /**
     * Fügt neue Merkmale in die Datenbank ein.
     *
     * @param characteristics Das einzufügende Characteristics-Objekt.
     */
    @Insert
    void insert(Characteristics characteristics);

    /**
     * Aktualisiert ein bestehendes Characteristics-Objekt in der Datenbank.
     *
     * @param characteristics Das zu aktualisierende Characteristics-Objekt.
     */
    @Update
    void update(Characteristics characteristics);

    /**
     * Löscht ein Characteristics-Objekt aus der Datenbank.
     *
     * @param characteristics Das zu löschende Characteristics-Objekt.
     */
    @Delete
    void delete(Characteristics characteristics);


}
