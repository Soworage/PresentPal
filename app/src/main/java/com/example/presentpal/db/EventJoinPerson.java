package com.example.presentpal.db;

import androidx.room.Embedded;

public class EventJoinPerson {

    @Embedded
   public Event event;
    @Embedded
    public Person person;

}
