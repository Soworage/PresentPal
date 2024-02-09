package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.presentpal.db.LogIn;

@Dao
public interface LogInDao {

    @Insert
    void insert(LogIn logIn);

    @Update
    void update(LogIn logIn);

    @Delete
    void delete(LogIn logIn);

    @Query("SELECT 1 FROM login WHERE password = :password")
    LiveData<Boolean> checkPassword(String password);

    @Query("SELECT EXISTS(SELECT 1 FROM login)")
    LiveData<Integer> isPasswordSet();

    @Query("SELECT * FROM login LIMIT 1")
    LiveData<LogIn> getLogIn();
}
