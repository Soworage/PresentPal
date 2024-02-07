package com.example.presentpal.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.model.EventModel;

public class EventInsertViewModel extends AndroidViewModel {

    private final EventModel eventModel;

    public EventInsertViewModel(@NonNull Application application) {
        super(application);
        eventModel = new EventModel(application);
    }

    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> date = new MutableLiveData<>();
    public MutableLiveData<Integer> personId = new MutableLiveData<>();


    //private MutableLiveData<>
    public void addEvent(View view) {
        eventModel.addEvent(title.getValue(), date.getValue(), personId.getValue());
    }

}
