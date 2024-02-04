package com.example.presentpal.db.dao;

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
    List<PresentIdea> getPresentIdeasForPerson(int personId);


}
