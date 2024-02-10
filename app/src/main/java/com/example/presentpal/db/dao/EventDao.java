package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventJoinPerson;

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
}

