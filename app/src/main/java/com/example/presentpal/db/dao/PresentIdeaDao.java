package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.db.PresentIdeaJoinPerson;

import java.util.List;

/**
 * Interface für den Datenzugriff auf die Tabelle der Geschenkideen in der Datenbank.
 * Ermöglicht das Einfügen, Aktualisieren, Löschen und Abfragen von Geschenkideen.
 */
@Dao
public interface PresentIdeaDao {

    /**
     * Fügt eine neue Geschenkidee in die Datenbank ein.
     *
     * @param presentIdea Das Objekt der Geschenkidee, das eingefügt werden soll.
     * @return Die ID der neu eingefügten Geschenkidee.
     */
    @Insert
    long insert(PresentIdea presentIdea);

    /**
     * Aktualisiert eine vorhandene Geschenkidee in der Datenbank.
     *
     * @param presentIdea Das Objekt der Geschenkidee, das aktualisiert werden soll.
     */
    @Update
    void update(PresentIdea presentIdea);

    /**
     * Löscht eine Geschenkidee aus der Datenbank.
     *
     * @param presentIdea Das Objekt der Geschenkidee, das gelöscht werden soll.
     */
    @Delete
    void delete(PresentIdea presentIdea);

    /**
     * Ruft alle Geschenkideen aus der Datenbank ab.
     *
     * @return Eine Liste aller Geschenkideen.
     */
    @Query("SELECT * FROM presentidea")
    List<PresentIdea> getAllPresentIdeas();

    /**
     * Ruft Geschenkideen für eine bestimmte Person ab.
     *
     * @param personId Die ID der Person, für die die Geschenkideen abgerufen werden sollen.
     * @return Eine LiveData-Liste von Geschenkideen, die zu der angegebenen Person gehören.
     */
    @Query("SELECT * FROM presentidea WHERE personId = :personId")
    LiveData<List<PresentIdea>> getPresentIdeasForPerson(int personId);

    /**
     * Ruft Geschenkideen ab, die einer bestimmten Person und/oder einem Event zugeordnet sind.
     *
     * @param eventId  Die ID des Events.
     * @param personId Die ID der Person.
     * @return Eine LiveData-Liste von Geschenkideen für die angegebene Person und/oder das Event.
     */
    @Query("SELECT * FROM presentidea WHERE eventId = :eventId OR eventId isNull AND personId = :personId")
    LiveData<List<PresentIdea>> getPresentIdeasByEvent(int eventId, int personId);

    /**
     * Ruft alle Geschenkideen ab, die einer bestimmten Person zugeordnet sind und für ein bestimmtes Event vorgesehen sind, einschließlich der Personendaten.
     *
     * @param personId Die ID der Person.
     * @param eventId  Die ID des Events.
     * @return Eine Liste von Geschenkideen mit zugehörigen Personendaten, gefiltert nach Person und Event.
     */
    @Query("SELECT pi.*, p.* FROM presentIdea pi INNER JOIN person p ON pi.personId = p.id WHERE p.id = :personId AND pi.isPresent = 0 AND (pi.eventId is null OR pi.eventId = :eventId) ORDER BY pi.eventId DESC")
    List<PresentIdeaJoinPerson> getAllPresentIdeasWithPersonByPersonByEvent(int personId, int eventId);

    /**
     * Ruft alle realisierten Geschenke ab, die einer bestimmten Person und einem spezifischen Event zugeordnet sind, einschließlich der Personendaten.
     *
     * @param personId Die ID der Person.
     * @param eventId  Die ID des Events.
     * @return Eine Liste von realisierten Geschenken mit zugehörigen Personendaten, gefiltert nach Person und Event.
     */
    @Query("SELECT pi.*, p.* FROM presentIdea pi INNER JOIN person p ON pi.personId = p.id WHERE p.id = :personId AND pi.eventId = :eventId AND pi.isPresent = 1 ORDER BY pi.title ASC")
    List<PresentIdeaJoinPerson> getAllPresentsWithPersonByPersonByEvent(int personId, int eventId);

    /**
     * Ruft eine spezifische Geschenkidee anhand ihrer ID ab.
     *
     * @param presentIdeaId Die ID der Geschenkidee.
     * @return Die abgerufene Geschenkidee als LiveData.
     */
    @Query("SELECT * FROM presentIdea WHERE piid = :presentIdeaId")
    LiveData<PresentIdea> getPresentIdeaById(int presentIdeaId);
}