package com.example.presentpal.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import com.example.presentpal.db.Category;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PersonCategory;

import java.util.List;

@Dao
public interface PersonCategoryDao {

    @Insert
    void insert(PersonCategory personCategory);

    @Delete
    void delete(PersonCategory personCategory);

    // Abfrage, um alle Kategorien für eine bestimmte Person zu erhalten
    @Query("SELECT * FROM category INNER JOIN personCategory ON category.name = personCategory.categoryId WHERE personCategory.personId = :personId")
    List<Category> getCategoriesForPerson(int personId);

    // Abfrage, um alle Kategorien für eine bestimmte Person zu erhalten bisschen zum testen
    @Query("SELECT * FROM person INNER JOIN personCategory ON person.id = personCategory.personId WHERE personCategory.categoryId = :categoryId")
    List<Person> getPersonsForCategory(String categoryId);
}
