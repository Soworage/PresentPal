package com.example.presentpal.db;

import android.content.Context;
import androidx.room.Room;

import com.example.presentpal.db.AppDatabase;

/**
 * Ein Singleton-Helferklasse zur Verwaltung der Datenbankzugriffe in der Anwendung.
 * Diese Klasse initialisiert die Room-Datenbank und stellt eine Instanz der AppDatabase zur Verfügung,
 * die für den Zugriff auf die Datenbankoperationen genutzt wird.
 */
public class AppDatabaseClient {

    // Einzige Instanz der Klasse
    private static AppDatabaseClient instance;
    // Instanz der AppDatabase
    private AppDatabase appDatabase;

    /**
     * Privater Konstruktor zur Initialisierung der AppDatabase mit dem Kontext der Anwendung.
     * @param context Der Kontext der Anwendung.
     */
    private AppDatabaseClient(Context context) {
        // Initialisiert die Room-Datenbank mit dem Anwendungskontext und dem Namen der Datenbank.
        // Bei Versionskonflikten führt fallbackToDestructiveMigration() dazu, dass die Datenbank neu erstellt wird,
        // anstatt zu migrieren. Dies kann Datenverlust verursachen, erleichtert jedoch die Entwicklung und das Testing.
        appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "presentpal_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    /**
     * Gibt die Singleton-Instanz von AppDatabaseClient zurück. Erstellt die Instanz, falls sie noch nicht existiert.
     * @param context Der Kontext der Anwendung.
     * @return Die Singleton-Instanz von AppDatabaseClient.
     */
    public static synchronized AppDatabaseClient getInstance(Context context) {
        if (instance == null) {
            instance = new AppDatabaseClient(context);
        }
        return instance;
    }

    /**
     * Gibt die AppDatabase-Instanz für den Zugriff auf die Datenbankoperationen zurück.
     * @return Die Instanz von AppDatabase.
     */
    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}