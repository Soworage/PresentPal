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
/**
 * ViewModel für den SplashScreen, das für die Initialisierung und Überprüfung des Anmeldestatus verantwortlich ist.
 * Diese Klasse überprüft, ob ein Passwort für die Anwendung gesetzt ist, was für die Entscheidung notwendig ist,
 * ob der Benutzer direkt zum Hauptbildschirm weitergeleitet wird oder zunächst die Passworteingabe erforderlich ist.
 */
public class SplashScreenViewModel extends AndroidViewModel {

    private LogInRepository logInRepository;
    /**
     * Konstruktor, der das ViewModel mit der Anwendungskontext initialisiert und das LogInRepository instanziiert.
     *
     * @param application Die Anwendung, die das ViewModel besitzt. Wird benötigt, um auf die Datenbank und andere Anwendungsressourcen zuzugreifen.
     */
    public SplashScreenViewModel(@NonNull Application application) {
        super(application);
        logInRepository = new LogInRepository(application);

    }
    /**
     * Gibt ein LiveData-Objekt zurück, das angibt, ob ein Passwort für die Anwendung gesetzt ist.
     * Dies wird verwendet, um zu entscheiden, ob der Benutzer zur Passworteingabe aufgefordert werden muss.
     *
     * @return Ein LiveData-Objekt, das die Anzahl der gesetzten Passwörter repräsentiert. Ein Wert von 0 bedeutet, dass kein Passwort gesetzt ist.
     */
    public LiveData<Integer> isPasswordSetLiveData() {
        return logInRepository.isPasswordSetLiveData();
    }



}