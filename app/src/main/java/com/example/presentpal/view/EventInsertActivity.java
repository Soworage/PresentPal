package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityEventInsertBinding;
import com.example.presentpal.db.Person;
import com.example.presentpal.viewmodel.EventInsertViewModel;

import java.util.Calendar;

/**
 * Die Aktivität ermöglicht das Einfügen eines neuen Ereignisses durch den Benutzer.
 * Sie bietet eine Benutzeroberfläche, um die Details des Ereignisses wie Datum und Beziehung zu einer Person einzugeben.
 */
public class EventInsertActivity extends AppCompatActivity {

    private EventInsertViewModel eventInsertViewModel;
    private ActivityEventInsertBinding activityEventInsertBinding;

    /**
     * Initialisiert die Aktivität, bindet das ViewModel an die UI und beobachtet Änderungen in den Daten.
     *
     * @param savedInstanceState Zustand der Aktivität, wenn sie neu erstellt wird, nachdem sie zuvor zerstört wurde.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_insert);
        activityEventInsertBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_insert);
        eventInsertViewModel = new ViewModelProvider(this).get(EventInsertViewModel.class);
        activityEventInsertBinding.setLifecycleOwner(this);
        activityEventInsertBinding.setEventInsertViewModel(eventInsertViewModel);

        eventInsertViewModel.getPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer positionSelect) {
                if (positionSelect != null) {
                    eventInsertViewModel.setSelectedPerson(positionSelect);
                }
            }
        });

        eventInsertViewModel.getFinish().observe(this, finish -> {
            if (finish) {
                finish();
            }
        });
        // Öffnet einen DatePickerDialog, wenn das ViewModel es anfordert
        eventInsertViewModel.getCalendarOpen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    // https://www.tutlane.com/tutorial/android/android-datepicker-with-examples
                    final Calendar calendar = Calendar.getInstance();
                    int day = calendar.get(Calendar.DAY_OF_MONTH);
                    int month = calendar.get(Calendar.MONTH);
                    int year = calendar.get(Calendar.YEAR);
                    DatePickerDialog picker = new DatePickerDialog(EventInsertActivity.this,
                            (view, year1, monthOfYear, dayOfMonth) -> eventInsertViewModel.setDate(String.format("%02d", dayOfMonth) + "." + String.format("%02d", monthOfYear + 1) + "." + year1), year, month, day);
                    picker.show();
                }
            }
        });
        // Informiert den Benutzer über den Erfolg oder Misserfolg des Einfügens eines Ereignisses
        eventInsertViewModel.eventInsertOk.observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long isInserted) {
                if (isInserted > -1) {
                    Toast.makeText(getApplication(), getResources().getString(R.string.event_insert_ok), Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(getApplication(), getResources().getString(R.string.event_insert_failed), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}