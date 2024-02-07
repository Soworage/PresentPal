
package com.example.presentpal.view.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.presentpal.view.fragment.EventTabEditFragment;
import com.example.presentpal.view.fragment.EventTabIdeasFragment;
import com.example.presentpal.view.fragment.EventTabPresentsFragment;

public class EventViewPagerAdapter extends FragmentStateAdapter {
    public EventViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new EventTabIdeasFragment();
            case 1:
                return new EventTabPresentsFragment();
            case 2:
                return new EventTabEditFragment();
            default:
                return new EventTabIdeasFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
