package com.example.presentpal.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.LogIn;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.db.dao.PersonDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonModel {

    private PersonDao personDao;
    private LiveData<List<Person>> allPersons;
    private LogInDao logInDao;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public PersonModel(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        personDao = database.personDao();
        logInDao = database.logInDao();

    }

    public void addPerson(String firstname, String lastname, String nickname) {
        Person person = new Person(firstname, lastname, nickname, false);
        insertPerson(person);
    }

    public void addUser(String nickname, String password, DataOperationCallback callback) {
        LogIn logIn = new LogIn(password);
        Person user = new Person(null, null, nickname ,true);
        executor.execute(() -> {
            try {
                personDao.insert(user);
                logInDao.insert(logIn);
                // Operation erfolgreich
                callback.onCompleted(true);
            } catch (Exception e) {
                // Operation fehlgeschlagen
                callback.onCompleted(false);
            }
        });
    }

    public interface DataOperationCallback { //für überprüfen ob es geklappt hat für user adden
        void onCompleted(boolean success);
    }


    private void insertPerson(Person person) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                personDao.insert(person);
            }
        });

    }
}
