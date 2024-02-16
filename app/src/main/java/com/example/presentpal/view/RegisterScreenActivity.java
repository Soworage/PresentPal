package com.example.presentpal.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.viewmodel.RegisterScreenViewModel;
import com.example.presentpal.databinding.ActivityRegisterScreenBinding;

/**
 * Die Aktivität für den Registrierungsbildschirm, die es Benutzern ermöglicht, ein neues Konto anzulegen.
 * Diese Klasse bindet das Benutzerinterface an das RegisterScreenViewModel und reagiert auf die Benutzereingaben
 * für den Registrierungsprozess.
 */
public class RegisterScreenActivity extends AppCompatActivity {
    private RegisterScreenViewModel registerScreenViewModel;
    private ActivityRegisterScreenBinding activityRegisterScreenBinding;

    /**
     * Wird aufgerufen, wenn die Aktivität erstellt wird. Initialisiert das Binding und das ViewModel und
     * stellt die Verbindung zwischen der Benutzeroberfläche und der Geschäftslogik her.
     *
     * @param savedInstanceState Zustand der Aktivität, wenn sie neu erstellt wird, nachdem sie zuvor zerstört wurde.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        activityRegisterScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_register_screen);
        registerScreenViewModel = new ViewModelProvider(this).get(RegisterScreenViewModel.class);
        activityRegisterScreenBinding.setLifecycleOwner(this);
        activityRegisterScreenBinding.setRegisterScreenViewModel(registerScreenViewModel);


        // Beobachten des addUserResult LiveData-Objekts
        registerScreenViewModel.addUserResult.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean success) {
                if (success) {
                    registerScreenViewModel.addCategories();
                    // Navigate to the login screend
                    Intent intent = new Intent(RegisterScreenActivity.this, LoginScreenActivity.class);
                    startActivity(intent);
                    finish();
                } else {

                }
            }
        });
    }
}


