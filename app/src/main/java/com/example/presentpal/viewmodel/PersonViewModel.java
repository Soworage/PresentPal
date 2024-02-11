package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventPlus;
import com.example.presentpal.db.Person;
import com.example.presentpal.model.EventRepository;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    public MutableLiveData<Person> person = new MutableLiveData<>();

    public MutableLiveData<List<EventPlus>> getAllEventsByPerson() {
        return allEventsByPerson;
    }

    public MutableLiveData<List<EventPlus>> allEventsByPerson = new MutableLiveData<>();

    private EventRepository eventRepository;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        //getEventsByPerson();
    }


    public void getEventsByPerson(){
        Log.i("PersonViewModel", "getEventsByPerson()");
        if(person.getValue() != null) {
            Log.i("PersonViewModel", "getEventsByPerson() 2 "+ person.getValue().nickname);
            allEventsByPerson.setValue(eventRepository.getAllEventsByPerson(person.getValue().id));
        }
    }
}
