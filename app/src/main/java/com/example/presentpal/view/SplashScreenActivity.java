package com.example.presentpal.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.model.LogInRepository;
import com.example.presentpal.viewmodel.SplashScreenViewModel;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenViewModel splashScreenViewModel;
    private final long SPLASH_SCREEN_DELAY = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashScreenViewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);

        // Nach der SPLASH_SCREEN_DELAY, überprüfen Sie den Passwortstatus
        new Handler().postDelayed(() -> {
            // Da wir nicht direkt im ViewModel beobachten, fügen wir den Observer hier hinzu

            splashScreenViewModel.isPasswordSetLiveData().observe(this, isPasswordSet -> {
                if (isPasswordSet != null && isPasswordSet > 0) {
                    Log.i("To Login Screen", "Wert: "+ isPasswordSet);
                    navigateToLoginScreen();
                } else {
                    navigateToRegisterScreen();
                }
            });
        }, SPLASH_SCREEN_DELAY);
    }
    private void navigateToLoginScreen() {
        Intent loginIntent = new Intent(this, LoginScreenActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void navigateToRegisterScreen() {
        Intent registerIntent = new Intent(this, RegisterScreenActivity.class);
        startActivity(registerIntent);
        finish();
    }
}
