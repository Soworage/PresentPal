package com.example.presentpal.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityMainBinding;
import com.example.presentpal.viewmodel.CategoryViewModel;
import com.example.presentpal.viewmodel.LoginScreenViewModel;
import com.example.presentpal.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding.setMainActivityViewModel(mainActivityViewModel);
        activityMainBinding.setLifecycleOwner(this);

        mainActivityViewModel.selectedCategory.observe(this, category -> {
            if (category != null) {
                navigateToCategoryActivity(category);
            }
        });
    }

    private void navigateToCategoryActivity(String category) {
        Intent intent = new Intent(this, CatergoryActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }
}