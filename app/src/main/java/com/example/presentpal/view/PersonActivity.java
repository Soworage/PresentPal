package com.example.presentpal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityPersonBinding;
import com.example.presentpal.db.Person;
import com.example.presentpal.view.adapter.viewpager.PersonViewPagerAdapter;
import com.example.presentpal.viewmodel.PersonViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PersonActivity extends AppCompatActivity {

    private PersonViewModel personViewModel;
    private ActivityPersonBinding activityPersonBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        activityPersonBinding = DataBindingUtil.setContentView(this, R.layout.activity_person);
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);

        activityPersonBinding.setLifecycleOwner(this);
        activityPersonBinding.setPersonViewModel(personViewModel);

        TabLayout tabLayout = findViewById(R.id.person_tabs);
        ViewPager2 viewPager = findViewById(R.id.person_viewpager);

        https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
        personViewModel.person.setValue((Person) getIntent().getSerializableExtra("person"));

        PersonViewPagerAdapter adapter = new PersonViewPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("Tab" + position +1);
            }
        }).attach();
    }
}