package com.example.presentpal.db;

import androidx.room.Embedded;

import java.io.Serializable;

/**
 * Diese Klasse erweitert die Event-Entität um zusätzliche Informationen,
 * die nicht direkt in der Event-Tabelle gespeichert sind, wie zum Beispiel
 * die Anzahl der Ideen und Geschenke, die mit einem Ereignis verbunden sind.
 */
public class EventPlus implements Serializable {

    @Embedded
    Event event;

    int ideas;
    int presents;

    /**
     * Gibt das Event-Objekt zurück.
     *
     * @return Das assoziierte Event-Objekt.
     */
    public Event getEvent() {
        return event;
    }

    /**
     * Setzt das Event-Objekt.
     *
     * @param event Das zu setzende Event-Objekt.
     */
    public void setEvent(Event event) {
        this.event = event;
    }

    /**
     * Gibt die Anzahl der Ideen zurück, die mit dem Event verbunden sind.
     *
     * @return Die Anzahl der Ideen.
     */
    public int getIdeas() {
        return ideas;
    }

    /**
     * Setzt die Anzahl der Ideen, die mit dem Event verbunden sind.
     *
     * @param ideas Die zu setzende Anzahl der Ideen.
     */
    public void setIdeas(int ideas) {
        this.ideas = ideas;
    }

    /**
     * Gibt die Anzahl der Geschenke zurück, die mit dem Event verbunden sind.
     *
     * @return Die Anzahl der Geschenke.
     */
    public int getPresents() {
        return presents;
    }

    /**
     * Setzt die Anzahl der Geschenke, die mit dem Event verbunden sind.
     *
     * @param presents Die zu setzende Anzahl der Geschenke.
     */
    public void setPresents(int presents) {
        this.presents = presents;
    }
}
