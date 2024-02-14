package com.example.presentpal.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.PresentIdeaRepository;

import java.util.List;

public class PresentIdeaInsertViewModel extends AndroidViewModel {

    private final PresentIdeaRepository presentIdeaRepository;


    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> shortDescription = new MutableLiveData<>();


    public MutableLiveData<Integer> position = new MutableLiveData<>();

    public MutableLiveData<Person> selectedPerson = new MutableLiveData<>();

    public MutableLiveData<Long> presentIdeaInsertOk = new MutableLiveData<>();


    public LiveData<List<Person>> allPersons;

    public PresentIdeaInsertViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
        allPersons = presentIdeaRepository.getAllPersons();
        position.setValue(0);
    }


    //private MutableLiveData<>
    public void addPresentIdea(){
        if (selectedPerson.getValue() != null) {
            presentIdeaInsertOk.setValue(presentIdeaRepository.addPresentIdea(selectedPerson.getValue().getId(), title.getValue(), shortDescription.getValue()));
        }
    }

    public LiveData<Person> getSelectedPerson() {
        return selectedPerson;
    }

    public void setSelectedPerson(int personPosition) {
        if (allPersons.getValue() != null){
            selectedPerson.setValue(allPersons.getValue().get(personPosition));
        }
    }

    public MutableLiveData<Integer> getPosition() {
        return position;
    }

    public void setPosition(MutableLiveData<Integer> position) {
        this.position = position;
    }

}
