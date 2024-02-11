package com.example.presentpal.db;

import androidx.room.Embedded;

import java.io.Serializable;

public class PresentIdeaJoinPerson implements Serializable {

    @Embedded
    public PresentIdea presentIdea;

    @Embedded
    public Person person;

    public PresentIdea getPresentIdea() {
        return presentIdea;
    }

    public void setPresentIdea(PresentIdea presentIdea) {
        this.presentIdea = presentIdea;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


}
