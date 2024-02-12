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
import androidx.lifecycle.ViewModelProvider;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityMainBinding;
import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.view.fragment.NavbarFragment;
import com.example.presentpal.viewmodel.CategoryViewModel;
import com.example.presentpal.viewmodel.LoginScreenViewModel;
import com.example.presentpal.viewmodel.MainActivityViewModel;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mainActivityViewModel;
    private ActivityMainBinding activityMainBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        activityMainBinding.setMainActivityViewModel(mainActivityViewModel);
        activityMainBinding.setLifecycleOwner(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, new NavbarFragment())
                    .commit();
        }
        mainActivityViewModel.selectedCategory.observe(this, category -> {
            if (category != null) {
                navigateToCategoryActivity(category);
            }
        });
    }

    private void navigateToCategoryActivity(String category) {
        Intent intent = new Intent(this, CatergoryActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    }

//        public LiveData<String> getFormattedEvent ( int eventIndex){
//            MutableLiveData<String> formattedEvent = new MutableLiveData<>();
//            upcomingEvents.observeForever(events -> {
//                if (events != null && events.size() > eventIndex) {
//                    Event event = events.get(eventIndex);
//                    String formatted = event.getTitle() + ", " + event.integerToDate(event.getDate()) + " (" + event.getPersonNickname() + ")";
//                    formattedEvent.setValue(formatted);
//                } else {
//                    formattedEvent.setValue("Keine weiteren Events");
//                }
//            });
//            return formattedEvent;
//        }
}
