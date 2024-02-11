package com.example.presentpal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityEventBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.view.adapter.viewpager.EventViewPagerAdapter;
import com.example.presentpal.view.adapter.viewpager.PersonViewPagerAdapter;
import com.example.presentpal.viewmodel.EventViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

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
        eventViewModel.getAllPresentIdeasAndPresents();
        activityEventBinding.setLifecycleOwner(this);
        activityEventBinding.setEventViewModel(eventViewModel);

        TabLayout tabLayout = findViewById(R.id.event_tabs);
        ViewPager2 viewPager = findViewById(R.id.event_viewpager);

        eventViewModel.getAllPresentIdeas().observe(this, presentIdeas -> {
            int value = eventViewModel.readyState.getValue();
            eventViewModel.readyState.setValue(value+1);
        });

        eventViewModel.getAllPresents().observe(this, presents -> {
            int value = eventViewModel.readyState.getValue();
            eventViewModel.readyState.setValue(value+1);
        });


        eventViewModel.getReadyState().observe(this, readyState -> {
            Log.d("EventActivity", "readyState: "+ readyState);
            if (readyState == 2 && eventViewModel.getAllPresentIdeas() != null && eventViewModel.getAllPresents() != null) {

                List<PresentIdeaJoinPerson> presentIdeasWithPersonList = eventViewModel.getAllPresentIdeas().getValue();
                List<PresentIdeaJoinPerson> presentWithPersonList = eventViewModel.getAllPresents().getValue();

            EventViewPagerAdapter adapter = new EventViewPagerAdapter(this, presentIdeasWithPersonList, presentWithPersonList);
            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    switch (position) {
                        case 0:
                            tab.setText("Ideas" + " (" + presentIdeasWithPersonList.size() + ")");
                            break;
                        case 1:
                            tab.setText("Presents" + " (" + presentWithPersonList.size() + ")");
                            break;
                        case 2:
                            tab.setText("Edit Event");
                            break;
                        default:
                            tab.setText("Error");
                            break;
                    }

                }
            }).attach();

            eventViewModel.readyState.setValue(0);
        }

        });


    }
}