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

public class EventInsertViewModel extends AndroidViewModel {

    private final EventRepository eventRepository;
    public LiveData<List<Person>> allPersons;

    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<Integer> position = new MutableLiveData<>();
    public MutableLiveData<Person> selectedPerson = new MutableLiveData<>();
    public MutableLiveData<Boolean> calendarOpen = new MutableLiveData<>();
    public MutableLiveData<Long> eventInsertOk = new MutableLiveData<>();



    public EventInsertViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        allPersons = eventRepository.getAllPersons();
        position.setValue(0);
        calendarOpen.setValue(false);
    }


    //private MutableLiveData<>
    public void addEvent() {
        if (selectedPerson.getValue() != null) {
            eventInsertOk.setValue(eventRepository.addEvent(title.getValue(), date.getValue(), selectedPerson.getValue().getId()));
        }
    }

    public LiveData<Person> getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(int personPosition) {
        if (allPersons.getValue() != null){
            selectedPerson.setValue(allPersons.getValue().get(personPosition));
        }
    }

    public MutableLiveData<Boolean> getCalendarOpen() {
        return calendarOpen;
    }

    public void setCalendarOpen() {
        if (calendarOpen.getValue() != null) {
            if (calendarOpen.getValue()) {
                calendarOpen.setValue(false);
            } else {
                calendarOpen.setValue(true);
            }
        }
    }

    public MutableLiveData<String> getDate() {
        return date;
    }

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
}
