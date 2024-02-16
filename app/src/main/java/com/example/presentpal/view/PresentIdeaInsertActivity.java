package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityPresentIdeaInsertBinding;
import com.example.presentpal.viewmodel.PresentIdeaInsertViewModel;

/**
 * Aktivität für das Einfügen einer neuen Geschenkidee. Diese Aktivität stellt eine Benutzeroberfläche bereit,
 * auf der Benutzer Details zu einer neuen Geschenkidee eingeben und diese speichern können.
 */
public class PresentIdeaInsertActivity extends AppCompatActivity {


    private PresentIdeaInsertViewModel presentIdeaInsertViewModel;

    private ActivityPresentIdeaInsertBinding activityPresentIdeaInsertBinding;

    /**
     * Wird aufgerufen, wenn die Aktivität erstellt wird. Initialisiert das ViewModel, bindet Daten
     * und stellt die Funktionalität zum Einfügen einer neuen Geschenkidee bereit.
     *
     * @param savedInstanceState Zustand der Aktivität, wenn sie neu erstellt wird, nachdem sie zuvor zerstört wurde.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_idea_insert);
        activityPresentIdeaInsertBinding = DataBindingUtil.setContentView(this, R.layout.activity_present_idea_insert);
        presentIdeaInsertViewModel = new ViewModelProvider(this).get(PresentIdeaInsertViewModel.class);
        activityPresentIdeaInsertBinding.setLifecycleOwner(this);
        activityPresentIdeaInsertBinding.setPresentIdeaInsertViewModel(presentIdeaInsertViewModel);


        presentIdeaInsertViewModel.getPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer positionSelect) {
                if (positionSelect != null) {
                    presentIdeaInsertViewModel.setSelectedPerson(positionSelect);
                }
            }
        });

        presentIdeaInsertViewModel.presentIdeaInsertOk.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long isInserted) {
                if (isInserted > -1) {
                    Toast.makeText(getApplication(), getResources().getString(R.string.present_idea_insert_ok), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplication(), getResources().getString(R.string.present_idea_insert_failed), Toast.LENGTH_SHORT).show();
                }

            }
        });

        presentIdeaInsertViewModel.getFinish().observe(this, finish -> {
            if (finish) {
                finish();
            }
        });
    }
}