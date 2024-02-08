package com.example.presentpal.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.model.PersonModel;
import com.example.presentpal.viewmodel.RegisterScreenViewModel;
import com.example.presentpal.databinding.ActivityRegisterScreenBinding;

public class RegisterScreenActivity extends AppCompatActivity {
    private RegisterScreenViewModel registerScreenViewModel;
    private ActivityRegisterScreenBinding activityRegisterScreenBinding;

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


