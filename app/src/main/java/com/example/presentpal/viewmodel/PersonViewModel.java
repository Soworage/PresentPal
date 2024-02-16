package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Category;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventPlus;
import com.example.presentpal.db.Person;
import com.example.presentpal.model.EventRepository;
import com.example.presentpal.model.PersonRepository;

import java.util.List;
/**
 * ViewModel für die Darstellung und Verwaltung von Daten einer spezifischen Person und deren Ereignissen in der Anwendung.
 */
public class PersonViewModel extends AndroidViewModel {

    private EventRepository eventRepository;

    public MutableLiveData<String> categories =new MutableLiveData<>();

    /**
     * Konstruktor, der eine neue Instanz von PersonViewModel erstellt.
     * Initialisiert das EventRepository für den Zugriff auf Ereignisdaten.
     *
     * @param application Die Anwendung, die das ViewModel besitzt.
     */
    public PersonViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
    }


    /**
     * Holt eine Liste von Kategorien, die mit einer bestimmten Person verknüpft sind.
     *
     * @param id Die ID der Person, für die die Kategorien abgerufen werden sollen.
     * @return LiveData, das eine Liste von Category-Objekten enthält.
     */
    public LiveData<List<EventPlus>> getEventsByPerson(int id){
           return eventRepository.getAllEventsByPerson(id);

    }

    public LiveData<Person> getPersonById(int id){
        return eventRepository.getPersonById(id);
    }

    public LiveData<List<Category>> getCategoryByPerson(int id){
      return  eventRepository.getCategoryByPerson(id);
    }

    private MutableLiveData<Boolean> finish = new MutableLiveData<>();
    public void goBack(){
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
