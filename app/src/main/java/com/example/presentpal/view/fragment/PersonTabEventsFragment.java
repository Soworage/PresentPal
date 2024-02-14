package com.example.presentpal.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presentpal.R;
import com.example.presentpal.databinding.FragmentPersonTabEventsBinding;
import com.example.presentpal.db.EventPlus;
import com.example.presentpal.view.adapter.recylerview.PersonEventsRecyclerViewAdapter;
import com.example.presentpal.viewmodel.PersonViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonTabEventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonTabEventsFragment extends Fragment {

    private static final String ARG_PARAM1 = "eventsByPerson";

    private List<EventPlus> eventsByPerson;


    private FragmentPersonTabEventsBinding fragmentPersonTabEventsBinding;

    private PersonEventsRecyclerViewAdapter personEventsRecyclerViewAdapter;

    public PersonTabEventsFragment() {
        // Required empty public constructor
    }


    // prompt: this is the newInstance function, is there a way to use a List<> for param1?
    // prompt: and if i have a list of objects?
    // prompt: can i use Serializable instead?
    public static PersonTabEventsFragment newInstance(List<EventPlus> eventsByPerson) {
        PersonTabEventsFragment fragment = new PersonTabEventsFragment();
        Bundle args = new Bundle();

        args.putSerializable("eventsByPersonFragment", (Serializable) eventsByPerson);
        fragment.setArguments(args);
        return fragment;
    }

    //prompt: if i use Serializable, how do I retrieve my arguments?
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            if (getArguments().containsKey("eventsByPersonFragment")) {

                Serializable serializable = getArguments().getSerializable("eventsByPersonFragment");

                if (serializable instanceof List<?>) {
                    eventsByPerson = (List<EventPlus>) serializable;
                    if (eventsByPerson == null) {
                        Log.i("EventTabFrag", "get eventsByPerson is null");
                    } else {
                        Log.i("EventTabFrag", "get eventsByPerson not null");
                    }
                }
            }
        }
    }

    // promt: is it possible to have an recycler view inside of a fragment?
    // promt: is it possible to use databinding on this Fragment class?

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentPersonTabEventsBinding = FragmentPersonTabEventsBinding.inflate(inflater, container, false);
        View rootView = fragmentPersonTabEventsBinding.getRoot();


        // Inflate the layout for this fragment
        personEventsRecyclerViewAdapter = new PersonEventsRecyclerViewAdapter(eventsByPerson);
        fragmentPersonTabEventsBinding.fragmentPersonIdeasRecyclerView.setAdapter(personEventsRecyclerViewAdapter);

        return rootView;
    }

}