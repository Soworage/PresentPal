package com.example.presentpal.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presentpal.R;
import com.example.presentpal.databinding.FragmentEventTabIdeasBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.view.adapter.recylerview.EventPresentsRecyclerViewAdapter;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventTabIdeasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventTabIdeasFragment extends Fragment {

    private static final String ARG_PARAM1 = "presentIdeas";

    private List<PresentIdeaJoinPerson> presentIdeas;
    private Event event;

    private FragmentEventTabIdeasBinding fragmentEventTabIdeasBinding;
    private EventPresentsRecyclerViewAdapter eventPresentsRecyclerViewAdapter;

    public EventTabIdeasFragment() {
        // Required empty public constructor
    }

    /**
     * Statische Methode zum Erstellen einer neuen Instanz des EventTabIdeasFragment.
     *
     * @param presentIdeas Die Liste der PresentIdeaJoinPerson-Objekte.
     * @param event        Das Event-Objekt.
     * @return Eine neue Instanz des EventTabIdeasFragment.
     */
    public static EventTabIdeasFragment newInstance(List<PresentIdeaJoinPerson> presentIdeas, Event event) {

        EventTabIdeasFragment fragment = new EventTabIdeasFragment();
        Bundle args = new Bundle();

        args.putSerializable("presentIdeas", (Serializable) presentIdeas);
        args.putSerializable("event", (Serializable) event);
        fragment.setArguments(args);

        return fragment;
    }


    /**
     * Die Methode onCreate wird aufgerufen, wenn das Fragment erstellt wird.
     * Hier werden die Fragment-Argumente abgerufen und den entsprechenden Variablen zugewiesen.
     *
     * @param savedInstanceState Das Bundle-Objekt, das den Zustand des Fragments enthält.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().containsKey("presentIdeas") && getArguments().containsKey("event")) {
                Serializable serializableA = getArguments().getSerializable("presentIdeas");
                Serializable serializableB = getArguments().getSerializable("event");
                if (serializableA != null && serializableB != null) {
                    if (serializableA instanceof List<?> && serializableB instanceof Event) {
                        presentIdeas = (List<PresentIdeaJoinPerson>) serializableA;
                        event = (Event) serializableB;
                    }
                }
            }
        }
    }

    /**
     * Die Methode onCreateView wird aufgerufen, um das Layout des Fragments zu erstellen und anzuzeigen.
     * Hier wird das Layout aufgeblasen und das Binding-Objekt initialisiert.
     *
     * @param inflater           Der LayoutInflater, der zum Inflating des Layouts verwendet wird.
     * @param container          Die ViewGroup, in der das Fragment platziert wird.
     * @param savedInstanceState Das Bundle-Objekt, das den Zustand des Fragments enthält.
     * @return Die gerenderte View des Fragments.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentEventTabIdeasBinding = FragmentEventTabIdeasBinding.inflate(inflater, container, false);
        View rootView = fragmentEventTabIdeasBinding.getRoot();


        // Inflate the layout for this fragment
        eventPresentsRecyclerViewAdapter = new EventPresentsRecyclerViewAdapter(presentIdeas, event);
        fragmentEventTabIdeasBinding.fragmentEventIdeasRecyclerView.setAdapter(eventPresentsRecyclerViewAdapter);

        return rootView;
    }
}