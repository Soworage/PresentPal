package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.model.PersonRepository;

import java.util.Objects;

public class RegisterScreenViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> addUserResult = new MutableLiveData<>();
    private final PersonRepository personRepository;
    public MutableLiveData<String> nicknameEditText = new MutableLiveData<>();
    public MutableLiveData<String> passwordEditText = new MutableLiveData<>();
    public MutableLiveData<String> passwordCheckEditText = new MutableLiveData<>();


    public RegisterScreenViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);

    }


    public void addUser(View view) {
        if (Objects.equals(passwordEditText.getValue(), passwordCheckEditText.getValue())) {
            Log.d("RegisterScreenVM", "Nickname: " + nicknameEditText.getValue() + ", Passwort: " + passwordEditText.getValue());
            personRepository.addUser(nicknameEditText.getValue(), passwordEditText.getValue(),
                    success -> {
                        // Das läuft im Hintergrundthread um sicherzustellen das änderungen am livedata im hauptthread durchgeführt werden
                        if (success) {
                            addUserResult.postValue(true);
                        } else {
                            addUserResult.postValue(false);
                        }
                    });


        }
    }
}