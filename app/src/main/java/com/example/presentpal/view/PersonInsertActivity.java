package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityPersonInsertBinding;
import com.example.presentpal.viewmodel.PersonInsertViewModel;

public class PersonInsertActivity extends AppCompatActivity {

    private PersonInsertViewModel personInsertViewModel;
    private ActivityPersonInsertBinding activityPersonInsertBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_insert);

        activityPersonInsertBinding = DataBindingUtil.setContentView(this, R.layout.activity_person_insert);
        personInsertViewModel = new ViewModelProvider(this).get(PersonInsertViewModel.class);

        activityPersonInsertBinding.setLifecycleOwner(this);
        activityPersonInsertBinding.setPersonInsertViewModel(personInsertViewModel);
    }
}