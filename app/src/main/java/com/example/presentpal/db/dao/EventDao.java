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

@Dao
public interface EventDao {

    @Insert
    long insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("SELECT * FROM event")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM event WHERE personId = :personId")
    LiveData<List<Event>> getEventsForPerson(int personId);

    @Query("SELECT * FROM event WHERE eid = :eventId")
    Event getEventById(int eventId);

    @Query("SELECT e.*, p.*  FROM event e INNER JOIN person p ON e.personId = p.id INNER JOIN personCategory pc ON p.id = pc.personId WHERE pc.categoryId = :categoryId AND e.closed = 0 ORDER BY e.personId ASC, e.date ASC")
    List<EventJoinPerson> getAllEventsWithPersonByCategory(String categoryId);

    @Query("SELECT e.*, p.*  FROM event e INNER JOIN person p ON e.personId = p.id WHERE e.closed = 0 ORDER BY e.personId ASC, e.date ASC")
    List<EventJoinPerson> getAllEventsWithPerson();

        @Query("SELECT * FROM event INNER JOIN person ON event.personID = person.id ORDER BY dateSortable ASC")
        LiveData<List<EventJoinPerson>> getUpcomingEvents();


    @Query("SELECT  \n" +
            "    e.*,\n" +
            "     (SELECT COUNT(*) FROM presentIdea pi1 WHERE pi1.personId = :personId AND pi1.eventId is Null OR pi1.eventId = e.eid AND pi1.isPresent = 0 ) as ideas,  \n" +
            "      (SELECT COUNT(*) FROM presentIdea pi2 WHERE pi2.personId = :personId AND pi2.eventId = e.eid AND pi2.isPresent = 1) as presents \n" +
            "FROM event e\n" +
            "WHERE e.personId = :personId")
    LiveData<List<EventPlus>> getAllEventsByPerson(int personId);
}

