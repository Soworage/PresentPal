package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.LogIn;
import com.example.presentpal.model.LogInRepository;


public class LoginScreenViewModel extends AndroidViewModel {

    public final LiveData<LogIn> login;
    private final LogInRepository logInRepository;
    public MutableLiveData<String> passwordCheckerText = new MutableLiveData<>();
    public MutableLiveData<Boolean> isPasswordCorrect = new MutableLiveData<>();

    public LoginScreenViewModel(@NonNull Application application) {
        super(application);
        logInRepository = new LogInRepository(application);
        login = logInRepository.getLogin();
    }

    public MutableLiveData<Boolean> getIsPasswordCorrect() {
        return isPasswordCorrect;
    }

    public void loginUser() {
        if (passwordCheckerText.getValue().equals(login.getValue().password)) {
            isPasswordCorrect.setValue(true);
        } else {
            isPasswordCorrect.setValue(false);
        }
    }
}
