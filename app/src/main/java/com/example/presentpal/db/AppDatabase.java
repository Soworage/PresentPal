package com.example.presentpal.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

// Fügen Sie hier alle Ihre Entitäten hinzu
@Database(entities = {Person.class, Event.class, PresentIdea.class, Characteristic.class, Category.class, PersonCharacteristic.class, PersonCategory.class}, version = 1)
@TypeConverters({DateConverter.class}) // Beispiel für einen Typkonverter, falls Sie Datumsobjekte verwenden
public abstract class AppDatabase extends RoomDatabase {
    // DAOs für jede Entität bzw. für jede Beziehung
//    public abstract PersonDao personDao();
//    public abstract EventDao eventDao();
//    public abstract PresentIdeaDao presentIdeaDao();
//    public abstract CharacteristicDao characteristicDao();
//    public abstract CategoryDao categoryDao();
//    public abstract PersonCharacteristicDao personCharacteristicDao();
//    public abstract PersonCategoryDao personCategoryDao();

    // Weitere DAOs oder Datenbank-bezogene Methoden können hier definiert werden
}
