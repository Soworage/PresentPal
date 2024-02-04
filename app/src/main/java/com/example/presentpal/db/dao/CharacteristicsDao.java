package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.presentpal.db.Characteristics;

import java.util.List;

@Dao
public interface CharacteristicsDao {

    @Insert
    void insert(Characteristics characteristics);

    @Update
    void update(Characteristics characteristics);

    @Delete
    void delete(Characteristics characteristics);

    @Query("SELECT * FROM characteristics")
    List<Characteristics> getAllCharacteristics();


}
