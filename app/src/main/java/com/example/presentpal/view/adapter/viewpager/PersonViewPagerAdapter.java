package com.example.presentpal.view.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.presentpal.view.fragment.PerosnTabEditFragment;
import com.example.presentpal.view.fragment.PersonTabEventsFragment;
import com.example.presentpal.view.fragment.PersonTabCharacteristicsFragment;

public class PersonViewPagerAdapter extends FragmentStateAdapter {
    public PersonViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new PersonTabEventsFragment();
            case 1:
                return new PersonTabCharacteristicsFragment();
            case 2:
                return new PerosnTabEditFragment();
            default:
                return new PersonTabEventsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
