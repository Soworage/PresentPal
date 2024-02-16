package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import org.jetbrains.annotations.NotNull;

/**
 * Entität, die eine Beziehung zwischen Personen beschreibt.
 * Der eindeutige Schlüssel für jede Beziehung ist der Name der Beziehung.
 */
@Entity(tableName = "relationship")
public class Relationship {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "relationshipName")
    public String relationshipName;

    /**
     * Konstruktor für die Relationship-Entität.
     * @param relationshipName Der Name der Beziehung.
     */
    public Relationship(@NotNull String relationshipName) {
        this.relationshipName = relationshipName;
    }

    /**
     * Gibt den Namen der Beziehung zurück.
     * @return Der Name der Beziehung.
     */
    public String getRelationshipName() {
        return relationshipName;
    }

    /**
     * Setzt den Namen der Beziehung.
     * @param relationshipName Der neue Name der Beziehung.
     */
    public void setRelationshipName(@NotNull String relationshipName) {
        this.relationshipName = relationshipName;
    }
}
