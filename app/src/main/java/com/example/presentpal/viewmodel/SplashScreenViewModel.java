package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.room.Database;

import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.model.LogInRepository;

public class SplashScreenViewModel extends AndroidViewModel {

    private LogInRepository logInRepository;

    public SplashScreenViewModel(@NonNull Application application) {
        super(application);
        logInRepository = new LogInRepository(application);

    }
    public LiveData<Integer> isPasswordSetLiveData() {
        return logInRepository.isPasswordSetLiveData();
    }



}