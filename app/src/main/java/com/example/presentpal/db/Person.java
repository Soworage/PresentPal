package com.example.presentpal.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Entity-Klasse, die eine Person darstellt, welche in der lokalen Datenbank gespeichert wird.
 * Personen können in Beziehung zu anderen Entitäten stehen, wie z.B. Ereignissen.
 */
@Entity(tableName = "person",
        foreignKeys = @ForeignKey(entity = Relationship.class,
                parentColumns = "relationshipName",
                childColumns = "relationshipName",
                onDelete = ForeignKey.SET_NULL),
        indices = {@Index(value = "relationshipName")})
public class Person implements Serializable {

    /**
     * Eindeutige ID der Person. Diese ID wird automatisch generiert.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    /**
     * Der Vorname der Person.
     */
    @ColumnInfo(name = "firstname")
    public String firstname;

    /**
     * Der Nachname der Person.
     */
    @ColumnInfo(name = "lastname")
    public String lastname;

    /**
     * Der Spitzname der Person. Dieses Feld darf nicht null sein.
     */
    @NotNull
    @ColumnInfo(name = "nickname")
    public String nickname;

    /**
     * Kennzeichnet, ob diese Person der Benutzer selbst ist.
     */
    @ColumnInfo(name = "user")
    public boolean user;


    /**
     * Der Name der Beziehung dieser Person zu anderen Personen.
     * Dies ist ein Verweis auf den Primärschlüssel in der Relationship-Tabelle.
     */
    @ColumnInfo(name = "relationshipName")
    public String relationshipName; // Verweis auf den Primärschlüssel in Relationship

    /**
     * Konstruktor für die Person-Entität.
     *
     * @param firstname Der Vorname der Person.
     * @param lastname  Der Nachname der Person.
     * @param nickname  Der Spitzname der Person. Muss angegeben werden.
     * @param user      Gibt an, ob die Person der Benutzer selbst ist.
     */
    public Person(String firstname, String lastname, @NotNull String nickname, boolean user) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.user = user;
    }

    /**
     * Gibt die ID der Person zurück.
     *
     * @return Die eindeutige ID der Person.
     */
    public int getId() {
        return id;
    }

    /**
     * Gibt den Vornamen der Person zurück.
     *
     * @return Der Vorname der Person.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setzt den Vornamen der Person.
     *
     * @param firstname Der neue Vorname der Person.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gibt den Nachnamen der Person zurück.
     *
     * @return Der Nachname der Person.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setzt den Nachnamen der Person.
     *
     * @param lastname Der neue Nachname der Person.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gibt den Spitznamen der Person zurück.
     *
     * @return Der Spitzname der Person. Dieses Feld ist nie null.
     */
    @NotNull
    public String getNickname() {
        return nickname;
    }

    /**
     * Setzt den Spitznamen der Person.
     *
     * @param nickname Der neue Spitzname der Person. Muss angegeben werden und darf nicht null sein.
     */
    public void setNickname(@NotNull String nickname) {
        this.nickname = nickname;
    }

    /**
     * Überprüft, ob diese Person der Benutzer selbst ist.
     *
     * @return {@code true}, wenn die Person der Benutzer selbst ist, sonst {@code false}.
     */
    public boolean isUser() {
        return user;
    }

    /**
     * Legt fest, ob diese Person der Benutzer selbst ist.
     *
     * @param user {@code true}, wenn die Person der Benutzer selbst sein soll, sonst {@code false}.
     */
    public void setUser(boolean user) {
        this.user = user;
    }


    /**
     * Gibt eine String-Repräsentation der Person zurück, in der Regel für Debugging-Zwecke.
     *
     * @return Eine String-Repräsentation der Person, die den Spitznamen enthält.
     */
    @NonNull
    @Override
    public String toString() {
        return this.nickname;
    }
}
