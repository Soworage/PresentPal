package com.example.presentpal.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
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

    public MutableLiveData<Person> person = new MutableLiveData<>();



    public MutableLiveData<Boolean> personInserted = new MutableLiveData<>();

    //private MutableLiveData<>
    public void addPerson(){
        person.setValue(personModel.addPerson(firstname.getValue(), lastname.getValue(), nickname.getValue()));
      // ÜBERARBETEIN!!
        personInserted.setValue(true);
    }

    public LiveData<Person> getPerson(){
        if(person.getValue() != null){
            return person;
        }
        return null;
    }
}
