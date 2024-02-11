package com.example.presentpal.view.adapter.recylerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presentpal.databinding.FragmentPersonTabEventsRowsBinding;
import com.example.presentpal.db.EventPlus;
import com.example.presentpal.view.fragment.PersonTabEventsFragment;

import java.util.List;

public class PersonEventsRecyclerViewAdapter extends RecyclerView.Adapter<PersonEventsRecyclerViewAdapter.EventsByPersonViewHolder> {

    private List<EventPlus> eventsByPerson;

    public PersonEventsRecyclerViewAdapter(List<EventPlus> eventsByPerson) {
        this.eventsByPerson = eventsByPerson;
    }

    @NonNull
    @Override
    public EventsByPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentPersonTabEventsRowsBinding fragmentPersonTabEventsRowsBinding = FragmentPersonTabEventsRowsBinding.inflate(layoutInflater, parent,false);
        return new EventsByPersonViewHolder(fragmentPersonTabEventsRowsBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsByPersonViewHolder holder, int position) {
        EventPlus eventPlus = eventsByPerson.get(position);
        holder.fragmentPersonTabEventsRowsBinding.setEventsByPerson(eventPlus);
        holder.fragmentPersonTabEventsRowsBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return eventsByPerson.size();
    }

    public static class EventsByPersonViewHolder extends RecyclerView.ViewHolder {

        FragmentPersonTabEventsRowsBinding fragmentPersonTabEventsRowsBinding;
        public EventsByPersonViewHolder(@NonNull FragmentPersonTabEventsRowsBinding fragmentPersonTabEventsRowsBinding) {
            super(fragmentPersonTabEventsRowsBinding.getRoot());
            this.fragmentPersonTabEventsRowsBinding = fragmentPersonTabEventsRowsBinding;
        }
    }


}