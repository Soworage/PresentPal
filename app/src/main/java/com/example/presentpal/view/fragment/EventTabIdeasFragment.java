package com.example.presentpal.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presentpal.R;
import com.example.presentpal.databinding.FragmentEventTabIdeasBinding;
import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.view.adapter.recylerview.EventPresentsRecyclerViewAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventTabIdeasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventTabIdeasFragment extends Fragment {

    private static final String ARG_PARAM1 = "presentIdeas";

    private List<PresentIdeaJoinPerson> presetIdeas;

    private FragmentEventTabIdeasBinding fragmentEventTabIdeasBinding;
    private EventPresentsRecyclerViewAdapter eventPresentsRecyclerViewAdapter;

    public EventTabIdeasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventTabIdeasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventTabIdeasFragment newInstance(String param1, String param2) {
        EventTabIdeasFragment fragment = new EventTabIdeasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_tab_ideas, container, false);
    }
}