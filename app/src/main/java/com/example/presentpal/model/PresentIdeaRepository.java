package com.example.presentpal.model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.db.dao.PresentIdeaDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class PresentIdeaRepository {

    private final PresentIdeaDao presentIdeaDao;
    private LiveData<List<PresentIdea>> allPresentIdeas;

    private ExecutorService executor = Executors.newSingleThreadExecutor();


    public PresentIdeaRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        presentIdeaDao = database.presentIdeaDao();
    }

    public LiveData<List<PresentIdea>> getAllPresentIdeasByEvent(Event event, Person person){
        return presentIdeaDao.getPresentIdeasByEvent(event.getId(), person.getId());
    }


    public void addPresentIdea(int personId, String title, String description){
        PresentIdea presentIdea = new PresentIdea(personId, null, title, description, 0f, null,false);
        insertPresentIdea(presentIdea);
    }

    private void insertPresentIdea(PresentIdea presentIdea) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                presentIdeaDao.insert(presentIdea);
            }
        });
    }
}
