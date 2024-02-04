package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.presentpal.db.Relationship;

import java.util.List;

@Dao
public interface RelationshipDao {

    @Insert
    void insert(Relationship relationship);

    @Delete
    void delete(Relationship relationship);

    @Query("SELECT * FROM relationship")
    List<Relationship> getAllRelationships();
}
