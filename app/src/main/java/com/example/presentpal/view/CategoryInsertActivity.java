package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityCategoryInsertBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.Person;
import com.example.presentpal.viewmodel.CategoryInsertViewModel;

import java.util.List;

/**
 * Die Activity zur Eingabe neuer Kategorien in die App.
 * Sie ermöglicht es dem Benutzer, eine Kategorie hinzuzufügen, die dann in verschiedenen Teilen der App verwendet werden kann.
 * Die Klasse nutzt das MVVM-Pattern (Model-View-ViewModel) für eine saubere Trennung der Geschäftslogik von der UI-Logik.
 */
public class CategoryInsertActivity extends AppCompatActivity {

    private ActivityCategoryInsertBinding activityCategoryInsertBinding;

    private CategoryInsertViewModel categoryInsertViewModel;


    /**
     * Wird beim Start der Activity aufgerufen. Initialisiert die Datenbindung und das ViewModel.
     * Stellt die Verbindung zwischen der UI und den Daten her und beobachtet Veränderungen, um entsprechend zu reagieren.
     *
     * @param savedInstanceState Wenn die Aktivität neu erstellt wird, nachdem sie vom System beendet wurde,
     *                           enthält dies die Daten, die zuletzt an onSaveInstanceState übergeben wurden.
     *                           Andernfalls ist es null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_insert);
        activityCategoryInsertBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_insert);
        categoryInsertViewModel = new ViewModelProvider(this).get(CategoryInsertViewModel.class);
        activityCategoryInsertBinding.setLifecycleOwner(this);
        activityCategoryInsertBinding.setCategoryInsertViewModel(categoryInsertViewModel);

        categoryInsertViewModel.setCategory(getIntent().getStringExtra("category"));

        categoryInsertViewModel.getPosition().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer positionSelect) {
                if (positionSelect != null) {
                    categoryInsertViewModel.setSelectedPerson(positionSelect);
                }
            }
        });
        // Observe finish flag in the view model
        categoryInsertViewModel.getFinish().observe(this, finish -> {
            if (finish) {
                finish();
            }
        });
    }
}