package com.example.presentpal.view.adapter.recylerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.presentpal.databinding.FragmentEventTabIdeasRowBinding;

import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.view.PresentIdeaActivity;

import java.util.List;

public class EventPresentsRecyclerViewAdapter extends RecyclerView.Adapter<EventPresentsRecyclerViewAdapter.PresentIdeasViewHolder> {

    private List<PresentIdeaJoinPerson> presentIdeas;

    public EventPresentsRecyclerViewAdapter(List<PresentIdeaJoinPerson> presentIdeas) {
        this.presentIdeas = presentIdeas;
    }

    @NonNull
    @Override
    public PresentIdeasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        FragmentEventTabIdeasRowBinding fragmentEventTabIdeasRowBinding = FragmentEventTabIdeasRowBinding.inflate(layoutInflater, parent, false);
        return new PresentIdeasViewHolder(fragmentEventTabIdeasRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PresentIdeasViewHolder holder, int position) {
        PresentIdeaJoinPerson presentIdeaJoinPerson = presentIdeas.get(position);
        holder.fragmentEventTabIdeasRowBinding.setPresentIdeaJoinPerson(presentIdeaJoinPerson);
        holder.fragmentEventTabIdeasRowBinding.executePendingBindings();

        holder.fragmentEventTabIdeasRowBinding.eventIdeasRowCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PresentIdeaActivity.class);
                intent.putExtra("presentIdea", presentIdeaJoinPerson.getPresentIdea());
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return presentIdeas.size();
    }

    public static class PresentIdeasViewHolder extends RecyclerView.ViewHolder {

        FragmentEventTabIdeasRowBinding fragmentEventTabIdeasRowBinding;

        public PresentIdeasViewHolder(@NonNull FragmentEventTabIdeasRowBinding fragmentEventTabIdeasRowBinding) {
            super(fragmentEventTabIdeasRowBinding.getRoot());
            this.fragmentEventTabIdeasRowBinding = fragmentEventTabIdeasRowBinding;
        }
    }
}
