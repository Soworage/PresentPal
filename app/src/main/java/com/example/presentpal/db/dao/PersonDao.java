package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.presentpal.db.Person;

import java.util.List;

/**
 * Data Access Object (DAO) für den Zugriff auf Personeninformationen in der Datenbank.
 */
@Dao
public interface PersonDao {

    /**
     * Fügt eine neue Person in die Datenbank ein.
     *
     * @param person Das einzufügende Person-Objekt.
     * @return Die ID der eingefügten Person.
     */
    @Insert
    long insert(Person person);

    /**
     * Aktualisiert eine bestehende Person in der Datenbank.
     *
     * @param person Das zu aktualisierende Person-Objekt.
     */
    @Update
    void update(Person person);

    /**
     * Löscht eine Person aus der Datenbank.
     *
     * @param person Das zu löschende Person-Objekt.
     */
    @Delete
    void delete(Person person);

    /**
     * Ruft alle Personen aus der Datenbank ab.
     *
     * @return Eine Liste aller Personen als LiveData.
     */
    @Query("SELECT * FROM person")
    LiveData<List<Person>> getAllPersons();

    /**
     * Ruft eine Person anhand ihrer ID ab.
     *
     * @param id Die ID der Person.
     * @return Die abgerufene Person als LiveData.
     */
    @Query("SELECT * FROM person WHERE id = :id")
    LiveData<Person> getPersonById(int id);

    /**
     * Sucht die Personendaten des aktuell eingeloggten Benutzers.
     *
     * @return Die Daten des eingeloggten Benutzers.
     */
    @Query("SELECT * FROM person WHERE user = 1")
    Person getUser();

    /**
     * Sucht eine Person anhand ihres Nicknamens.
     *
     * @param nickname Der Nickname der Person.
     * @return Die abgerufene Person als LiveData.
     */
    @Query("SELECT * FROM person WHERE nickname = :nickname LIMIT 1")
    LiveData<Person> findUserByNickname(String nickname);

    /**
     * Ruft alle Personen ab, die einer bestimmten Kategorie zugeordnet sind.
     *
     * @param category Die Kategorie, nach der gefiltert werden soll.
     * @return Eine Liste der Personen in dieser Kategorie als LiveData.
     */
    @Query("SELECT person.* FROM person INNER JOIN personCategory ON personId = id WHERE categoryId = :category")
    LiveData<List<Person>> getAllPersonsByCategory(String category);
}
