package com.example.presentpal.viewmodel;

import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.InverseMethod;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.presentpal.db.User;
import com.example.presentpal.db.dao.LogInDao;
import com.example.presentpal.model.LogInRepository;
import com.example.presentpal.view.MainActivity;

import java.util.Objects;

/**
 * ViewModel für den Login-Bildschirm.
 * Verantwortlich für die Authentifizierung des Benutzers und die Bereitstellung der Navigationsoptionen.
 */
public class LoginScreenViewModel extends AndroidViewModel {


    public MutableLiveData<String> passwordCheckerText = new MutableLiveData<>();

    public LiveData<User> User;
    public MutableLiveData<Integer> isPasswordCorrect = new MutableLiveData<>();
    private LogInRepository logInRepository;

    public MutableLiveData<Boolean> openIdeaInsert = new MutableLiveData<>();
    public MutableLiveData<Boolean> openInfo = new MutableLiveData<>();

    /**
     * Konstruktor, der eine neue Instanz von LoginScreenViewModel initialisiert.
     *
     * @param application Die Anwendung, die das ViewModel besitzt.
     */
    public LoginScreenViewModel(@NonNull Application application) {
        super(application);
        logInRepository = new LogInRepository(application);
        User = logInRepository.getUser();
    }

    /**
     * Setzt den Text des Passwortprüfers.
     *
     * @param s Der eingegebene Text.
     */
    public void setPasswordCheckerText(CharSequence s) {
        this.passwordCheckerText.setValue(s.toString());
    }

    /**
     * Liefert LiveData, die angibt, ob das Passwort korrekt ist.
     *
     * @return LiveData, die den Zustand der Passwortüberprüfung enthält.
     */
    public LiveData<Integer> getIsPasswordCorrect() {
        return isPasswordCorrect;
    }


    /**
     * Überprüft das eingegebene Passwort und setzt das Ergebnis der Überprüfung.
     */
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
    /**
     * Signalisiert die Anforderung, den Informationsdialog anzuzeigen.
     */
    //https://stackoverflow.com/questions/10903754/input-text-dialog-android
    public void showInfo() {
        openInfo.setValue(true);
    }
    /**
     * Signalisiert die Anforderung, den Dialog für die schnelle Ideeneingabe zu öffnen.
     */
    public void quickIdea() {
        openIdeaInsert.setValue(true);
    }

    /**
     * Liefert den Text für den Informationsdialog.
     *
     * @return Der Text, der im Informationsdialog angezeigt werden soll.
     */
    public String getInfoText() {
        return infoText;
    }

    private String infoText = "\n" +
            "Autoren:" +
            "\n" +
            "Alex Mihel, Kay Schindler  \n" +
            "\n" +
            "Matrikelnummer: " +
            "\n" +
            "1016076, 588005 \n" +
            "\n" +
            "E-Mail: " +
            "\n" +
            "alex.mihel@hs-osnabrueck.de, kay.schindler@hs-osnabrueck.de\n" +
            "\n" +
            "Dozent: " +
            "\n" +
            "Prof. Dr. Rainer Roosmann \n" +
            "\n";


}
