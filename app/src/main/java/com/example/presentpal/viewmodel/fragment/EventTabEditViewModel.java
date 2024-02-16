package com.example.presentpal.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Event;
import com.example.presentpal.model.EventRepository;

/**
 * ViewModel zur Verwaltung von Daten im Zusammenhang mit der Bearbeitung von Veranstaltungen.
 */
public class EventTabEditViewModel extends AndroidViewModel {

    private EventRepository eventRepository;

    // MutableLiveData zur Speicherung der bearbeiteten Veranstaltung
    public MutableLiveData<Event> event = new MutableLiveData<>();

    /**
     * Konstruktor für das ViewModel.
     * @param application Der Anwendungskontext.
     */
    public EventTabEditViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
    }

    /**
     * Methode zur Behandlung des Klicks auf die Schaltfläche "Speichern".
     * Sie aktualisiert die Veranstaltung mithilfe des Repositorys.
     */
    public void onClickSave(){
        eventRepository.updateEvent(event.getValue());
    }
}
