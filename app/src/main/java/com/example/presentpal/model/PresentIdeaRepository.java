package com.example.presentpal.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.Category;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.db.dao.CategoryDao;
import com.example.presentpal.db.dao.EventDao;
import com.example.presentpal.db.dao.PersonDao;
import com.example.presentpal.db.dao.PresentIdeaDao;
import com.example.presentpal.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Repository für die Verwaltung von Geschenkideen.
 */
public class PresentIdeaRepository {

    private final PresentIdeaDao presentIdeaDao;

    private final PersonDao personDao;

    public final EventDao eventDao;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Konstruktor, der eine neue Instanz des PresentIdeaRepository erstellt.
     *
     * @param application Die Anwendungskontext.
     */
    public PresentIdeaRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        presentIdeaDao = database.presentIdeaDao();
        personDao = database.personDao();
        eventDao = database.eventDao();
    }

    /**
     * Gibt alle Geschenkideen für ein bestimmtes Event und eine bestimmte Person zurück.
     *
     * @param event  Das Event, für das die Geschenkideen abgerufen werden sollen.
     * @param person Die Person, für die die Geschenkideen abgerufen werden sollen.
     * @return Eine LiveData-Liste von Geschenkideen.
     */
    public LiveData<List<PresentIdea>> getAllPresentIdeasByEvent(Event event, Person person) {
        return presentIdeaDao.getPresentIdeasByEvent(event.getId(), person.getId());
    }

    /**
     * Fügt eine neue Geschenkidee hinzu.
     *
     * @param personId         Die ID der Person, für die die Geschenkidee hinzugefügt wird.
     * @param title            Der Titel der Geschenkidee.
     * @param shortDescription Eine kurze Beschreibung der Geschenkidee.
     * @return Die ID der neu eingefügten Geschenkidee.
     */
    public long addPresentIdea(int personId, String title, String shortDescription) {
        PresentIdea presentIdea = new PresentIdea(personId, null, title, "", shortDescription, 0f, null, false);
        return insertPresentIdea(presentIdea);
    }

    /**
     * Aktualisiert eine vorhandene Geschenkidee.
     *
     * @param presentIdea Die zu aktualisierende Geschenkidee.
     */
    public void updatePresentIdea(PresentIdea presentIdea) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                presentIdeaDao.update(presentIdea);
            }
        });
    }

    /**
     * Aktualisiert ein vorhandenes Event.
     *
     * @param event Das zu aktualisierende Event.
     */
    public void updateEvent(Event event) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                eventDao.update(event);
            }
        });
    }

    /**
     * Gibt ein Event anhand seiner ID zurück.
     *
     * @param eid Die ID des Events.
     * @return Das Event als LiveData.
     */
    public LiveData<Event> getEventById(int eid) {
        return eventDao.getEventById(eid);
    }

    /**
     * Gibt alle Personen zurück.
     *
     * @return Eine LiveData-Liste von Personen.
     */
    public LiveData<List<Person>> getAllPersons() {
        return personDao.getAllPersons();
    }

    /**
     * Gibt eine Person anhand ihrer ID zurück.
     *
     * @param id Die ID der Person.
     * @return Die Person als LiveData.
     */
    public LiveData<Person> getPersonById(int id) {
        return personDao.getPersonById(id);
    }

    /**
     * Hilfsmethode zum Einfügen einer Geschenkidee.
     *
     * @param presentIdea Die einzufügende Geschenkidee.
     * @return Die ID der eingefügten Geschenkidee.
     */
    private long insertPresentIdea(PresentIdea presentIdea) {

        final long[] returnValue = new long[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                returnValue[0] = presentIdeaDao.insert(presentIdea);
            }
        });

        return returnValue[0];
    }

    /**
     * Gibt eine Geschenkidee anhand ihrer ID zurück.
     *
     * @param presentIdeaId Die ID der Geschenkidee.
     * @return Die Geschenkidee als LiveData.
     */
    public LiveData<PresentIdea> getPresentIdeaById(int presentIdeaId) {
        return presentIdeaDao.getPresentIdeaById(presentIdeaId);
    }

    /**
     * Gibt alle Geschenkideen zusammen mit den Personen für ein bestimmtes Event zurück.
     *
     * @param personId Die ID der Person.
     * @param eventId  Die ID des Events.
     * @return Eine Liste von Geschenkideen und Personen.
     */
    public List<PresentIdeaJoinPerson> getAllPresentIdeasWithPersonByPersonByEvent(int personId, int eventId) {

        List<PresentIdeaJoinPerson> returnList = new ArrayList<>();
        Log.d("TAG", "checkPassword: test start");

        Future<List<PresentIdeaJoinPerson>> future = executor.submit(() -> {
            List<PresentIdeaJoinPerson> returnListWithC = presentIdeaDao.getAllPresentIdeasWithPersonByPersonByEvent(personId, eventId);

            return returnListWithC;
        });

        try {
            returnList = future.get();
            Log.d("PresentIdeaRepo", "get future Ideas");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return returnList;
    }

    /**
     * Gibt alle Geschenke zusammen mit den Personen für ein bestimmtes Event zurück.
     *
     * @param personId Die ID der Person.
     * @param eventId  Die ID des Events.
     * @return Eine Liste von Geschenken und Personen.
     */
    public List<PresentIdeaJoinPerson> getAllPresentWithPersonByPersonByEvent(int personId, int eventId) {
        List<PresentIdeaJoinPerson> returnList = new ArrayList<>();
        Log.d("TAG", "checkPassword: test start");

        Future<List<PresentIdeaJoinPerson>> future = executor.submit(() -> {
            List<PresentIdeaJoinPerson> returnListWithC = presentIdeaDao.getAllPresentsWithPersonByPersonByEvent(personId, eventId);
            Log.d("EventRepo", "with Category");
            return returnListWithC;
        });

        try {
            returnList = future.get();
            Log.d("PresentIdeaRepo", "get future Presents");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return returnList;
    }


}
