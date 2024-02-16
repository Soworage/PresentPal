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

/**
 * ViewModel für die Verwaltung von Präsentationsideen in der Anwendung.
 * Ermöglicht die Interaktion mit dem {@link PresentIdeaRepository} für CRUD-Operationen bezüglich Präsentationsideen.
 */
public class PresentIdeaViewModel extends AndroidViewModel {

    private final PresentIdeaRepository presentIdeaRepository;

    public MutableLiveData<Event> event = new MutableLiveData<>();

    public MutableLiveData<PresentIdea> presentIdea = new MutableLiveData<>();

    /**
     * Konstruktor, der eine neue Instanz von {@link PresentIdeaViewModel} initialisiert.
     * Initialisiert das {@link PresentIdeaRepository} für den Zugriff auf die Datenbank.
     *
     * @param application Die Anwendung, die das ViewModel besitzt.
     */
    public PresentIdeaViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
    }

    /**
     * Holt eine Präsentationsidee anhand ihrer ID.
     *
     * @param presentIdeaId Die ID der Präsentationsidee, die abgerufen werden soll.
     * @return LiveData, das die angeforderte Präsentationsidee enthält.
     */
    public LiveData<PresentIdea> getPresentIdeaById(int presentIdeaId) {
        return presentIdeaRepository.getPresentIdeaById(presentIdeaId);
    }

    /**
     * Markiert eine Präsentationsidee als ein tatsächliches Geschenk oder entfernt diese Markierung,
     * abhängig vom aktuellen Zustand der Präsentationsidee.
     */
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

    /**
     * Signalisiert der Benutzeroberfläche, dass der Nutzer den aktuellen Vorgang beenden und zurückkehren möchte.
     */
    public void goBack() {
        finish.setValue(true);
    }

    /**
     * Liefert LiveData, das beobachtet werden kann, um auf die Aufforderung zum Zurückkehren zu reagieren.
     *
     * @return MutableLiveData, das den Zustand des Zurückkehrens repräsentiert.
     */
    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }


}
