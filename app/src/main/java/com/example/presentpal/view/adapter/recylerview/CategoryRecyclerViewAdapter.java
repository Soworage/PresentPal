package com.example.presentpal.view.adapter.recylerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presentpal.databinding.ActivityCategoryRowBinding;
import com.example.presentpal.viewmodel.CategoryViewModel;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.PersonWithEventsViewHolder> {

    //https://www.youtube.com/watch?v=0eV7iB109ME
    private List<CategoryViewModel.PersonWithEvents> personWithEvents;

    public CategoryRecyclerViewAdapter(List<CategoryViewModel.PersonWithEvents> personWithEventsEvents) {
        this.personWithEvents = personWithEventsEvents;
    }

    @NonNull
    @Override
    public PersonWithEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ActivityCategoryRowBinding activityCategoryRowBinding = ActivityCategoryRowBinding.inflate(layoutInflater, parent, false);
        return new PersonWithEventsViewHolder(activityCategoryRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonWithEventsViewHolder holder, int position) {
        CategoryViewModel.PersonWithEvents personWE = personWithEvents.get(position);
        holder.activityCategoryRowBinding.setPersonWithEvents(personWE);
        holder.activityCategoryRowBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return personWithEvents.size();
    }

    public static class PersonWithEventsViewHolder extends RecyclerView.ViewHolder {

        ActivityCategoryRowBinding activityCategoryRowBinding;

        public PersonWithEventsViewHolder(@NonNull ActivityCategoryRowBinding activityCategoryRowBinding) {
            super(activityCategoryRowBinding.getRoot());
            this.activityCategoryRowBinding = activityCategoryRowBinding;

        }
    }
}
