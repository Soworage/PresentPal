package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.presentpal.db.LogIn;
import com.example.presentpal.db.User;

@Dao
public interface LogInDao{

    @Insert
    void insert(LogIn logIn);

    @Update
    void update(LogIn logIn);

    @Delete
    void delete(LogIn logIn);

    @Query("SELECT COUNT(password) FROM login WHERE password = :password")
    LiveData<Integer> checkPassword(String password);

    @Query("SELECT COUNT(*) FROM login WHERE password = :password")
    Integer  checkPasswordLiveData(String password); // New method to return MutableLiveData

    @Query("SELECT EXISTS(SELECT 1 FROM login)")
    LiveData<Integer> isPasswordSet();

    @Query("SELECT p.*, l.* FROM person p, login l WHERE p.user = 1 ")
    LiveData<User> getUser();
}
