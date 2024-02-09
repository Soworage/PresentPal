package com.example.presentpal.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.presentpal.db.AppDatabase;
import com.example.presentpal.db.AppDatabaseClient;
import com.example.presentpal.db.dao.LogInDao;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class LogInRepository {
    private final LogInDao logInDao;


    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public LogInRepository(Application application) {
        AppDatabase database = AppDatabaseClient.getInstance(application).getAppDatabase();
        logInDao = database.logInDao();
    }



    public LiveData<Integer> isPasswordSetLiveData(){
        return logInDao.isPasswordSet();
    }

    public Integer checkPassword(String password) {
        final int[] value = {0};
        Log.d("TAG", "checkPassword: test start");

        Future<Integer> future = executor.submit(() -> {
            int v = logInDao.checkPasswordLiveData(password);
            Log.d("TAG", "checkPassword: " + v);
            return v;
        });

        try {
            value[0] = future.get(); // This will block until the result is available
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        Log.d("TAG", "checkPassword: test end");

        return value[0];
    }
}
