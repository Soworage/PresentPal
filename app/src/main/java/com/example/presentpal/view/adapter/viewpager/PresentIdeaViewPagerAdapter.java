

package com.example.presentpal.view.adapter.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.view.fragment.EventTabEditFragment;
import com.example.presentpal.view.fragment.EventTabIdeasFragment;
import com.example.presentpal.view.fragment.EventTabPresentsFragment;
import com.example.presentpal.view.fragment.PresentIdeaTabDetailsFragment;
import com.example.presentpal.view.fragment.PresentIdeaTabEditFragment;

/**
 * Ein ViewPager-Adapter für die Anzeige von Fragmenten in einem ViewPager.
 */
public class PresentIdeaViewPagerAdapter extends FragmentStateAdapter {

    public PresentIdea presentIdea;

    /**
     * Konstruktor für den PresentIdeaViewPagerAdapter.
     *
     * @param fragmentActivity Die FragmentActivity, zu der der ViewPager gehört.
     * @param presentIdea      Das PresentIdea-Objekt, das im ViewPager angezeigt wird.
     */
    public PresentIdeaViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, PresentIdea presentIdea) {
        super(fragmentActivity);
        this.presentIdea = presentIdea;
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
                return PresentIdeaTabDetailsFragment.newInstance(presentIdea);
            case 1:
                return new PresentIdeaTabEditFragment();
            default:
                return new PresentIdeaTabDetailsFragment();
        }
    }

    /**
     * Gibt die Gesamtanzahl der Fragmente im ViewPager zurück.
     *
     * @return Die Anzahl der Fragmente im ViewPager.
     */
    @Override
    public int getItemCount() {
        return 2;
    }
}
