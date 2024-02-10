package com.example.presentpal.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityCatergoryBinding;
import com.example.presentpal.view.adapter.recylerview.CategoryRecyclerViewAdapter;
import com.example.presentpal.viewmodel.CategoryViewModel;

public class CatergoryActivity extends AppCompatActivity {

    private CategoryViewModel categoryViewModel;
    private ActivityCatergoryBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catergory);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_catergory);
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);

        binding.setLifecycleOwner(this);
        binding.setCategoryViewModel(categoryViewModel);

        //https://www.youtube.com/watch?v=0eV7iB109ME
        categoryViewModel.getAllEventsWithPersonByCategory().observe(this,  personWithEvents -> {
            CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(personWithEvents);
            binding.catergoryRecyclerView.setAdapter(categoryRecyclerViewAdapter);
            binding.catergoryRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        });




    }

    public void buildRecycler(){

        //CategoryRecyclerViewAdapter categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter();
        //activityCategoryBinding.setAd(CategoryRecyclerViewAdapter );
    }
}