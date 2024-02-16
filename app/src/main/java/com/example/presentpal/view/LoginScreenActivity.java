package com.example.presentpal.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityLoginScreenBinding;
import com.example.presentpal.viewmodel.LoginScreenViewModel;

/**
 * Die LoginScreenActivity ist für die Darstellung und Verwaltung des Login-Bildschirms zuständig.
 * Sie ermöglicht es dem Nutzer, sich mit einem Passwort zu authentifizieren, um Zugang zu den Hauptfunktionen der App zu erhalten.
 * Diese Klasse bindet das Layout mit den Daten über das ViewModel und reagiert auf Benutzerinteraktionen wie die Passworteingabe.
 */
public class LoginScreenActivity extends AppCompatActivity {
    private LoginScreenViewModel loginScreenViewModel;
    private ActivityLoginScreenBinding activityLoginScreenBinding;

    /**
     * Wird aufgerufen, wenn die Activity zum ersten Mal erstellt wird. Initialisiert das Binding und das ViewModel.
     * Setzt Beobachter auf die LiveData-Objekte im ViewModel, um auf Änderungen zu reagieren und entsprechende Aktionen auszuführen,
     * wie das Starten einer neuen Activity oder das Anzeigen von Benachrichtigungen.
     *
     * @param savedInstanceState Wenn die Activity neu initialisiert wird, nachdem sie vom System beendet wurde,
     *                           enthält dies die Daten, die zuletzt an onSaveInstanceState übergeben wurden.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        loginScreenViewModel = new ViewModelProvider(this).get(LoginScreenViewModel.class);
        activityLoginScreenBinding.setLifecycleOwner(this);
        activityLoginScreenBinding.setLoginScreenViewModel(loginScreenViewModel);

        loginScreenViewModel.getIsPasswordCorrect().observe(this, isPasswordCorrect -> {

            if (isPasswordCorrect != null && isPasswordCorrect > 0) {

                Intent loginIntent = new Intent(this, MainActivity.class);
                startActivity(loginIntent);
                finish();
            } else {
                CharSequence text = "Password wrong";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(LoginScreenActivity.this, text, duration);
                toast.show();
            }
        });

        loginScreenViewModel.openIdeaInsert.observe(this, open -> {
            Intent intent = new Intent(this, PresentIdeaInsertActivity.class);
            startActivity(intent);
        });


        loginScreenViewModel.openInfo.observe(this, openInfo -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("App-Info")
                    .setMessage(loginScreenViewModel.getInfoText())
                    .setPositiveButton("OK", (dialog, which) -> {
                    })
                    .show();
        });


    }
}
