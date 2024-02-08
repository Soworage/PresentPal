package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityCatergoryBinding;
import com.example.presentpal.viewmodel.CategoryViewModel;

public class CatergoryActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private ActivityCatergoryBinding activityCategoryBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catergory);
        activityCategoryBinding = DataBindingUtil.setContentView(this,R.layout.activity_catergory);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        activityCategoryBinding.setLifecycleOwner(this);
        activityCategoryBinding.setCategoryViewModel(categoryViewModel);

    }
}