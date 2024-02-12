package com.example.presentpal.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.presentpal.db.Person;
import com.example.presentpal.db.Category;
import com.example.presentpal.db.Characteristics;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.PersonCategory;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.db.Relationship;


import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.db.dao.PersonDao;
import com.example.presentpal.db.dao.CategoryDao;
import com.example.presentpal.db.dao.CharacteristicsDao;
import com.example.presentpal.db.dao.EventDao;
import com.example.presentpal.db.dao.PersonCategoryDao;
import com.example.presentpal.db.dao.PresentIdeaDao;
import com.example.presentpal.db.dao.RelationshipDao;

@Database(entities = {
        Person.class,
        Category.class,
        Characteristics.class,
        Event.class,
        PersonCategory.class,
        PresentIdea.class,
        Relationship.class,
        LogIn.class
}, version = 4)
public abstract class AppDatabase extends RoomDatabase {
  //Abstract für jedes Dao
    public abstract PersonDao personDao();
    public abstract CategoryDao categoryDao();
    public abstract CharacteristicsDao characteristicsDao();
    public abstract EventDao eventDao();
    public abstract PersonCategoryDao personCategoryDao();
    public abstract PresentIdeaDao presentIdeaDao();
    public abstract RelationshipDao relationshipDao();
    public abstract LogInDao logInDao();


}
