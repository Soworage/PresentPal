package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.Database;

import com.example.presentpal.db.dao.LogInDao;

public class SplashScreenViewModel extends AndroidViewModel {

    private final LogInDao loginDao;
    private final MutableLiveData<NavigateTo> navigateTo = new MutableLiveData<>();

    public SplashScreenViewModel(@NonNull Application application, LogInDao loginDao) {
        super(application);
        this.loginDao = loginDao;
    }

    public LiveData<NavigateTo> getNavigateTo() {
        return navigateTo;
    }



    public void checkUserRegistration() {
        // Observe the isPasswordSet LiveData
        LiveData<Boolean> ispasswordSet = loginDao.isPasswordSet();
        if (ispasswordSet.getValue() != null) {
            navigateTo.setValue(NavigateTo.LOGIN);
        } else {
            navigateTo.setValue(NavigateTo.REGISTER);
        }

    }


    public enum NavigateTo {
        LOGIN,
        REGISTER
    }
}