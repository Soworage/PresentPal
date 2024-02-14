package com.example.presentpal.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Event;
import com.example.presentpal.model.EventRepository;

public class EventTabEditViewModel extends AndroidViewModel {

    private EventRepository eventRepository;

    public MutableLiveData<Event> event = new MutableLiveData<>();

    public EventTabEditViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);

    }

    public void onClickSave(){
        eventRepository.updateEvent(event.getValue());
    }
}
