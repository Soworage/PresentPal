package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.PersonRepository;

/**
 * ViewModel zur Verwaltung der Daten und Logik beim Einfügen einer neuen Person in die Datenbank.
 */
public class PersonInsertViewModel extends AndroidViewModel {

    private final PersonRepository personRepository;

    /**
     * Konstruktor, der eine neue Instanz von PersonInsertViewModel initialisiert.
     * Initialisiert das PersonRepository für Datenbankoperationen.
     *
     * @param application Die Anwendung, die das ViewModel besitzt.
     */
    public PersonInsertViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
    }

    public MutableLiveData<String> firstname = new MutableLiveData<>();
    public MutableLiveData<String> lastname = new MutableLiveData<>();
    public MutableLiveData<String> nickname = new MutableLiveData<>();

    public MutableLiveData<Long> personInsertOk = new MutableLiveData<>();

    /**
     * Fügt eine neue Person mit den aktuellen Werten für Vorname, Nachname und Spitzname zur Datenbank hinzu.
     * Das Ergebnis der Operation wird in personInsertOk gespeichert.
     */
    public void addPerson() {
        personInsertOk.setValue(personRepository.addPerson(firstname.getValue(), lastname.getValue(), nickname.getValue()));
    }

    private MutableLiveData<Boolean> finish = new MutableLiveData<>();

    /**
     * Signalisiert der Benutzeroberfläche, dass der Nutzer den aktuellen Vorgang beenden und zur vorherigen Ansicht zurückkehren möchte.
     */
    public void goBack() {
        finish.setValue(true);
    }

    /**
     * Liefert LiveData, das beobachtet werden kann, um auf Benutzeraktionen zum Beenden des aktuellen Vorgangs zu reagieren.
     *
     * @return MutableLiveData, das den Beendigungszustand repräsentiert.
     */
    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }


}
