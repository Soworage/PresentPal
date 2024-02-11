package com.example.presentpal.db;

import androidx.room.Embedded;

import java.io.Serializable;

public class EventPlus implements Serializable {

    @Embedded
    Event event;

    int ideas;
    int presents;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getIdeas() {
        return ideas;
    }

    public void setIdeas(int ideas) {
        this.ideas = ideas;
    }

    public int getPresents() {
        return presents;
    }

    public void setPresents(int presents) {
        this.presents = presents;
    }
}
