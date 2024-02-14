package com.example.presentpal.view.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.presentpal.db.EventPlus;
import com.example.presentpal.view.fragment.PersonTabEditFragment;
import com.example.presentpal.view.fragment.PersonTabEventsFragment;
import com.example.presentpal.view.fragment.PersonTabCharacteristicsFragment;

import java.util.List;

public class PersonViewPagerAdapter extends FragmentStateAdapter {

    private List<EventPlus> eventsByPerson;
    public PersonViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<EventPlus> eventsByPerson) {
        super(fragmentActivity);
        this.eventsByPerson = eventsByPerson;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return PersonTabEventsFragment.newInstance(eventsByPerson);
            case 1:
                return new PersonTabCharacteristicsFragment();
            case 2:
                return PersonTabEditFragment.newInstance();
            default:
                return new PersonTabEventsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
