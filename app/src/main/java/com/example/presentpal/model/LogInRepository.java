package com.example.presentpal.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.LogIn;
import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.viewmodel.SplashScreenViewModel;

public class LogInRepository {
    private final LogInDao logInDao;
    public LogInRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        logInDao = database.logInDao();
    }



    public LiveData<Integer> isPasswordSetLiveData(){
        return logInDao.isPasswordSet();
    }

}
