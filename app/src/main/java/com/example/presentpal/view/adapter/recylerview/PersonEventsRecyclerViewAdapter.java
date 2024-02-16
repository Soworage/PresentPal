package com.example.presentpal.view.adapter.recylerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presentpal.databinding.FragmentPersonTabEventsRowsBinding;
import com.example.presentpal.db.EventPlus;
import com.example.presentpal.view.EventActivity;


import java.util.List;

/**
 * Ein RecyclerView-Adapter für die Anzeige von EventPlus-Objekten, die einer bestimmten Person zugeordnet sind.
 */
public class PersonEventsRecyclerViewAdapter extends RecyclerView.Adapter<PersonEventsRecyclerViewAdapter.EventsByPersonViewHolder> {

    private List<EventPlus> eventsByPerson;

    /**
     * Konstruktor für den PersonEventsRecyclerViewAdapter.
     *
     * @param eventsByPerson Eine Liste von EventPlus-Objekten, die einer bestimmten Person zugeordnet sind.
     */
    public PersonEventsRecyclerViewAdapter(List<EventPlus> eventsByPerson) {
        this.eventsByPerson = eventsByPerson;
    }

    /**
     * Erstellt einen neuen ViewHolder, der an den RecyclerView gebunden werden kann.
     *
     * @param parent   Die Elternansicht, in der der ViewHolder platziert wird.
     * @param viewType Der Typ des angeforderten Ansichts-Typs.
     * @return Ein neuer {@link EventsByPersonViewHolder} für den RecyclerView.
     */
    @NonNull
    @Override
    public EventsByPersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentPersonTabEventsRowsBinding fragmentPersonTabEventsRowsBinding = FragmentPersonTabEventsRowsBinding.inflate(layoutInflater, parent, false);
        return new EventsByPersonViewHolder(fragmentPersonTabEventsRowsBinding);
    }

    /**
     * Füllt die Daten in den ViewHolder-Elementen an einer bestimmten Position aus.
     *
     * @param holder   Der ViewHolder, der die Daten anzeigen soll.
     * @param position Die Position des Elements in der RecyclerView.
     */
    @Override
    public void onBindViewHolder(@NonNull EventsByPersonViewHolder holder, int position) {
        EventPlus eventPlus = eventsByPerson.get(position);
        holder.fragmentPersonTabEventsRowsBinding.setEventsByPerson(eventPlus);
        holder.fragmentPersonTabEventsRowsBinding.executePendingBindings();

        holder.fragmentPersonTabEventsRowsBinding.personEventRowCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), EventActivity.class);
                intent.putExtra("event", eventPlus.getEvent());
                v.getContext().startActivity(intent);
            }
        });
    }

    /**
     * Gibt die Anzahl der EventPlus-Objekte in der Liste zurück.
     *
     * @return Die Anzahl der EventPlus-Objekte.
     */
    @Override
    public int getItemCount() {
        return eventsByPerson.size();
    }

    /**
     * Ein ViewHolder für EventPlus-Objekte, die einer bestimmten Person zugeordnet sind.
     */
    public static class EventsByPersonViewHolder extends RecyclerView.ViewHolder {

        FragmentPersonTabEventsRowsBinding fragmentPersonTabEventsRowsBinding;

        /**
         * Konstruktor für den EventsByPersonViewHolder.
         *
         * @param fragmentPersonTabEventsRowsBinding Das Binding-Objekt für die Ansicht.
         */
        public EventsByPersonViewHolder(@NonNull FragmentPersonTabEventsRowsBinding fragmentPersonTabEventsRowsBinding) {
            super(fragmentPersonTabEventsRowsBinding.getRoot());
            this.fragmentPersonTabEventsRowsBinding = fragmentPersonTabEventsRowsBinding;
        }
    }


}