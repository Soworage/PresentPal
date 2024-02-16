package com.example.presentpal.view.adapter.recylerview;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presentpal.databinding.ActivityCategoryRowBinding;
import com.example.presentpal.view.PersonActivity;
import com.example.presentpal.viewmodel.CategoryViewModel;

import java.util.List;

/**
 * Ein RecyclerView.Adapter, der eine Liste von {@link CategoryViewModel.PersonWithEvents} Objekten anzeigt.
 */
public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.PersonWithEventsViewHolder> {

    //https://www.youtube.com/watch?v=0eV7iB109ME
    private List<CategoryViewModel.PersonWithEvents> personWithEvents;

    /**
     * Konstruktor für den CategoryRecyclerViewAdapter.
     *
     * @param personWithEventsEvents Die Liste von PersonWithEvents Objekten, die im RecyclerView angezeigt werden sollen.
     */
    public CategoryRecyclerViewAdapter(List<CategoryViewModel.PersonWithEvents> personWithEventsEvents) {
        this.personWithEvents = personWithEventsEvents;
    }

    /**
     * Erstellt einen neuen ViewHolder, der an den RecyclerView gebunden werden kann.
     *
     * @param parent   Die Elternansicht, in der der ViewHolder platziert wird.
     * @param viewType Der Typ des angeforderten Ansichts-Typs.
     * @return Ein neuer {@link PersonWithEventsViewHolder} für den RecyclerView.
     */
    @NonNull
    @Override
    public PersonWithEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ActivityCategoryRowBinding activityCategoryRowBinding = ActivityCategoryRowBinding.inflate(layoutInflater, parent, false);
        return new PersonWithEventsViewHolder(activityCategoryRowBinding);
    }

    /**
     * Füllt die Daten in den ViewHolder-Elementen an einer bestimmten Position aus.
     *
     * @param holder   Der ViewHolder, der die Daten anzeigen soll.
     * @param position Die Position des Elements in der RecyclerView.
     */
    @Override
    public void onBindViewHolder(@NonNull PersonWithEventsViewHolder holder, int position) {
        CategoryViewModel.PersonWithEvents personWE = personWithEvents.get(position);
        holder.activityCategoryRowBinding.setPersonWithEvents(personWE);
        holder.activityCategoryRowBinding.executePendingBindings();


        // https://www.youtube.com/watch?v=0eV7iB109ME
        holder.activityCategoryRowBinding.categoryRowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PersonActivity.class);
                intent.putExtra("person", personWE.getPerson());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return personWithEvents.size();
    }

    /**
     * ViewHolder für PersonWithEvents Objekte in einem RecyclerView.
     */
    public static class PersonWithEventsViewHolder extends RecyclerView.ViewHolder {

        ActivityCategoryRowBinding activityCategoryRowBinding;

        /**
         * Konstruktor für den PersonWithEventsViewHolder.
         *
         * @param activityCategoryRowBinding Das Binding für das Layout eines einzelnen Elements in der Liste.
         */
        public PersonWithEventsViewHolder(@NonNull ActivityCategoryRowBinding activityCategoryRowBinding) {
            super(activityCategoryRowBinding.getRoot());
            this.activityCategoryRowBinding = activityCategoryRowBinding;

        }
    }
}
