package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Category;
import com.example.presentpal.db.Person;
import com.example.presentpal.model.PersonRepository;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private PersonRepository personRepository;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        selectCategory();
    }

    public MutableLiveData<Category> category = new MutableLiveData<>();
    public MutableLiveData<List<Person>> allPersonsByCategory = new MutableLiveData<>();

    public void selectCategory(){
        if(category.getValue() != null){
            allPersonsByCategory.setValue(personRepository.getAllPersonsByCategory(category.getValue()).getValue());
        }
        else {
            allPersonsByCategory.setValue(personRepository.getAllPersons().getValue());
        }
    }

}
