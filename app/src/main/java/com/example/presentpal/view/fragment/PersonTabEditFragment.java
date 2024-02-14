package com.example.presentpal.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presentpal.R;
import com.example.presentpal.databinding.FragmentPersonTabEditBinding;
import com.example.presentpal.db.Person;
import com.example.presentpal.viewmodel.fragment.PersonTabEditViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PersonTabEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PersonTabEditFragment extends Fragment {


    private PersonTabEditViewModel personTabEditViewModel;

    public  FragmentPersonTabEditBinding binding;

    public PersonTabEditFragment() {
        // Required empty public constructor
    }


    public static PersonTabEditFragment newInstance() {
        return new PersonTabEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

       binding = DataBindingUtil.inflate(inflater,R.layout.fragment_person_tab_edit,container,false);
        personTabEditViewModel = new ViewModelProvider(this).get(PersonTabEditViewModel.class);

        personTabEditViewModel.person.setValue((Person) getActivity().getIntent().getSerializableExtra("person"));
        binding.setPersonTabEditViewModel(personTabEditViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}