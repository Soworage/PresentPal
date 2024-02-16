package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.db.EventPlus;
import java.util.List;

/**
 * Data Access Object (DAO) für den Zugriff auf Ereignisse (Events) in der Datenbank.
 */
@Dao
public interface EventDao {

    /**
     * Fügt ein neues Event in die Datenbank ein und gibt die ID des eingefügten Events zurück.
     *
     * @param event Das einzufügende Event.
     * @return Die ID des eingefügten Events.
     */
    @Insert
    long insert(Event event);

    /**
     * Aktualisiert ein bestehendes Event in der Datenbank.
     *
     * @param event Das zu aktualisierende Event.
     */
    @Update
    void update(Event event);

    /**
     * Löscht ein Event aus der Datenbank.
     *
     * @param event Das zu löschende Event.
     */
    @Delete
    void delete(Event event);

    /**
     * Ruft alle Events aus der Datenbank ab.
     *
     * @return Eine LiveData-Liste aller Events.
     */
    @Query("SELECT * FROM event")
    LiveData<List<Event>> getAllEvents();


    /**
     * Ruft ein Event anhand seiner ID ab.
     *
     * @param eventId Die ID des Events.
     * @return Ein LiveData-Objekt des gesuchten Events.
     */
    @Query("SELECT * FROM event WHERE eid = :eventId")
    LiveData<Event> getEventById(int eventId);

    /**
     * Ruft alle offenen Events ab, die einer bestimmten Kategorie zugeordnet sind.
     *
     * @param categoryId Die ID der Kategorie.
     * @return Eine Liste aller EventJoinPerson-Objekte, die der angegebenen Kategorie zugeordnet sind.
     */
    @Query("SELECT e.*, p.*  FROM event e INNER JOIN person p ON e.personId = p.id INNER JOIN personCategory pc ON p.id = pc.personId WHERE pc.categoryId = :categoryId AND e.closed = 0 ORDER BY e.personId ASC, e.dateSortable ASC")
    List<EventJoinPerson> getAllEventsWithPersonByCategory(String categoryId);

    /**
     * Ruft alle offenen Events mit den zugehörigen Personen ab.
     *
     * @return Eine Liste aller EventJoinPerson-Objekte.
     */
    @Query("SELECT e.*, p.*  FROM event e INNER JOIN person p ON e.personId = p.id WHERE e.closed = 0 ORDER BY e.personId ASC, e.dateSortable ASC")
    List<EventJoinPerson> getAllEventsWithPerson();

    /**
     * Ruft alle bevorstehenden offenen Events ab.
     *
     * @return Eine LiveData-Liste aller bevorstehenden EventJoinPerson-Objekte.
     */
    @Query("SELECT * FROM event INNER JOIN person ON event.personID = person.id WHERE event.closed = 0 ORDER BY dateSortable ASC")
    LiveData<List<EventJoinPerson>> getUpcomingEvents();

    /**
     * Ruft alle Events einer Person ab, inklusive der Anzahl an Ideen und Geschenken.
     *
     * @param personId Die ID der Person.
     * @return Eine LiveData-Liste von EventPlus-Objekten, die Events zusammen mit der Anzahl an Ideen und Geschenken enthält.
     */
    @Query("SELECT  \n" +
            "    e.*,\n" +
            "     (SELECT COUNT(*) FROM presentIdea pi1 WHERE pi1.personId = :personId AND pi1.eventId is Null OR pi1.eventId = e.eid AND pi1.isPresent = 0 ) as ideas,  \n" +
            "      (SELECT COUNT(*) FROM presentIdea pi2 WHERE pi2.personId = :personId AND pi2.eventId = e.eid AND pi2.isPresent = 1) as presents \n" +
            "FROM event e\n" +
            "WHERE e.personId = :personId")
    LiveData<List<EventPlus>> getAllEventsByPerson(int personId);
}
