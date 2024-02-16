package com.example.presentpal.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.PersonRepository;

/**
 * ViewModel zur Verwaltung von Daten im Zusammenhang mit der Bearbeitung von Personen.
 */
public class PersonTabEditViewModel extends AndroidViewModel {

    private PersonRepository personRepository;

    // MutableLiveData zur Speicherung der bearbeiteten Person
    public MutableLiveData<Person> person = new MutableLiveData<>();

    /**
     * Konstruktor für das ViewModel.
     * @param application Der Anwendungskontext.
     */
    public PersonTabEditViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
    }

    /**
     * Methode zur Behandlung des Klicks auf die Schaltfläche "Speichern".
     * Sie aktualisiert die Person mithilfe des Repositorys.
     */
    public void onclickSave(){
        personRepository.updatePerson(getPerson().getValue());
    }

    /**
     * Methode zur Rückgabe der MutableLiveData-Instanz, die die bearbeitete Person enthält.
     * @return MutableLiveData-Instanz mit der bearbeiteten Person.
     */
    public MutableLiveData<Person> getPerson() {
        return person;
    }
}
