package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class MainActivityViewModel extends AndroidViewModel {
//    public static class NavigationEvent {
//        private String data; // Die Daten, die übermittelt werden sollen
//
//        public NavigationEvent(String data) {
//            this.data = data;
//        }
//
//        public String getData() {
//            return data;
//        }
//    }

    private MutableLiveData<String> _selectedCategory = new MutableLiveData<>();
    public MutableLiveData<String> selectedCategory = _selectedCategory;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

//    public LiveData<NavigationEvent> getNavigateToCategoryScreen() {
//        return navigateToCategoryScreen;
//    }

    public void onCategorySelected(String category) {
        _selectedCategory.setValue(category);
        Log.d("category", category);
    }

    public void selectFriendsCategory() {
        onCategorySelected("friends");

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

}
