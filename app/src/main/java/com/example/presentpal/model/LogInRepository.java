package com.example.presentpal.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.User;
import com.example.presentpal.db.dao.LogInDao;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Repository für den Login, welches die Datenzugriffsschicht abstrahiert und eine saubere API für den Datenzugriff bereitstellt.
 */
public class LogInRepository {
    private final LogInDao logInDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Konstruktor, der eine Instanz der LogInRepository-Klasse erstellt.
     *
     * @param application Die Application-Instanz, die verwendet wird, um auf die Datenbank zugreifen zu können.
     */
    public LogInRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        logInDao = database.logInDao();
    }

    /**
     * Gibt den aktuellen Benutzer zurück.
     *
     * @return LiveData<User> Eine LiveData-Instanz, die den aktuellen Benutzer enthält.
     */
    public LiveData<User> getUser() {
        return logInDao.getUser();
    }

    /**
     * Überprüft, ob ein Passwort im System hinterlegt ist.
     *
     * @return LiveData<Integer> Eine LiveData-Instanz, die angibt, ob ein Passwort gesetzt ist oder nicht.
     */
    public LiveData<Integer> isPasswordSetLiveData() {
        return logInDao.isPasswordSet();
    }

    /**
     * Überprüft, ob das übergebene Passwort mit dem in der Datenbank hinterlegten Passwort übereinstimmt.
     *
     * @param password Das zu überprüfende Passwort.
     * @return Integer Der Wert 1, wenn das Passwort korrekt ist, sonst 0.
     */
    public Integer checkPassword(String password) {
        final int[] value = {0};
        Log.d("TAG", "checkPassword: test start");

        Future<Integer> future = executor.submit(() -> {
            int v = logInDao.checkPasswordLiveData(password);
            Log.d("TAG", "checkPassword: " + v);
            return v;
        });

        try {
            value[0] = future.get(); // Blockiert, bis das Ergebnis verfügbar ist
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("TAG", "checkPassword: test end");

        return value[0];
    }
}
