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
import com.example.presentpal.db.dao.EventDao;
import com.example.presentpal.db.dao.PersonDao;
import com.example.presentpal.viewmodel.CategoryViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class EventRepository {

    private final EventDao eventDao;
    private final PersonDao personDao;



    private ExecutorService executor = Executors.newSingleThreadExecutor();


    public EventRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        eventDao = database.eventDao();
        personDao = database.personDao();
    }

    public LiveData<List<EventPlus>> getAllEventsByPerson(int personId) {

     return eventDao.getAllEventsByPerson(personId);

    }
    public LiveData<List<Person>> getAllPersons() {
        return personDao.getAllPersons();
    }

    public LiveData<Person> getPersonById(int id){
        return personDao.getPersonById(id);
    }


    public void addEvent(String title,String date, Integer personId){
        Event event = new Event(personId, title, date, Event.dateToInteger(date),null, 0, null, 0f);
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

    public List<CategoryViewModel.PersonWithEvents> getAllPersonsWithEventsByCategory(String category){


        List<CategoryViewModel.PersonWithEvents> returnList = new ArrayList<>();
        Log.d("TAG", "checkPassword: test start");

        Future<List<EventJoinPerson>> future = executor.submit(() -> {
            if(category != null){

            List<EventJoinPerson> returnListWithC = eventDao.getAllEventsWithPersonByCategory(category);
            Log.d("EventRepo", "with Category");
                return returnListWithC;
            }
            else {
                List<EventJoinPerson> returnListWithoutC = eventDao.getAllEventsWithPerson();
                Log.d("EventRepo", "without Category");
                Log.d("EventRepo", returnListWithoutC.get(0).person.nickname);
                return returnListWithoutC;
            }
        });

        try {
            List<EventJoinPerson> returnListFuture = future.get();
            Log.d("EventRepo-Future", "size: "+ returnListFuture.size());
            String currentName = null;
            List<EventJoinPerson> temporaryList = new ArrayList<>();
            for (int i = 0; i < returnListFuture.size(); i++) {
                Log.d("EventRepo-Future", "Element: "+ returnListFuture.get(i).event.title);
                if(currentName == null){
                    currentName = returnListFuture.get(i).person.nickname;
                    temporaryList.add(returnListFuture.get(i));
                    Log.d("EventRepo-Future", "TemporaryList size: "+ temporaryList.size());
                }else if (currentName.equals(returnListFuture.get(i).person.nickname)){
                    temporaryList.add(returnListFuture.get(i));
                    Log.d("EventRepo-Future", "TemporaryList size: "+ temporaryList.size());
                }else {
                    returnList.add(new CategoryViewModel.PersonWithEvents(temporaryList));
                    temporaryList.clear();
                    currentName =returnListFuture.get(i).person.nickname;
                    temporaryList.add(returnListFuture.get(i));
                }
                if(i == returnListFuture.size() -1 ){
                    returnList.add(new CategoryViewModel.PersonWithEvents(temporaryList));
                }

            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("EventRepo", "return");
        if (returnList.size()!=0){      Log.d("EventRepo", returnList.get(0).getTitleA());}
        else {Log.d("EventRepo","return is empty");}

        return returnList;


    }

    public LiveData<List<EventJoinPerson>> getUpcomingEvents(){
        return eventDao.getUpcomingEvents();
    }
}
