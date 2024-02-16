package com.example.presentpal.db;

import androidx.room.Embedded;

/**
 * Diese Klasse repräsentiert eine Verknüpfung (Join) zwischen den Entitäten Event und Person.
 * Sie ermöglicht den direkten Zugriff auf die Daten beider Entitäten.
 */
public class EventJoinPerson {

    @Embedded
    public Event event;

    @Embedded
    public Person person;

    /**
     * Konstruktor für EventJoinPerson.
     *
     * @param event  Das Event-Objekt, das Teil der Verknüpfung ist.
     * @param person Das Person-Objekt, das Teil der Verknüpfung ist.
     */
    public EventJoinPerson(Event event, Person person) {
        this.event = event;
        this.person = person;
    }
}