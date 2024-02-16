package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Entität für die Kategorie-Tabelle in der Room-Datenbank.
 * Repräsentiert eine Kategorie, die für die Klassifizierung von Daten verwendet werden kann.
 */
@Entity(tableName = "category")
public class Category {
    /**
     * Primärschlüssel der Kategorie. Eindeutiger Bezeichner für jede Kategorie.
     */
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "name")
    public String name;

    /**
     * Konstruktor für die Category-Entität.
     *
     * @param name Der Name der Kategorie.
     */
    public Category(@NotNull String name) {
        this.name = name;
    }

    /**
     * Gibt den Namen der Kategorie zurück.
     *
     * @return Der Name der Kategorie.
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen der Kategorie.
     *
     * @param name Der neue Name der Kategorie.
     */
    public void setName(@NotNull String name) {
        this.name = name;
    }
}
