package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
/**
 * Entität, die eine Geschenkidee repräsentiert. Jede Instanz dieser Klasse ist eine Idee für ein Geschenk,
 * das einer Person zu einem bestimmten Ereignis gegeben werden kann.
 */
@Entity(tableName = "presentIdea",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Event.class,
                        parentColumns = "eid",
                        childColumns = "eventId",
                        onDelete = ForeignKey.SET_NULL)
        },
        indices = {@Index(value = "personId"),@Index(value = "eventId")})

public class PresentIdea implements Serializable {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "piid")
        public int piid;

        @ColumnInfo(name = "personId")
        public int personId;

        @ColumnInfo(name = "eventId")
        public Integer eventId;

        @NotNull
        @ColumnInfo(name = "title")
        public String title;

        @ColumnInfo(name = "description")
        public String description;

        @ColumnInfo(name = "shortDescription")
        public String shortDescription;

        @ColumnInfo(name = "price")
        public float price;

        @ColumnInfo(name = "availableAt")
        public String availableAt;

        @ColumnInfo(name = "isPresent")
        public boolean isPresent;

        /**
         * Konstruktor für eine Geschenkidee.
         *
         * @param personId Die ID der Person, für die die Geschenkidee vorgesehen ist.
         * @param eventId Die ID des Ereignisses, zu dem die Geschenkidee gehört. Kann null sein.
         * @param title Der Titel der Geschenkidee.
         * @param description Die ausführliche Beschreibung der Geschenkidee.
         * @param shortDescription Eine kurze Beschreibung der Geschenkidee.
         * @param price Der Preis der Geschenkidee.
         * @param availableAt Informationen darüber, wo die Geschenkidee verfügbar ist.
         * @param isPresent Gibt an, ob die Idee als ausgewähltes Geschenk markiert ist.
         */
        public PresentIdea(int personId, Integer eventId, @NotNull String title, String description, String shortDescription, float price, String availableAt, boolean isPresent) {
                this.personId = personId;
                this.eventId = eventId;
                this.title = title;
                this.description = description;
                this.shortDescription = shortDescription;
                this.price = price;
                this.availableAt = availableAt;
                this.isPresent = isPresent;
        }

        /**
         * Gibt die eindeutige ID der Geschenkidee zurück.
         * @return Die eindeutige Identifikationsnummer der Geschenkidee.
         */
        public int getId() {
                return piid;
        }

        /**
         * Setzt die eindeutige ID der Geschenkidee.
         * @param piid Die zu setzende eindeutige Identifikationsnummer der Geschenkidee.
         */
        public void setId(int piid) {
                this.piid = piid;
        }

        /**
         * Gibt die ID der Person zurück, für die die Geschenkidee vorgesehen ist.
         * @return Die ID der zugehörigen Person.
         */
        public int getPersonId() {
                return personId;
        }

        /**
         * Setzt die ID der Person, für die die Geschenkidee vorgesehen ist.
         * @param personId Die zu setzende ID der Person.
         */
        public void setPersonId(int personId) {
                this.personId = personId;
        }

        /**
         * Setzt die ID des Ereignisses, zu dem die Geschenkidee gehört.
         * @param eventId Die zu setzende ID des Ereignisses.
         */
        public void setEventId(Integer eventId) {
                this.eventId = eventId;
        }

        /**
         * Gibt den Titel der Geschenkidee zurück.
         * @return Der Titel der Geschenkidee.
         */
        @NotNull
        public String getTitle() {
                return title;
        }

        /**
         * Setzt den Titel der Geschenkidee.
         * @param title Der zu setzende Titel der Geschenkidee.
         */
        public void setTitle(@NotNull String title) {
                this.title = title;
        }

        /**
         * Gibt die Beschreibung der Geschenkidee zurück.
         * @return Die Beschreibung der Geschenkidee.
         */
        public String getDescription() {
                return description;
        }

        /**
         * Setzt die Beschreibung der Geschenkidee.
         * @param description Die zu setzende Beschreibung der Geschenkidee.
         */
        public void setDescription(String description) {
                this.description = description;
        }

        /**
         * Gibt den Preis der Geschenkidee zurück.
         * @return Der Preis der Geschenkidee.
         */
        public float getPrice() {
                return price;
        }

        /**
         * Setzt den Preis der Geschenkidee.
         * @param price Der zu setzende Preis der Geschenkidee.
         */
        public void setPrice(float price) {
                this.price = price;
        }

        /**
         * Gibt die Kurzbeschreibung der Geschenkidee zurück.
         * @return Die Kurzbeschreibung der Geschenkidee.
         */
        public String getShortDescription() {
                return shortDescription;
        }

        /**
         * Setzt die Kurzbeschreibung der Geschenkidee.
         * @param shortDescription Die zu setzende Kurzbeschreibung der Geschenkidee.
         */
        public void setShortDescription(String shortDescription) {
                this.shortDescription = shortDescription;
        }

        /**
         * Überprüft, ob die Geschenkidee bereits als Geschenk gewählt wurde.
         * @return {@code true}, wenn die Geschenkidee als Geschenk gewählt wurde, sonst {@code false}.
         */
        public boolean isPresent() {
                return isPresent;
        }

        /**
         * Setzt, ob die Geschenkidee als Geschenk gewählt wurde.
         * @param present {@code true}, wenn die Geschenkidee als Geschenk gewählt wurde, sonst {@code false}.
         */
        public void setPresent(boolean present) {
                isPresent = present;
        }
}
