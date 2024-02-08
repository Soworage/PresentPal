package com.example.presentpal.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.dao.EventDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventRepository {

    private final EventDao eventDao;

    private LiveData<List<Event>> allEvents;
    private LiveData<List<Person>> allPersons;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public LiveData<List<Person>> getAllPersons() {
        return allPersons;
    }

    public EventRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        eventDao = database.eventDao();
        allPersons = database.personDao().getAllPersons();
    }

    public LiveData<List<Event>> getAllEventsByPerson(Person person) {
        return eventDao.getEventsForPerson(person.getId());
    }

    public void addEvent(String title,String date, Integer personId){
        Event event = new Event(personId, title, date, null, false, null);
        insertEvent(event);
    }

    private void insertEvent(Event event) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                eventDao.insert(event);
            }
        });
    }

}
