
package com.example.presentpal.view.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.presentpal.db.Event;
import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.view.fragment.EventTabEditFragment;
import com.example.presentpal.view.fragment.EventTabIdeasFragment;
import com.example.presentpal.view.fragment.EventTabPresentsFragment;

import java.util.List;

/**
 * Ein ViewPager-Adapter für die Anzeige von Fragmenten in einem ViewPager.
 */
public class EventViewPagerAdapter extends FragmentStateAdapter {

    private List<PresentIdeaJoinPerson> presentIdeasList;
    private List<PresentIdeaJoinPerson> presentList;

    private Event event;

    /**
     * Konstruktor für den EventViewPagerAdapter.
     *
     * @param fragmentActivity Die FragmentActivity, zu der der ViewPager gehört.
     * @param presentIdeasList Eine Liste von PresentIdeaJoinPerson-Objekten für Ideen, die einem Event zugeordnet sind.
     * @param presentList      Eine Liste von PresentIdeaJoinPerson-Objekten für Geschenke, die einem Event zugeordnet sind.
     * @param event            Das Event-Objekt, das im ViewPager angezeigt werden soll.
     */
    public EventViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, List<PresentIdeaJoinPerson> presentIdeasList, List<PresentIdeaJoinPerson> presentList, Event event) {
        super(fragmentActivity);
        this.presentIdeasList = presentIdeasList;
        this.presentList = presentList;
        this.event = event;

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
                return EventTabIdeasFragment.newInstance(presentIdeasList, event);
            case 1:
                return EventTabIdeasFragment.newInstance(presentList, event);
            case 2:
                return new EventTabEditFragment();
            default:
                return new EventTabEditFragment();
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
