package com.example.presentpal.db;

import androidx.room.Embedded;

public class PresentIdeaJoinPerson {

    @Embedded
    public PresentIdea presentIdea;

    @Embedded
    public Person person;
}
