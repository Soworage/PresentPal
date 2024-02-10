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
import com.example.presentpal.db.dao.EventDao;
import com.example.presentpal.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        Event event = new Event(personId, title, date, null, 0, null);
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

   /* public LiveData<List<EventJoinPerson>> getAllEventsWithPersonByCategory(Category category){
        return eventDao.getAllEventsWithPersonByCategory(category.name);
    }*/

    /*public LiveData<List<EventJoinPerson>> getAllEventsWithPerson(){
        return eventDao.getAllEventsWithPerson();
    }*/


    public List<CategoryViewModel.PersonWithEvents> getAllPersonsWithEventsByCategory(Category category){


        List<CategoryViewModel.PersonWithEvents> returnList = new ArrayList<>();
        Log.d("TAG", "checkPassword: test start");

        Future<List<EventJoinPerson>> future = executor.submit(() -> {
            if(category != null){

            List<EventJoinPerson> returnListWithC = eventDao.getAllEventsWithPersonByCategory(category.name);
            Log.d("EventRepo", "with Category");
                return returnListWithC;
            }
            else {
                List<EventJoinPerson> returnListWithoutC = eventDao.getAllEventsWithPerson();
                Log.d("EventRepo", "without Category");
                return returnListWithoutC;
            }
        });

        try {
            List<EventJoinPerson> returnListFuture = future.get();
            String currentName = null;
            List<EventJoinPerson> temporaryList = new ArrayList<>();
            for (EventJoinPerson mainListElement : returnListFuture) {
                if(currentName == null){
                    currentName = mainListElement.person.nickname;
                    temporaryList.add(mainListElement);
                }else if (currentName.equals(mainListElement.person.nickname)){
                    temporaryList.add(mainListElement);
                }else {
                    returnList.add(new CategoryViewModel.PersonWithEvents(temporaryList));
                    temporaryList.clear();
                    temporaryList.add(mainListElement);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("EventRepo", "return");

        return returnList;


    }
}
