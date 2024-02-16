package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

/**
 * Entität, die eine Charakteristik in der Datenbank repräsentiert.
 * Diese Klasse wird verwendet, um Charakteristiken mit Haupt- und Unterkategorien sowie einer Beschreibung zu speichern.
 */
@Entity(tableName="characteristics")
public class Characteristics {

    /**
     * Die eindeutige ID der Charakteristik. Wird automatisch generiert.
     */
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    public int id;

    /**
     * Die Hauptkategorie, zu der die Charakteristik gehört.
     */
    @NotNull
    @ColumnInfo(name ="mainCategory")
    public String mainCategory;

    /**
     * Die Unterkategorie der Charakteristik innerhalb der Hauptkategorie.
     */
    @NotNull
    @ColumnInfo(name ="subCategory")
    public String subCategory;

    /**
     * Eine Beschreibung oder Details zur Charakteristik.
     */
    @NotNull
    @ColumnInfo(name ="description")
    public String description;

    /**
     * Konstruktor für eine Charakteristik.
     *
     * @param id Die ID der Charakteristik (wird automatisch generiert).
     * @param mainCategory Die Hauptkategorie der Charakteristik.
     * @param subCategory Die Unterkategorie der Charakteristik.
     * @param description Die Beschreibung der Charakteristik.
     */
    public Characteristics(int id, @NotNull String mainCategory, @NotNull String subCategory, @NotNull String description) {
        this.id = id;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.description = description;
    }

    /**
     * Gibt die ID der Charakteristik zurück.
     *
     * @return Die ID der Charakteristik.
     */
    public int getId() {
        return id;
    }

    /**
     * Setzt die ID der Charakteristik.
     *
     * @param id Die neue ID der Charakteristik.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gibt die Beschreibung der Charakteristik zurück.
     *
     * @return Die Beschreibung der Charakteristik.
     */
    @NotNull
    public String getDescription() {
        return description;
    }

    /**
     * Setzt die Beschreibung der Charakteristik.
     *
     * @param description Die neue Beschreibung der Charakteristik.
     */
    public void setDescription(@NotNull String description) {
        this.description = description;
    }
}