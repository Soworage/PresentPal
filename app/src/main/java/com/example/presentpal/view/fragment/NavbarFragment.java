package com.example.presentpal.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.presentpal.R;
import com.example.presentpal.databinding.FragmentNavbarBinding;
import com.example.presentpal.navigation.NavigationHandler;
import com.example.presentpal.view.EventInsertActivity;
import com.example.presentpal.view.MainActivity;
import com.example.presentpal.view.PersonInsertActivity;
import com.example.presentpal.view.PresentIdeaActivity;
import com.example.presentpal.viewmodel.PresentIdeaInsertViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class NavbarFragment extends Fragment implements NavigationHandler {

private FragmentNavbarBinding binding;
    public NavbarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_navbar, container, false);

        // Setzt die NavigationHandler-Instanz für das Binding
        binding.setNavigationHandler(this);

        // Gibt die Root-View des Bindings zurück
        return binding.getRoot();
    }

    @Override
    public void navigateToMainScreenActivity() {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    public void navigateToShowOptionsDialog() {

        final CharSequence[] options = {getString(R.string.insert_a_new_person), getString(R.string.insert_a_new_event), getString(R.string.insert_a_new_present_idea)};

        // AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.choose_an_action);

        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent;
                switch (which) {
                    case 0: // Person einfügen
                        intent = new Intent(getActivity(), PersonInsertActivity.class);
                        startActivity(intent);
                        break;
                    case 1: // Termin einfügen
                        intent = new Intent(getActivity(), EventInsertActivity.class);
                        startActivity(intent);
                        break;
                    case 2: // Geschenkidee einfügen

                        break;
                }
            }
        });

        builder.setNegativeButton(R.string.cancel, null);

        // Erstelle und zeige den AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}