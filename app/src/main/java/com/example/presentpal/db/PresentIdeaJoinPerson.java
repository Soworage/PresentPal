package com.example.presentpal.db;

import androidx.room.Embedded;
import java.io.Serializable;

/**
 * Diese Klasse repräsentiert die Verknüpfung zwischen einer Geschenkidee und einer Person.
 * Sie ermöglicht den Zugriff auf Informationen sowohl der Geschenkidee als auch der Person
 * innerhalb einer einzigen Entität. Dies ist besonders nützlich für Datenabfragen, die Informationen
 * aus beiden Entitäten kombinieren.
 */
public class PresentIdeaJoinPerson implements Serializable {

    @Embedded
    public PresentIdea presentIdea;

    @Embedded
    public Person person;

    /**
     * Gibt die Geschenkidee zurück, die mit der Person verknüpft ist.
     * @return Die verknüpfte Geschenkidee.
     */
    public PresentIdea getPresentIdea() {
        return presentIdea;
    }

    /**
     * Setzt eine neue Geschenkidee für die Verknüpfung.
     * @param presentIdea Die zu setzende Geschenkidee.
     */
    public void setPresentIdea(PresentIdea presentIdea) {
        this.presentIdea = presentIdea;
    }

    /**
     * Gibt die Person zurück, die mit der Geschenkidee verknüpft ist.
     * @return Die verknüpfte Person.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Setzt eine neue Person für die Verknüpfung.
     * @param person Die zu setzende Person.
     */
    public void setPerson(Person person) {
        this.person = person;
    }
}
