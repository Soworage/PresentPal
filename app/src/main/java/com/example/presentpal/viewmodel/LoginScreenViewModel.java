package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.databinding.InverseMethod;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.presentpal.db.User;
import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.model.LogInRepository;

import java.util.Objects;

public class LoginScreenViewModel extends AndroidViewModel {


    public MutableLiveData<String> passwordCheckerText = new MutableLiveData<>();

    public LiveData<User> User;
    public MutableLiveData<Integer> isPasswordCorrect = new MutableLiveData<>();
    private LogInRepository logInRepository;

    public LoginScreenViewModel(@NonNull Application application) {
        super(application);
        logInRepository = new LogInRepository(application);
        User = logInRepository.getUser();
    }



    public void setPasswordCheckerText(CharSequence s) {
        this.passwordCheckerText.setValue(s.toString());
    }

    public LiveData<Integer> getIsPasswordCorrect() {
        return isPasswordCorrect;
    }



    public void loginUser() {
        Log.d("Password", "Password1: " + passwordCheckerText.getValue());
        if (passwordCheckerText.getValue() != null) {
            Integer value = logInRepository.checkPassword(passwordCheckerText.getValue());
            isPasswordCorrect.setValue(value);
            Log.d("Password", "Password: " + value);
        } else {
            // Handle the case where passwordCheckerText is null
            Log.e("LoginUser", "passwordCheckerText is null");
        }
    }

}
