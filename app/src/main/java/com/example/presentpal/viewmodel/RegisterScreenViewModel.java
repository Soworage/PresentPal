package com.example.presentpal.viewmodel;

import android.app.Application;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Category;
import com.example.presentpal.model.PersonRepository;

import java.util.Objects;

/**
 * ViewModel für die Registrierungsbildschirm, verantwortlich für die Verwaltung der Benutzerregistrierung.
 */
public class RegisterScreenViewModel extends AndroidViewModel {

    public MutableLiveData<Boolean> addUserResult = new MutableLiveData<>();
    private final PersonRepository personRepository;

    public MutableLiveData<String> nicknameEditText = new MutableLiveData<>();
    public MutableLiveData<String> passwordEditText = new MutableLiveData<>();
    public MutableLiveData<String> passwordCheckEditText = new MutableLiveData<>();
    /**
     * Konstruktor, der das ViewModel mit der Anwendungskontext initialisiert und das PersonRepository instanziiert.
     *
     * @param application Die Anwendung, die das ViewModel besitzt.
     */
    public RegisterScreenViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);

    }

    /**
     * Fügt einen neuen Benutzer hinzu, wenn die Passwörter übereinstimmen.
     * Setzt das Ergebnis der Registrierung in {@link #addUserResult}.
     *
     * @param view Der View, von dem die Aktion ausgelöst wurde (nicht verwendet).
     */
    public void addUser(View view) {
        if (Objects.equals(passwordEditText.getValue(), passwordCheckEditText.getValue())) {

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
    /**
     * Fügt initiale Kategorien zur Datenbank hinzu.
     */
    public void addCategories(){
        personRepository.addCategory(new Category("#favorites"));
        personRepository.addCategory(new Category("#work"));
        personRepository.addCategory(new Category("#friends"));
        personRepository.addCategory(new Category("#family"));
    }
}