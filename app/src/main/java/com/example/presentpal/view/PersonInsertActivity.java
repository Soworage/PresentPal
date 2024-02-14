package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityPersonInsertBinding;
import com.example.presentpal.db.Person;
import com.example.presentpal.viewmodel.PersonInsertViewModel;

import java.io.Serializable;

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

        personInsertViewModel.personInsertOk.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long isInserted) {
                if (isInserted > -1) {
                    Toast.makeText(getApplication(), getResources().getString(R.string.person_insert_ok), Toast.LENGTH_SHORT).show();
                 finish();
                }
                else {
                    Toast.makeText(getApplication(), getResources().getString(R.string.person_insert_failed), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}