package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.PersonRepository;

import java.util.List;

public class CategoryInsertViewModel extends AndroidViewModel {

    private final PersonRepository personRepository;
    public LiveData<List<Person>> allPersons;


    private MutableLiveData<Boolean> finish = new MutableLiveData<>();

    public MutableLiveData<Integer> position = new MutableLiveData<>();

    public MutableLiveData<Person> selectedPerson = new MutableLiveData<>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String category;

    public CategoryInsertViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        allPersons = personRepository.getAllPersons();
        position.setValue(0);
    }

    public void setSelectedPerson(int personPosition) {
        if (allPersons.getValue() != null){
            selectedPerson.setValue(allPersons.getValue().get(personPosition));
        }
    }

    public void addCategory(){
        if (selectedPerson.getValue() != null) {
           long returnValue = personRepository.insertPersonCatergory(selectedPerson.getValue().id, category);
           if(returnValue >= 0){
               goBack();
           }
        }
    }

    public MutableLiveData<Integer> getPosition() {
        return position;
    }

    public void goBack(){
        finish.setValue(true);
    }

    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }
}
