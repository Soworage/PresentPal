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


public class PresentIdeaRepository {

    private final PresentIdeaDao presentIdeaDao;

    private final PersonDao personDao;

    public final EventDao eventDao;

    private ExecutorService executor = Executors.newSingleThreadExecutor();


    public PresentIdeaRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        presentIdeaDao = database.presentIdeaDao();
        personDao = database.personDao();
        eventDao = database.eventDao();
    }

    public LiveData<List<PresentIdea>> getAllPresentIdeasByEvent(Event event, Person person) {
        return presentIdeaDao.getPresentIdeasByEvent(event.getId(), person.getId());
    }


    public long addPresentIdea(int personId, String title, String shortDescription) {
        PresentIdea presentIdea = new PresentIdea(personId, null, title, "", shortDescription, 0f, null, false);
        return insertPresentIdea(presentIdea);
    }

    public void updatePresentIdea(PresentIdea presentIdea){
        executor.execute(new Runnable() {
            @Override
            public void run(){ presentIdeaDao.update(presentIdea);}
        });
    }

    public void updateEvent(Event event){
        executor.execute(new Runnable() {
            @Override
            public void run(){ eventDao.update(event);}
        });
    }

    public LiveData<Event> getEventById(int eid){
        return eventDao.getEventById(eid);
    }

    public LiveData<List<Person>> getAllPersons() {
        return personDao.getAllPersons();
    }

    public LiveData<Person> getPersonById(int id){return personDao.getPersonById(id);}
    private long insertPresentIdea(PresentIdea presentIdea) {

        final long[] returnValue = new long[1];
        executor.execute(new Runnable() {
            @Override
            public void run() {
                returnValue[0] =presentIdeaDao.insert(presentIdea);
            }
        });

        return returnValue[0];
    }

    public LiveData<PresentIdea> getPresentIdeaById(int presentIdeaId){
        return presentIdeaDao.getPresentIdeaById(presentIdeaId);
    }

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
