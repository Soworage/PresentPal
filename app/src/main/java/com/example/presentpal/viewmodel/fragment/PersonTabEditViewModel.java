package com.example.presentpal.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;

import com.example.presentpal.model.PersonRepository;

public class PersonTabEditViewModel extends AndroidViewModel {

    private PersonRepository personRepository;

    public MutableLiveData<Person> person = new MutableLiveData<>();
    public PersonTabEditViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
    }

    public void onclickSave(){
        personRepository.updatePerson(getPerson().getValue());
    }
    public MutableLiveData<Person> getPerson() {
        return person;
    }
}
