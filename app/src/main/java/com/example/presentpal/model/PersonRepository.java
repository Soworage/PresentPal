package com.example.presentpal.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.Category;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.LogIn;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PersonCategory;
import com.example.presentpal.db.dao.CategoryDao;
import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.db.dao.PersonCategoryDao;
import com.example.presentpal.db.dao.PersonDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Verwaltet den Zugriff auf Personen-bezogene Datenquellen und führt asynchrone Operationen aus.
 */
public class PersonRepository {

    private final PersonDao personDao;
    private final LogInDao logInDao;

    private final PersonCategoryDao personCategoryDao;

    private final CategoryDao categoryDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Konstruktor für PersonRepository.
     * Initialisiert die Datenzugriffsobjekte (DAOs) für die Arbeit mit der Datenbank.
     *
     * @param application Die Anwendungsinstanz, die für den Datenbankzugriff benötigt wird.
     */
    public PersonRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        personDao = database.personDao();
        logInDao = database.logInDao();
        categoryDao = database.categoryDao();
        personCategoryDao = database.personCategoryDao();

    }

    /**
     * Fügt eine neue Person hinzu.
     *
     * @param firstname Der Vorname der Person.
     * @param lastname  Der Nachname der Person.
     * @param nickname  Der Spitzname der Person.
     * @return Die ID der neu eingefügten Person.
     */
    public long addPerson(String firstname, String lastname, String nickname) {
        Person person = new Person(firstname, lastname, nickname, false);
        return insertPerson(person);
    }

    /**
     * Aktualisiert die Daten einer Person in der Datenbank.
     *
     * @param person Das Person-Objekt mit den aktualisierten Daten.
     */
    public void updatePerson(Person person) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                personDao.update(person);
            }
        });
    }

    /**
     * Fügt eine neue Person-Kategorie-Beziehung hinzu.
     *
     * @param personId Die ID der Person.
     * @param category Die Kategorie.
     * @return Die ID der neu eingefügten Person-Kategorie-Beziehung.
     */
    public long insertPersonCatergory(int personId, String category) {

        final long[] returnValue = new long[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                returnValue[0] = personCategoryDao.insert(new PersonCategory(personId, category));
            }
        });

        return returnValue[0];


    }

    /**
     * Ruft eine Person anhand ihrer ID ab.
     *
     * @param id Die ID der Person.
     * @return LiveData, die die angeforderte Person enthält.
     */
    public LiveData<Person> getPersonById(int id) {
        return personDao.getPersonById(id);
    }

    /**
     * Fügt einen Benutzer hinzu und erstellt gleichzeitig ein Login.
     *
     * @param nickname Der Spitzname des Benutzers.
     * @param password Das Passwort für das Login.
     * @param callback Callback, um den Erfolg der Operation zu kommunizieren.
     */
    public void addUser(String nickname, String password, DataOperationCallback callback) {
        LogIn logIn = new LogIn(password);
        if (nickname == null || nickname.trim().isEmpty()) {
            Log.e("PersonModel", "Nickname ist null oder leer");
            callback.onCompleted(false);
            return;
        }

        Person user = new Person(null, null, nickname, true);
        executor.execute(() -> {
            try {
                personDao.insert(user);
                logInDao.insert(logIn);
                // Operation erfolgreich
                callback.onCompleted(true);
            } catch (Exception e) {
                // Logge die Ausnahme, um den Fehler zu sehen
                Log.e("PersonModel", "Fehler beim Hinzufügen des Benutzers", e);
                // Operation fehlgeschlagen
                callback.onCompleted(false);
            }
        });
    }

    /**
     * Callback-Interface für Datenoperationsrückmeldungen.
     */
    public interface DataOperationCallback { //für überprüfen ob es geklappt hat für user adden
        void onCompleted(boolean success);
    }

    /**
     * Fügt eine Person in die Datenbank ein.
     *
     * @param person Das Person-Objekt, das eingefügt werden soll.
     * @return Die ID der eingefügten Person.
     */

    private long insertPerson(Person person) {

        final long[] returnValue = new long[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                returnValue[0] = personDao.insert(person);
            }
        });

        return returnValue[0];

    }

    /**
     * Ruft alle Personen ab.
     *
     * @return LiveData-Liste aller Personen.
     */
    public LiveData<List<Person>> getAllPersons() {
        return personDao.getAllPersons();
    }

    /**
     * Ruft alle Personen ab, die einer bestimmten Kategorie zugeordnet sind.
     *
     * @param category Die Kategorie, nach der gefiltert werden soll.
     * @return LiveData-Liste der Personen in der angegebenen Kategorie.
     */
    public LiveData<List<Person>> getAllPersonsByCategory(String category) {
        return personDao.getAllPersonsByCategory(category);
    }

    /**
     * Fügt eine neue Kategorie hinzu.
     *
     * @param category Das Kategorie-Objekt, das hinzugefügt werden soll.
     */
    public void addCategory(Category category) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.insert(category);
            }
        });
    }
}
