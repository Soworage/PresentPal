package com.example.presentpal.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.db.dao.PersonDao;
import com.example.presentpal.db.dao.CategoryDao;
import com.example.presentpal.db.dao.CharacteristicsDao;
import com.example.presentpal.db.dao.EventDao;
import com.example.presentpal.db.dao.PersonCategoryDao;
import com.example.presentpal.db.dao.PresentIdeaDao;
import com.example.presentpal.db.dao.RelationshipDao;
/**
 * Die Hauptdatenbankklasse für die App, die als Zugriffspunkt für die persistente Datenhaltung dient.
 * Diese Klasse definiert die Liste der Entitäten, die in der Datenbank gespeichert werden, und bietet Zugriffsmethoden
 * für die DAOs (Data Access Objects), die für die Interaktion mit den Datenbanktabellen verwendet werden.
 */

@Database(entities = {
        Person.class,
        Category.class,
        Characteristics.class,
        Event.class,
        PersonCategory.class,
        PresentIdea.class,
        Relationship.class,
        LogIn.class
}, version = 10)
public abstract class AppDatabase extends RoomDatabase {

  /**
   * Gibt das DAO für den Zugriff auf Personen-Daten zurück.
   * @return Eine Instanz von {@link PersonDao}.
   */
  public abstract PersonDao personDao();

  /**
   * Gibt das DAO für den Zugriff auf Kategorien-Daten zurück.
   * @return Eine Instanz von {@link CategoryDao}.
   */
  public abstract CategoryDao categoryDao();

  /**
   * Gibt das DAO für den Zugriff auf Merkmale-Daten zurück.
   * @return Eine Instanz von {@link CharacteristicsDao}.
   */
  public abstract CharacteristicsDao characteristicsDao();

  /**
   * Gibt das DAO für den Zugriff auf Event-Daten zurück.
   * @return Eine Instanz von {@link EventDao}.
   */
  public abstract EventDao eventDao();

  /**
   * Gibt das DAO für den Zugriff auf die Zuordnung von Personen zu Kategorien zurück.
   * @return Eine Instanz von {@link PersonCategoryDao}.
   */
  public abstract PersonCategoryDao personCategoryDao();

  /**
   * Gibt das DAO für den Zugriff auf Geschenkideen-Daten zurück.
   * @return Eine Instanz von {@link PresentIdeaDao}.
   */
  public abstract PresentIdeaDao presentIdeaDao();

  /**
   * Gibt das DAO für den Zugriff auf Beziehungen-Daten zurück.
   * @return Eine Instanz von {@link RelationshipDao}.
   */
  public abstract RelationshipDao relationshipDao();

  /**
   * Gibt das DAO für den Zugriff auf Login-Daten zurück.
   * @return Eine Instanz von {@link LogInDao}.
   */
  public abstract LogInDao logInDao();
}
