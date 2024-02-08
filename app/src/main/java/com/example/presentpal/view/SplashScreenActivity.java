package com.example.presentpal.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.viewmodel.SplashScreenViewModel;

public class SplashScreenActivity extends AppCompatActivity {

    private SplashScreenViewModel splashScreenViewModel;
    private final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_splash_screen); //

        splashScreenViewModel = new ViewModelProvider(this).get(SplashScreenViewModel.class);

        new Handler().postDelayed(() -> {
            // LiveData beobachten
            splashScreenViewModel.getNavigateTo().observe(this, navigateTo -> {
                if (navigateTo != null) {
                    if (navigateTo == SplashScreenViewModel.NavigateTo.LOGIN) {
                        navigateToLoginScreen();
                    } else if (navigateTo == SplashScreenViewModel.NavigateTo.REGISTER) {
                        navigateToRegisterScreen();
                    }
                }
            });

            // Überprüfung starten
            splashScreenViewModel.checkUserRegistration();
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
