package com.example.presentpal.viewmodel;

import android.app.Application;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.R;
import com.example.presentpal.model.PersonModel;

import java.util.Objects;

public class RegisterScreenViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> addUserResult = new MutableLiveData<>();
    private final PersonModel personModel;
    public MutableLiveData<String> nicknameEditText = new MutableLiveData<>();
    public MutableLiveData<String> passwordEditText = new MutableLiveData<>();
    public MutableLiveData<String> passwordCheckEditText = new MutableLiveData<>();


    public RegisterScreenViewModel(@NonNull Application application) {
        super(application);
        personModel = new PersonModel(application);

    }


    public void addUser(View view) {
    //    if (Objects.equals(passwordEditText.getValue(), passwordCheckEditText.getValue())) {
            personModel.addUser(nicknameEditText.getValue(), passwordEditText.getValue(),
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
