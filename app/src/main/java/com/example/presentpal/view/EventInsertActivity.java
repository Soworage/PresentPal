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

public class EventInsertActivity extends AppCompatActivity {

    private EventInsertViewModel eventInsertViewModel;
    private ActivityEventInsertBinding activityEventInsertBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_insert);

        activityEventInsertBinding = DataBindingUtil.setContentView(this, R.layout.activity_event_insert);
        eventInsertViewModel = new ViewModelProvider(this).get(EventInsertViewModel.class);

        activityEventInsertBinding.setLifecycleOwner(this);
        activityEventInsertBinding.setEventInsertViewModel(eventInsertViewModel);

        eventInsertViewModel.getSelectedPerson().observe(this, new Observer<Person>() {
            @Override
            public void onChanged(Person selectedPerson) {
                CharSequence text = "Hello toast!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(EventInsertActivity.this, text, duration);
                toast.show();
                Log.i("EventInsertActivity", "Toast");
                if (selectedPerson != null) {

                }
            }
        });

        eventInsertViewModel.getCalendarOpen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                CharSequence text = "CalendarOpen changed!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(EventInsertActivity.this, text, duration);
                toast.show();
                    if(aBoolean){
                        // https://www.tutlane.com/tutorial/android/android-datepicker-with-examples
                        final Calendar calendar = Calendar.getInstance();
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int month = calendar.get(Calendar.MONTH);
                        int year = calendar.get(Calendar.YEAR);
                        DatePickerDialog  picker = new DatePickerDialog(EventInsertActivity.this,
                                (view, year1, monthOfYear, dayOfMonth) -> eventInsertViewModel.setDate(String.format("%02d",dayOfMonth) + "." + String.format("%02d", monthOfYear + 1) + "." + year1), year, month, day);
                        picker.show();
                    }
            }
        });
    }
}