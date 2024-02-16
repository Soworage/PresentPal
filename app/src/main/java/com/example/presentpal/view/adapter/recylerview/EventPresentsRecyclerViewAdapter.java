package com.example.presentpal.view.adapter.recylerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presentpal.databinding.FragmentEventTabIdeasRowBinding;

import com.example.presentpal.db.Event;
import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.view.PresentIdeaActivity;

import java.util.List;
/**
 * Ein RecyclerView-Adapter für die Anzeige von PresentIdeaJoinPerson-Objekten in einem Event.
 */
public class EventPresentsRecyclerViewAdapter extends RecyclerView.Adapter<EventPresentsRecyclerViewAdapter.PresentIdeasViewHolder> {

    private List<PresentIdeaJoinPerson> presentIdeas;


    private Event event;
    /**
     * Konstruktor für den EventPresentsRecyclerViewAdapter.
     *
     * @param presentIdeas Eine Liste von PresentIdeaJoinPerson-Objekten.
     * @param event        Das Event, zu dem die PresentIdeaJoinPerson-Objekte gehören.
     */
    public EventPresentsRecyclerViewAdapter(List<PresentIdeaJoinPerson> presentIdeas, Event event) {
        this.presentIdeas = presentIdeas;
        this.event = event;
    }
    /**
     * Erstellt einen neuen ViewHolder, der an den RecyclerView gebunden werden kann.
     *
     * @param parent   Die Elternansicht, in der der ViewHolder platziert wird.
     * @param viewType Der Typ des angeforderten Ansichts-Typs.
     * @return Ein neuer {@link PresentIdeasViewHolder} für den RecyclerView.
     */
    @NonNull
    @Override
    public PresentIdeasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentEventTabIdeasRowBinding fragmentEventTabIdeasRowBinding = FragmentEventTabIdeasRowBinding.inflate(layoutInflater, parent, false);
        return new PresentIdeasViewHolder(fragmentEventTabIdeasRowBinding);
    }
    /**
     * Füllt die Daten in den ViewHolder-Elementen an einer bestimmten Position aus.
     *
     * @param holder   Der ViewHolder, der die Daten anzeigen soll.
     * @param position Die Position des Elements in der RecyclerView.
     */
    @Override
    public void onBindViewHolder(@NonNull PresentIdeasViewHolder holder, int position) {

        if (presentIdeas != null) {
            PresentIdeaJoinPerson presentIdeaJoinPerson = presentIdeas.get(position);

            holder.fragmentEventTabIdeasRowBinding.setPresentIdeaJoinPerson(presentIdeaJoinPerson);
            holder.fragmentEventTabIdeasRowBinding.executePendingBindings();

            holder.fragmentEventTabIdeasRowBinding.eventIdeasRowCardView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PresentIdeaActivity.class);
                    intent.putExtra("presentIdea", presentIdeaJoinPerson.getPresentIdea());
                    intent.putExtra("event", event);
                    v.getContext().startActivity(intent);
                }
            });

        }
    }
    /**
     * Gibt die Anzahl der PresentIdeaJoinPerson-Objekte in der Liste zurück.
     *
     * @return Die Anzahl der PresentIdeaJoinPerson-Objekte.
     */
    @Override
    public int getItemCount() {
        return presentIdeas.size();
    }
    /**
     * Ein ViewHolder für PresentIdeaJoinPerson-Objekte.
     */
    public static class PresentIdeasViewHolder extends RecyclerView.ViewHolder {

        FragmentEventTabIdeasRowBinding fragmentEventTabIdeasRowBinding;
        /**
         * Konstruktor für den PresentIdeasViewHolder.
         *
         * @param fragmentEventTabIdeasRowBinding Das Binding-Objekt für die Ansicht.
         */
        public PresentIdeasViewHolder(@NonNull FragmentEventTabIdeasRowBinding fragmentEventTabIdeasRowBinding) {
            super(fragmentEventTabIdeasRowBinding.getRoot());
            this.fragmentEventTabIdeasRowBinding = fragmentEventTabIdeasRowBinding;
        }
    }
    /**
     * Gibt das Event zurück, zu dem die PresentIdeaJoinPerson-Objekte gehören.
     *
     * @return Das Event-Objekt.
     */
    public Event getEvent() {
        return event;
    }

}


