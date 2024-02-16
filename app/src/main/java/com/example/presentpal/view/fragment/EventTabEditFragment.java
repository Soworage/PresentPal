package com.example.presentpal.view.fragment;

import static android.content.Intent.getIntent;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presentpal.R;
import com.example.presentpal.databinding.FragmentEventTabEditBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.viewmodel.fragment.EventTabEditViewModel;

/**
 * Ein Fragment für die Bearbeitung von Eventdetails.
 */
public class EventTabEditFragment extends Fragment {

    private EventTabEditViewModel eventTabEditViewModel;


    public EventTabEditFragment() {
        // Required empty public constructor
    }

    /**
     * Statische Methode zum Erstellen einer neuen Instanz des EventTabEditFragment.
     *
     * @return Eine neue Instanz des EventTabEditFragment.
     */
    public static EventTabEditFragment newInstance() {
        return new EventTabEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentEventTabEditBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_tab_edit, container, false);
        eventTabEditViewModel = new ViewModelProvider(this).get(EventTabEditViewModel.class);

        eventTabEditViewModel.event.setValue((Event) getActivity().getIntent().getSerializableExtra("event"));
        binding.setEventTabEditViewModel(eventTabEditViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}