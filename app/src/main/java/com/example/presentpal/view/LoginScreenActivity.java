package com.example.presentpal.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityLoginScreenBinding;
import com.example.presentpal.viewmodel.LoginScreenViewModel;


public class LoginScreenActivity extends AppCompatActivity {
    private LoginScreenViewModel loginScreenViewModel;
    private ActivityLoginScreenBinding activityLoginScreenBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_login_screen);
        loginScreenViewModel = new ViewModelProvider(this).get(LoginScreenViewModel.class);
        activityLoginScreenBinding.setLifecycleOwner(this);
        activityLoginScreenBinding.setLoginScreenViewModel(loginScreenViewModel);

        loginScreenViewModel.getIsPasswordCorrect().observe(this, isPasswordCorrect -> {

            if (isPasswordCorrect != null && isPasswordCorrect > 0) {

                Intent loginIntent = new Intent(this, CatergoryActivity.class);
                startActivity(loginIntent);
                finish();
            } else {
                CharSequence text = "Password wrong";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(LoginScreenActivity.this, text, duration);
                toast.show();
            }
        });

    }
}
