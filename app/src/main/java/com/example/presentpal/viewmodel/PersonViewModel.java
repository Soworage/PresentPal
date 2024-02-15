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

public class PersonViewModel extends AndroidViewModel {

    private EventRepository eventRepository;

    public MutableLiveData<String> categories =new MutableLiveData<>();

    public PersonViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
    }


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
    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }
}
