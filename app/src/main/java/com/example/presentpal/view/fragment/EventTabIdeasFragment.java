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

    private FragmentEventTabIdeasBinding fragmentEventTabIdeasBinding;
    private EventPresentsRecyclerViewAdapter eventPresentsRecyclerViewAdapter;

    public EventTabIdeasFragment() {
        // Required empty public constructor
    }


    public static EventTabIdeasFragment newInstance(List<PresentIdeaJoinPerson> presentIdeas) {

        EventTabIdeasFragment fragment = new EventTabIdeasFragment();
        Bundle args = new Bundle();

        args.putSerializable("presentIdeas", (Serializable) presentIdeas);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().containsKey("presentIdeas")) {
                Serializable serializable = getArguments().getSerializable("presentIdeas");
                if (serializable != null) {
                    if (serializable instanceof List<?>) {
                        presentIdeas = (List<PresentIdeaJoinPerson>) serializable;
                    }
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentEventTabIdeasBinding = FragmentEventTabIdeasBinding.inflate(inflater, container, false);
        View rootView = fragmentEventTabIdeasBinding.getRoot();


        // Inflate the layout for this fragment
        eventPresentsRecyclerViewAdapter = new EventPresentsRecyclerViewAdapter(presentIdeas);
        fragmentEventTabIdeasBinding.fragmentEventIdeasRecyclerView.setAdapter(eventPresentsRecyclerViewAdapter);

        return rootView;
    }
}