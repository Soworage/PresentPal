package com.example.presentpal.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Entity-Klasse für die Speicherung von Login-Informationen in der Datenbank.
 * Diese Klasse definiert die Struktur der "login"-Tabelle, die zur Speicherung von Passwörtern verwendet wird.
 */
@Entity(tableName = "login")
public class LogIn {

    /**
     * Das Passwort des Benutzers. Es dient als Primärschlüssel der Tabelle.
     */
    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "password")
    public String password;

    /**
     * Konstruktor für die LogIn-Entität.
     *
     * @param password Das Passwort, das für den Login verwendet wird.
     */
    public LogIn(@NonNull String password) {
        this.password = password;
    }

    /**
     * Setzt das Passwort für den Login.
     *
     * @param password Das neue Passwort.
     */
    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
