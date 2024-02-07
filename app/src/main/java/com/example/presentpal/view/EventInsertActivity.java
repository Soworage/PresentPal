package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityEventInsertBinding;
import com.example.presentpal.viewmodel.EventInsertViewModel;

public class EventInsertActivity extends AppCompatActivity {

    private EventInsertViewModel eventInsertViewModel;
    private ActivityEventInsertBinding activityEventInsertBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_insert);

        activityEventInsertBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_insert);
        eventInsertViewModel = new ViewModelProvider(this).get(EventInsertActivity.class);

        activityEventInsertBinding.setLifecycleOwner(this);
        activityEventInsertBinding.setEventInsertViewModel(eventInsertViewModel);
    }
}