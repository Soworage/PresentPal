package com.example.presentpal.view.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presentpal.R;
import com.example.presentpal.databinding.FragmentPresentIdeaTabEditBinding;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.viewmodel.fragment.PresentIdeaTabEditViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PresentIdeaTabEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PresentIdeaTabEditFragment extends Fragment {

    private PresentIdeaTabEditViewModel presentIdeaTabEditViewModel;

    public PresentIdeaTabEditFragment() {
        // Required empty public constructor
    }


    public static PresentIdeaTabEditFragment newInstance(String param1, String param2) {

        return new PresentIdeaTabEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentPresentIdeaTabEditBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_present_idea_tab_edit,container,false);
        presentIdeaTabEditViewModel = new ViewModelProvider(this).get(PresentIdeaTabEditViewModel.class);

        presentIdeaTabEditViewModel.presentIdea.setValue((PresentIdea) getActivity().getIntent().getSerializableExtra("presentIdea"));
        binding.setPresentIdeaTabEditViewModel(presentIdeaTabEditViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }
}