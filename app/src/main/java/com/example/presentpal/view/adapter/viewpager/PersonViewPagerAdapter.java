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

/**
 * Ein ViewPager-Adapter für die Anzeige von Fragmenten in einem ViewPager.
 */
public class PersonViewPagerAdapter extends FragmentStateAdapter {

    private List<EventPlus> eventsByPerson;

    /**
     * Konstruktor für den PersonViewPagerAdapter.
     *
     * @param fragmentActivity Die FragmentActivity, zu der der ViewPager gehört.
     * @param eventsByPerson   Eine Liste von EventPlus-Objekten, die den Ereignissen einer Person entsprechen.
     */
    public PersonViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<EventPlus> eventsByPerson) {
        super(fragmentActivity);
        this.eventsByPerson = eventsByPerson;
    }

    /**
     * Erstellt ein neues Fragment an einer bestimmten Position im ViewPager.
     *
     * @param position Die Position des Fragments im ViewPager.
     * @return Ein neues Fragment, das an der Position im ViewPager erstellt wurde.
     */
    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
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

    /**
     * Gibt die Gesamtanzahl der Fragmente im ViewPager zurück.
     *
     * @return Die Anzahl der Fragmente im ViewPager.
     */
    @Override
    public int getItemCount() {
        return 3;
    }
}
