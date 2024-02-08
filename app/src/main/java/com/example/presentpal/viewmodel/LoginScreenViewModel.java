package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.model.LogInRepository;

import java.util.Objects;

public class LoginScreenViewModel extends AndroidViewModel {


    public MutableLiveData<String> passwordCheckerText = new MutableLiveData<>();
    public MutableLiveData<Integer> isPasswordCorrect = new MutableLiveData<>();
    private LogInRepository logInRepository;

    public LoginScreenViewModel(@NonNull Application application) {
        super(application);
        logInRepository = new LogInRepository(application);
    }

    public LiveData<String> getPasswordCheckerText() {
        return passwordCheckerText;
    }


    public LiveData<Integer> getIsPasswordCorrect() {
        return isPasswordCorrect;
    }

    public void loginUser() {
        Log.d("Passwort", "Password " );
        isPasswordCorrect.setValue(logInRepository.checkPassword(passwordCheckerText.getValue()).getValue());

    }

}
