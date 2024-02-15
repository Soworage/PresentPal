package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.PersonRepository;

public class PersonInsertViewModel extends AndroidViewModel {

    private final PersonRepository personRepository;
    public PersonInsertViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
    }

    public MutableLiveData<String> firstname = new MutableLiveData<>();
    public MutableLiveData<String> lastname = new MutableLiveData<>();
    public MutableLiveData<String> nickname = new MutableLiveData<>();

    public MutableLiveData<Long> personInsertOk = new MutableLiveData<>();

    //private MutableLiveData<>
    public void addPerson(){
        personInsertOk.setValue(personRepository.addPerson(firstname.getValue(), lastname.getValue(), nickname.getValue()));
    }

    private MutableLiveData<Boolean> finish = new MutableLiveData<>();
    public void goBack(){
        finish.setValue(true);
    }
    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }


}
