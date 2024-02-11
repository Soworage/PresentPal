
package com.example.presentpal.view.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.view.fragment.EventTabEditFragment;
import com.example.presentpal.view.fragment.EventTabIdeasFragment;
import com.example.presentpal.view.fragment.EventTabPresentsFragment;

import java.util.List;

public class EventViewPagerAdapter extends FragmentStateAdapter {

    private List<PresentIdeaJoinPerson> presentIdeasList;
    private List<PresentIdeaJoinPerson> presentList;

    public EventViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<PresentIdeaJoinPerson> presentIdeasList, List<PresentIdeaJoinPerson> presentList) {
        super(fragmentActivity);
        this.presentIdeasList = presentIdeasList;
        this.presentList = presentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                //return EventTabIdeasFragment.newInstance(presentIdeasList);
            case 1:
               // return new EventTabPresentsFragment.newInstance(presentList);
            case 2:
                return new EventTabEditFragment();
            default:
                return new EventTabEditFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
