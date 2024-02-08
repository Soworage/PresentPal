package com.example.presentpal.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.EventRepository;

import java.util.List;

public class EventInsertViewModel extends AndroidViewModel {

    private final EventRepository eventModel;
    public LiveData<List<Person>> allPersons;

    public EventInsertViewModel(@NonNull Application application) {
        super(application);
        eventModel = new EventRepository(application);
        allPersons = eventModel.getAllPersons();
        calendarOpen.setValue(false);
    }

    public MutableLiveData<String> title = new MutableLiveData<>();

    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<Integer> position = new MutableLiveData<>();

    public MutableLiveData<Person> selectedPerson = new MutableLiveData<>();

    public MutableLiveData<Boolean> calendarOpen = new MutableLiveData<>();


    //private MutableLiveData<>
    public void addEvent(View view) {
        if(selectedPerson.getValue() != null) {
            eventModel.addEvent(title.getValue(), date.getValue(), selectedPerson.getValue().getId());
        }
    }

    public LiveData<Person> getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(Person person) {
        selectedPerson.setValue(person);
    }

    public void onPersonSelected() {
        if (position.getValue() != null && allPersons.getValue() != null) {
            setSelectedPerson(allPersons.getValue().get(position.getValue()));
        }
    }

    public MutableLiveData<Boolean> getCalendarOpen() {
        return calendarOpen;
    }

    public void setCalendarOpen() {
        if(calendarOpen.getValue()!= null){
            if(calendarOpen.getValue()){
                calendarOpen.setValue(false);
            }
            else {
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

}
