package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Event;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.model.PresentIdeaRepository;

import java.util.List;

public class PresentIdeaViewModel extends AndroidViewModel {

    private final PresentIdeaRepository presentIdeaRepository;

    public MutableLiveData<List<Event>> allEvents = new MutableLiveData<>();

    public MutableLiveData<Integer> position = new MutableLiveData<>();

    public PresentIdeaViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
        position.setValue(0);
    }

    public LiveData<PresentIdea> getPresentIdeaById(int presentIdeaId) {
        return presentIdeaRepository.getPresentIdeaById(presentIdeaId);
    }

    public LiveData<List<Event>> getAllEventsByPerson(int personId){
        return presentIdeaRepository.getAllEventsByPerson(personId);
    }

    public MutableLiveData<List<Event>> getAllEvents() {
        return allEvents;
    }

    public void setAllEvents(List<Event> allEventsNew) {
        allEvents.setValue(allEventsNew);
    }
}
