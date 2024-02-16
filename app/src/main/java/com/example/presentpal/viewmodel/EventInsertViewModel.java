package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.EventRepository;

import java.util.List;

/**
 * ViewModel für das Einfügen von Ereignissen in der Anwendung.
 * Verwaltet die UI-bezogenen Daten im Lebenszyklus bewusst und ermöglicht die Kommunikation zwischen der Repository-Schicht und der UI.
 */
public class EventInsertViewModel extends AndroidViewModel {

    private final EventRepository eventRepository;
    public LiveData<List<Person>> allPersons;

    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<Integer> position = new MutableLiveData<>();
    public MutableLiveData<Person> selectedPerson = new MutableLiveData<>();
    public MutableLiveData<Boolean> calendarOpen = new MutableLiveData<>();
    public MutableLiveData<Long> eventInsertOk = new MutableLiveData<>();


    /**
     * Konstruktor für EventInsertViewModel.
     * Initialisiert das Repository und lädt alle Personen.
     *
     * @param application Die Anwendung, die das ViewModel besitzt. Wird verwendet, um den Anwendungskontext zu erhalten.
     */
    public EventInsertViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        allPersons = eventRepository.getAllPersons();
        position.setValue(0);
        calendarOpen.setValue(false);
    }


    /**
     * Fügt ein neues Ereignis hinzu, basierend auf den vom Benutzer eingegebenen Daten.
     */
    public void addEvent() {
        if (selectedPerson.getValue() != null) {
            eventInsertOk.setValue(eventRepository.addEvent(title.getValue(), date.getValue(), selectedPerson.getValue().getId()));
        }
    }

    /**
     * Gibt die ausgewählte Person zurück.
     *
     * @return LiveData, die die ausgewählte Person enthält.
     */
    public LiveData<Person> getSelectedPerson() {
        return selectedPerson;
    }

    /**
     * Setzt die ausgewählte Person basierend auf der Position in der Liste.
     *
     * @param personPosition Die Position der ausgewählten Person in der Liste.
     */
    public void setSelectedPerson(int personPosition) {
        if (allPersons.getValue() != null) {
            selectedPerson.setValue(allPersons.getValue().get(personPosition));
        }
    }

    /**
     * Gibt zurück, ob der Kalender geöffnet ist oder nicht.
     *
     * @return MutableLiveData, die den Zustand des Kalenders enthält.
     */
    public MutableLiveData<Boolean> getCalendarOpen() {
        return calendarOpen;
    }

    /**
     * Wechselt den Zustand des Kalenders zwischen geöffnet und geschlossen.
     */
    public void setCalendarOpen() {
        if (calendarOpen.getValue() != null) {
            if (calendarOpen.getValue()) {
                calendarOpen.setValue(false);
            } else {
                calendarOpen.setValue(true);
            }
        }
    }

    /**
     * Gibt das ausgewählte Datum zurück.
     *
     * @return MutableLiveData, die das ausgewählte Datum enthält.
     */
    public MutableLiveData<String> getDate() {
        return date;
    }

    /**
     * Setzt das ausgewählte Datum und schließt den Kalender.
     *
     * @param newDate Das neu ausgewählte Datum.
     */
    public void setDate(String newDate) {
        date.setValue(newDate);
        calendarOpen.setValue(false);
    }

    public MutableLiveData<Integer> getPosition() {
        return position;
    }

    public void setPosition(MutableLiveData<Integer> position) {
        this.position = position;
    }

    private MutableLiveData<Boolean> finish = new MutableLiveData<>();

    public void goBack() {
        finish.setValue(true);
    }

    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }
}
