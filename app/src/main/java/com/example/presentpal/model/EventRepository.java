package com.example.presentpal.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.Category;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.db.EventPlus;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.dao.CategoryDao;
import com.example.presentpal.db.dao.EventDao;
import com.example.presentpal.db.dao.PersonDao;
import com.example.presentpal.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Repository-Klasse für die Verwaltung von Event-Datenoperationen.
 * Diese Klasse bietet eine saubere API für den Rest der App für den Zugriff auf App-Daten.
 */
public class EventRepository {

    private final EventDao eventDao;
    private final PersonDao personDao;
    private final CategoryDao categoryDao;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * Konstruktor, der das Repository mit Instanzen der Datenzugriffsobjekte initialisiert.
     *
     * @param application Der Anwendungskontext, der verwendet wird, um die Datenbankinstanz zu erhalten.
     */
    public EventRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        eventDao = database.eventDao();
        personDao = database.personDao();
        categoryDao = database.categoryDao();
    }

    /**
     * Ruft alle Events ab, die mit einer bestimmten Person verknüpft sind.
     *
     * @param personId Die ID der Person.
     * @return LiveData-Liste von EventPlus-Objekten für Echtzeit-Updates in der UI.
     */
    public LiveData<List<EventPlus>> getAllEventsByPerson(int personId) {

        return eventDao.getAllEventsByPerson(personId);

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
     * Ruft eine Person anhand ihrer ID ab.
     *
     * @param id Die ID der Person.
     * @return LiveData der angeforderten Person.
     */
    public LiveData<Person> getPersonById(int id) {
        return personDao.getPersonById(id);
    }

    /**
     * Fügt ein neues Event hinzu.
     *
     * @param title    Der Titel des Events.
     * @param date     Das Datum des Events.
     * @param personId Die ID der Person, die mit dem Event verknüpft ist.
     * @return Die ID des neu eingefügten Events.
     */
    public long addEvent(String title, String date, Integer personId) {
        Event event = new Event(personId, title, date, Event.dateToInteger(date), null, 0, null, 0f);
        return insertEvent(event);
    }

    /**
     * Aktualisiert ein vorhandenes Event.
     *
     * @param event Das Event-Objekt mit den aktualisierten Informationen.
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
     * Fügt ein Event-Objekt in die Datenbank ein und gibt den Einfüge-ID synchron zurück.
     * Diese Methode verwendet einen ExecutorService, um die Einfügeoperation asynchron auszuführen,
     * wartet jedoch auf das Ergebnis, um den generierten ID synchron zurückzugeben.
     *
     * @param event Das Event-Objekt, das in die Datenbank eingefügt werden soll.
     * @return Der generierte Schlüssel (ID) des eingefügten Events. Gibt -1 zurück, wenn ein Fehler auftritt.
     */
    private long insertEvent(Event event) {
        Future<Long> future = executor.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                return eventDao.insert(event);
            }
        });

        try {
            // Warte auf das Ergebnis des Callable und gibt es zurück.
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();

            return -1; // Fe
        }
    }

    /**
     * Ruft alle Personen mit ihren Events ab, gefiltert nach einer bestimmten Kategorie.
     *
     * @param category Die Kategorie, nach der gefiltert werden soll.
     * @return Eine Liste von PersonWithEvents, die die gefilterten Daten enthält.
     */
    public List<CategoryViewModel.PersonWithEvents> getAllPersonsWithEventsByCategory(String category) {


        List<CategoryViewModel.PersonWithEvents> returnList = new ArrayList<>();
        Log.d("TAG", "checkPassword: test start");

        Future<List<EventJoinPerson>> future = executor.submit(() -> {
            if (category != null) {

                List<EventJoinPerson> returnListWithC = eventDao.getAllEventsWithPersonByCategory(category);
                Log.d("EventRepo", "with Category");
                return returnListWithC;
            } else {
                List<EventJoinPerson> returnListWithoutC = eventDao.getAllEventsWithPerson();
                Log.d("EventRepo", "without Category");
                Log.d("EventRepo", returnListWithoutC.get(0).person.nickname);
                return returnListWithoutC;
            }
        });

        try {
            List<EventJoinPerson> returnListFuture = future.get();
            Log.d("EventRepo-Future", "size: " + returnListFuture.size());
            String currentName = null;
            List<EventJoinPerson> temporaryList = new ArrayList<>();
            for (int i = 0; i < returnListFuture.size(); i++) {
                Log.d("EventRepo-Future", "Element: " + returnListFuture.get(i).event.title);
                if (currentName == null) {
                    currentName = returnListFuture.get(i).person.nickname;
                    temporaryList.add(returnListFuture.get(i));
                    Log.d("EventRepo-Future", "TemporaryList size: " + temporaryList.size());
                } else if (currentName.equals(returnListFuture.get(i).person.nickname)) {
                    temporaryList.add(returnListFuture.get(i));
                    Log.d("EventRepo-Future", "TemporaryList size: " + temporaryList.size());
                } else {
                    returnList.add(new CategoryViewModel.PersonWithEvents(temporaryList));
                    temporaryList.clear();
                    currentName = returnListFuture.get(i).person.nickname;
                    temporaryList.add(returnListFuture.get(i));
                }
                if (i == returnListFuture.size() - 1) {
                    returnList.add(new CategoryViewModel.PersonWithEvents(temporaryList));
                }

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("EventRepo", "return");
        if (returnList.size() != 0) {
            Log.d("EventRepo", returnList.get(0).getTitleA());
        } else {
            Log.d("EventRepo", "return is empty");
        }

        return returnList;


    }

    /**
     * Ruft bevorstehende Events ab.
     *
     * @return LiveData-Liste von EventJoinPerson für bevorstehende Events.
     */
    public LiveData<List<EventJoinPerson>> getUpcomingEvents() {
        return eventDao.getUpcomingEvents();
    }

    /**
     * Ruft Kategorien ab, die einer bestimmten Person zugeordnet sind.
     *
     * @param personId Die ID der Person.
     * @return LiveData-Liste von Kategorien, die der Person zugeordnet sind.
     */
    public LiveData<List<Category>> getCategoryByPerson(int personId) {
        return categoryDao.getAllCategoriesByPerson(personId);
    }
}
