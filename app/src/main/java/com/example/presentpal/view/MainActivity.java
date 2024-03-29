package com.example.presentpal.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityMainBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.db.Person;
import com.example.presentpal.view.fragment.NavbarFragment;
import com.example.presentpal.viewmodel.CategoryViewModel;
import com.example.presentpal.viewmodel.LoginScreenViewModel;
import com.example.presentpal.viewmodel.MainActivityViewModel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Hauptaktivität der Anwendung, die als Einstiegspunkt für die Benutzerinteraktion dient.
 * Zeigt anstehende Ereignisse an und ermöglicht die Navigation durch verschiedene Kategorien der App.
 */
public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;

    /**
     * Wird aufgerufen, wenn die Aktivität erstellt wird. Initialisiert das ViewModel, bindet Daten und
     * setzt Event-Listener.
     *
     * @param savedInstanceState Zustand der Aktivität, wenn sie neu erstellt wird, nachdem sie zuvor zerstört wurde.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        mainActivityViewModel.getUpcomingEvents();

        activityMainBinding.setMainActivityViewModel(mainActivityViewModel);
        activityMainBinding.setLifecycleOwner(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new NavbarFragment())
                    .commit();
        }
        mainActivityViewModel.selectedCategory.observe(this, category -> {
            navigateToCategoryActivity(category);
        });

        mainActivityViewModel.getUpcomingEvents().observe(this, new Observer<List<EventJoinPerson>>() {
            @Override
            public void onChanged(List<EventJoinPerson> upcomingEvents) {
                if (upcomingEvents != null) {
                    Log.i("MainActivity", "Test" + upcomingEvents.size());
                    if (upcomingEvents.size() > 0) {
                        activityMainBinding.setEventJoinPersonA(upcomingEvents.get(0));
                        activityMainBinding.setEventJoinPersonB(new EventJoinPerson(new Event(1, "-", "-", 0, "-", 0, "-", 0f), new Person("-", "-", "-", false)));

                    }
                    if (upcomingEvents.size() > 1) {
                        activityMainBinding.setEventJoinPersonB(upcomingEvents.get(1));
                    }
                    if (upcomingEvents.size() == 0) {
                        activityMainBinding.setEventJoinPersonA(new EventJoinPerson(new Event(1, "-", "-", 0, "-", 0, "-", 0f), new Person("-", "-", "-", false)));
                        activityMainBinding.setEventJoinPersonB(new EventJoinPerson(new Event(1, "-", "-", 0, "-", 0, "-", 0f), new Person("-", "-", "-", false)));

                    }
                }
            }
        });
    }
    /**
     * Navigiert zur Activity, die der ausgewählten Kategorie entspricht.
     *
     * @param category Die ausgewählte Kategorie als String.
     */
    private void navigateToCategoryActivity(String category) {
        Intent intent = new Intent(this, CatergoryActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }


}
