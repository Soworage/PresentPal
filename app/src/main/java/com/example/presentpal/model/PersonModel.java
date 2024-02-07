package com.example.presentpal.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.dao.PersonDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PersonModel {

    private PersonDao personDao;
    private LiveData<List<Person>> allPersons;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public PersonModel(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        personDao = database.personDao();
    }

    public void addPerson(String firstname, String lastname, String nickname){
        Person person = new Person(firstname, lastname, nickname, false);
        insertPerson(person);
    }

    public void addUser(String firstname, String lastname, String nickname){
        Person user = new Person(firstname, lastname, nickname, true);
        insertPerson(user);
    }

    private void insertPerson(Person person){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                personDao.insert(person);
            }
        });

    }
}
