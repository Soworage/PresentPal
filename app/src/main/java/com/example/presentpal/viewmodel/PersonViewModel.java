package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;

public class PersonViewModel extends AndroidViewModel {

    public MutableLiveData<Person> person = new MutableLiveData<>();

    public PersonViewModel(@NonNull Application application) {
        super(application);


    }
}
