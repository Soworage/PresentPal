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

    public MutableLiveData<Event> event = new MutableLiveData<>();

    public MutableLiveData<PresentIdea> presentIdea = new MutableLiveData<>();

    public PresentIdeaViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
    }

    public LiveData<PresentIdea> getPresentIdeaById(int presentIdeaId) {
        return presentIdeaRepository.getPresentIdeaById(presentIdeaId);
    }

    public void markAsPresent() {
        if (presentIdea.getValue().isPresent) {
            presentIdea.getValue().setPresent(false);
            presentIdea.getValue().setEventId(null);
            presentIdeaRepository.updateEvent(event.getValue());
            presentIdeaRepository.updatePresentIdea(presentIdea.getValue());
        } else {
            presentIdea.getValue().setPresent(true);
            presentIdea.getValue().setEventId(event.getValue().eid);
            presentIdeaRepository.updateEvent(event.getValue());
            presentIdeaRepository.updatePresentIdea(presentIdea.getValue());
        }
    }

    private MutableLiveData<Boolean> finish = new MutableLiveData<>();

    public void goBack() {
        finish.setValue(true);
    }

    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }


}
