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
import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.db.dao.PersonDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonRepository {

    private final PersonDao personDao;
    private final LogInDao logInDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public PersonRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        personDao = database.personDao();
        logInDao = database.logInDao();

    }

    public long addPerson(String firstname, String lastname, String nickname) {
        Person person = new Person(firstname, lastname, nickname, false);
        return insertPerson(person);
    }

    public void updatePerson(Person person){
        executor.execute(new Runnable() {
            @Override
            public void run(){ personDao.update(person);}
        });
    }

    public LiveData<Person> getPersonById(int id){
        return personDao.getPersonById(id);
    }

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


    public interface DataOperationCallback { //für überprüfen ob es geklappt hat für user adden
        void onCompleted(boolean success);
    }


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

    public LiveData<List<Person>> getAllPersons(){
        return personDao.getAllPersons();
    }

    public LiveData<List<Person>> getAllPersonsByCategory(Category category){
        return personDao.getAllPersonsByCategory(category.name);
    }
}
