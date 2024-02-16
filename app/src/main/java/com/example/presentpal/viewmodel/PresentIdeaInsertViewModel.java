package com.example.presentpal.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.PresentIdeaRepository;

import java.util.List;

/**
 * ViewModel für die Hinzufügung einer neuen Geschenkidee. Es stellt LiveData und Funktionen zur Verfügung,
 * um die Interaktion zwischen der Benutzeroberfläche und den Datenquellen zu verwalten.
 */
public class PresentIdeaInsertViewModel extends AndroidViewModel {

    private final PresentIdeaRepository presentIdeaRepository;


    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> shortDescription = new MutableLiveData<>();


    public MutableLiveData<Integer> position = new MutableLiveData<>();

    public MutableLiveData<Person> selectedPerson = new MutableLiveData<>();

    public MutableLiveData<Long> presentIdeaInsertOk = new MutableLiveData<>();


    public LiveData<List<Person>> allPersons;

    /**
     * Konstruktor, initialisiert das Repository und lädt alle Personen.
     *
     * @param application Die Anwendung, die das ViewModel besitzt.
     */

    public PresentIdeaInsertViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
        allPersons = presentIdeaRepository.getAllPersons();
        position.setValue(0);
    }


    /**
     * Fügt eine neue Geschenkidee hinzu, basierend auf den eingegebenen Daten.
     */
    public void addPresentIdea() {
        if (selectedPerson.getValue() != null) {
            presentIdeaInsertOk.setValue(presentIdeaRepository.addPresentIdea(selectedPerson.getValue().getId(), title.getValue(), shortDescription.getValue()));
        }
    }
    /**
     * Gibt die aktuell ausgewählte Person zurück.
     * @return LiveData für die ausgewählte Person.
     */
    public LiveData<Person> getSelectedPerson() {
        return selectedPerson;
    }
    /**
     * Setzt die ausgewählte Person basierend auf der Position in der Liste.
     * @param personPosition Die Position der ausgewählten Person in der Liste.
     */
    public void setSelectedPerson(int personPosition) {
        if (allPersons.getValue() != null) {
            selectedPerson.setValue(allPersons.getValue().get(personPosition));
        }
    }

    public MutableLiveData<Integer> getPosition() {
        return position;
    }

    public void setPosition(MutableLiveData<Integer> position) {
        this.position = position;
    }

    private MutableLiveData<Boolean> finish = new MutableLiveData<>();
    /**
     * Signalisiert der UI, dass der Nutzer zurück navigieren möchte.
     */
    public void goBack() {
        finish.setValue(true);
    }
    /**
     * Gibt LiveData zurück, das beobachtet werden kann, um auf die Aufforderung zum Zurückgehen zu reagieren.
     * @return MutableLiveData, das den Zustand des Zurückgehens repräsentiert.
     */
    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }

}
