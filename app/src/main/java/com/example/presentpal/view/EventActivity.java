package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityEventBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.viewmodel.EventViewModel;

public class EventActivity extends AppCompatActivity {

   private EventViewModel eventViewModel;

   private ActivityEventBinding activityEventBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        activityEventBinding = DataBindingUtil.setContentView(this, R.layout.activity_event);
        eventViewModel = new ViewModelProvider(this).get(EventViewModel.class);

        eventViewModel.event.setValue((Event) getIntent().getSerializableExtra("event"));


        activityEventBinding.setLifecycleOwner(this);
        activityEventBinding.setEventViewModel(eventViewModel);




    }
}