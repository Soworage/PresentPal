package com.example.presentpal.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.model.PersonModel;

public class PersonInsertViewModel extends AndroidViewModel {

    private final PersonModel personModel;
    public PersonInsertViewModel(@NonNull Application application) {
        super(application);
        personModel = new PersonModel(application);
    }

    public MutableLiveData<String> firstname = new MutableLiveData<>();
    public MutableLiveData<String> lastname = new MutableLiveData<>();
    public MutableLiveData<String> nickname = new MutableLiveData<>();

    //private MutableLiveData<>
    public void addPerson(View view){
        personModel.addPerson(firstname.getValue(), lastname.getValue(), nickname.getValue());
    }
}
