package com.example.presentpal.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presentpal.R;
import com.example.presentpal.databinding.FragmentPresentIdeaTabDetailsBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventPlus;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.viewmodel.fragment.PresentIdeaTabDetailsViewModel;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PresentIdeaTabDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PresentIdeaTabDetailsFragment extends Fragment {


    private PresentIdeaTabDetailsViewModel presentIdeaTabDetailsViewModel;

    public PresentIdea presentIdea;

    private static final String ARG_PARAM1 = "presentIdea";

    public PresentIdeaTabDetailsFragment() {
        // Required empty public constructor
    }

    public static PresentIdeaTabDetailsFragment newInstance(PresentIdea presentIdea) {
        PresentIdeaTabDetailsFragment fragment = new PresentIdeaTabDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("presentIdeaFragment", (Serializable) presentIdea);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            if (getArguments().containsKey("presentIdeaFragment")) {

                Serializable serializable = getArguments().getSerializable("presentIdeaFragment");

                if (serializable instanceof PresentIdea) {
                    presentIdea = (PresentIdea) serializable;
                }
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPresentIdeaTabDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_present_idea_tab_details, container, false);
        presentIdeaTabDetailsViewModel = new ViewModelProvider(this).get(PresentIdeaTabDetailsViewModel.class);

        presentIdeaTabDetailsViewModel.setPresentIdea(presentIdea);

        binding.setPresentIdeaTabDetailsViewModel(presentIdeaTabDetailsViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}