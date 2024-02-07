package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.presentpal.db.PresentIdea;

import java.util.List;

@Dao
public interface PresentIdeaDao {

    @Insert
    long insert(PresentIdea presentIdea);

    @Update
    void update(PresentIdea presentIdea);

    @Delete
    void delete(PresentIdea presentIdea);

    @Query("SELECT * FROM presentidea")
    List<PresentIdea> getAllPresentIdeas();

    // Finden von Geschenkideen für eine bestimmte Person
    @Query("SELECT * FROM presentidea WHERE personId = :personId")
    LiveData<List<PresentIdea>> getPresentIdeasForPerson(int personId);

    @Query("SELECT * FROM presentidea WHERE eventId = :eventId OR eventId isNull AND personId = :personId")
    LiveData<List<PresentIdea>> getPresentIdeasByEvent(int eventId, int personId);


}
