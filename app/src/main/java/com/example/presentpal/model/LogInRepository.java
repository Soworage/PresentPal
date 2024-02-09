package com.example.presentpal.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.LogIn;
import com.example.presentpal.db.dao.LogInDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogInRepository {
    private final LogInDao logInDao;
    private ExecutorService executorService;

    public LogInRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        executorService = Executors.newSingleThreadExecutor();
        logInDao = database.logInDao();
    }

    public LiveData<Integer> isPasswordSetLiveData() {
        LiveData<Integer> test = logInDao.isPasswordSet();
        Log.i("Password set Test LoginRepo", "Wert: " + test.getValue());
        return test;
    }

    public LiveData<LogIn> getLogin() {
        return logInDao.getLogIn();
    }
}
