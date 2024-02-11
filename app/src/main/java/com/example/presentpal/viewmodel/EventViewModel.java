package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Event;
import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.model.PresentIdeaRepository;

import java.util.List;

public class EventViewModel extends AndroidViewModel {

    public MutableLiveData<Event> event = new MutableLiveData<>();

    public MutableLiveData<List<PresentIdeaJoinPerson>> allPresentIdeas = new MutableLiveData<>();
    public MutableLiveData<List<PresentIdeaJoinPerson>> allPresents = new MutableLiveData<>();

    public MutableLiveData<Integer> readyState = new MutableLiveData<>();

    public MutableLiveData<String> daysLeft = new MutableLiveData<>();

    public MutableLiveData<String> price = new MutableLiveData<>();

    private PresentIdeaRepository presentIdeaRepository;
    public EventViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
        readyState.setValue(0);
    }

    public MutableLiveData<List<PresentIdeaJoinPerson>> getAllPresentIdeas() {
        return allPresentIdeas;
    }

    public MutableLiveData<List<PresentIdeaJoinPerson>> getAllPresents() {
        return allPresents;
    }

    public MutableLiveData<Integer> getReadyState() {
        return readyState;
    }
    public void getAllPresentIdeasAndPresents() {

        if(event.getValue() != null) {
            allPresentIdeas.setValue(presentIdeaRepository.getAllPresentIdeasWithPersonByPersonByEvent(event.getValue().personId, event.getValue().eid));
            allPresents.setValue(presentIdeaRepository.getAllPresentWithPersonByPersonByEvent(event.getValue().personId, event.getValue().eid));
        }
        else{  Log.d("EventViewMode", "event is null!");}
    }
}
