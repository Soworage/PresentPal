package com.example.presentpal.db;

import android.content.Context;
import androidx.room.Room;

import com.example.presentpal.db.AppDatabase;

public class AppDatabaseClient {

    private static AppDatabaseClient instance;
    private AppDatabase appDatabase;

    private AppDatabaseClient(Context context) {
        // Erstellt die Datenbank hier mit Room.databaseBuilder()
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "presentpal_database")
                .fallbackToDestructiveMigration() // Die Zeile soll man wohl immer machen, um bei updates neu zu erstellen und nicht zu migrieren soll fehler auffangen laut google
                .build();
    }

    public static synchronized AppDatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabaseClient(context);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
