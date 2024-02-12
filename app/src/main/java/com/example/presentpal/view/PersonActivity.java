package com.example.presentpal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityPersonBinding;
import com.example.presentpal.db.EventPlus;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.view.adapter.viewpager.PersonViewPagerAdapter;
import com.example.presentpal.viewmodel.PersonViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class PersonActivity extends AppCompatActivity {

    private PersonViewModel personViewModel;
    private ActivityPersonBinding activityPersonBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        activityPersonBinding = DataBindingUtil.setContentView(this, R.layout.activity_person);
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);
       // https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
       Person intentPerson = (Person) getIntent().getSerializableExtra("person");
        personViewModel.getEventsByPerson(intentPerson.getId());
        activityPersonBinding.setLifecycleOwner(this);
        activityPersonBinding.setPersonViewModel(personViewModel);

        TabLayout tabLayout = findViewById(R.id.person_tabs);
        ViewPager2 viewPager = findViewById(R.id.person_viewpager);


        personViewModel.getPersonById(intentPerson.getId()).observe(this,  personById -> {
            activityPersonBinding.setPerson(personById);
        });

        personViewModel.getEventsByPerson(intentPerson.getId()).observe(this,  eventsByPerson -> {
            Log.i("PersonActivity", "eventsByPerson changed");
            PersonViewPagerAdapter adapter = new PersonViewPagerAdapter(this, eventsByPerson);
            viewPager.setAdapter(adapter);

            new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                    switch (position) {
                        case 0:
                            tab.setText("Events" + " (" + eventsByPerson.size() + ")");
                            break;
                        case 1:
                            tab.setText("Interests");
                            break;
                        case 2:
                            tab.setText("Edit Profil");
                            break;
                        default:
                            tab.setText("Error");
                            break;
                    }

                }
            }).attach();
        });

    }
}