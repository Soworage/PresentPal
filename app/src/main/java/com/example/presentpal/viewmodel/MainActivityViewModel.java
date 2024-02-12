package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.User;
import com.example.presentpal.model.EventRepository;
import com.example.presentpal.model.LogInRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {


    private MutableLiveData<String> _selectedCategory = new MutableLiveData<>();
    public MutableLiveData<String> selectedCategory = _selectedCategory;
    public LiveData<User> User;
    private EventRepository eventRepository;
    private LogInRepository logInRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        logInRepository = new LogInRepository(application);
        eventRepository = new EventRepository(application);
        User = logInRepository.getUser();

    }


    public void onCategorySelected(String category) {
        _selectedCategory.setValue(category);
    }

    public void selectAllCategory() {
        onCategorySelected(null);
    }

    public void selectFamilyCategory() {
        onCategorySelected("family");
    }

    public void selectColleagueCategory() {
        onCategorySelected("colleague");
    }

    public void selectFavoriteCategory() {
        onCategorySelected("favorites");
    }

    public void selectFriendsCategory() {
        onCategorySelected("friends");
    }

    public LiveData<List<EventJoinPerson>> getUpcomingEvents(){
        return eventRepository.getUpcomingEvents();
    }
}
