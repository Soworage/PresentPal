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

public class CategoryInsertActivity extends AppCompatActivity {

    private ActivityCategoryInsertBinding activityCategoryInsertBinding;

    private CategoryInsertViewModel categoryInsertViewModel;

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

        categoryInsertViewModel.getFinish().observe(this, finish -> {
            if (finish) {
                finish();
            }
        });
    }
}