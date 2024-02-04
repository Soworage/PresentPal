package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.presentpal.db.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Query("DELETE FROM category WHERE name = :name")
    void deleteByName(String name);

    @Query("SELECT * FROM category")
    List<Category> getAllCategories();
}
