package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Event;

public class EventViewModel extends AndroidViewModel {

    public MutableLiveData<Event> event = new MutableLiveData<>();
    public EventViewModel(@NonNull Application application) {
        super(application);



    }
}
