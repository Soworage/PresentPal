package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import org.jetbrains.annotations.NotNull;
/**
 * Entitätstabelle für die Zuordnung zwischen Personen und Kategorien.
 * Diese Klasse stellt eine Verknüpfungstabelle dar, die eine Many-to-Many-Beziehung zwischen Personen und Kategorien ermöglicht.
 * Jede Instanz repräsentiert eine Zuordnung eines Person-Objekts zu einem Kategorie-Objekt.
 */
@Entity(tableName = "personCategory",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Category.class,
                        parentColumns = "name",
                        childColumns = "categoryId",
                        onDelete = ForeignKey.CASCADE)
        },
        primaryKeys = {"personId", "categoryId"},
        indices = {@Index(value = "categoryId")}
)
public class PersonCategory {

    @NotNull
    @ColumnInfo(name = "personId")
    public int personId;

    @NotNull
    @ColumnInfo(name = "categoryId")
    public String categoryId;
    /**
     * Konstruktor für PersonCategory.
     *
     * @param personId Die ID der Person, die der Kategorie zugeordnet ist.
     * @param categoryId Die ID der Kategorie, der die Person zugeordnet ist.
     */
    public PersonCategory(int personId, @NotNull String categoryId) {
        this.personId = personId;
        this.categoryId = categoryId;
    }
    /**
     * Gibt die ID der Person zurück.
     *
     * @return Die ID der Person.
     */
    public int getPersonId() {
        return personId;
    }
    /**
     * Setzt die ID der Person.
     *
     * @param personId Die neue ID der Person.
     */
    public void setPersonId(int personId) {
        this.personId = personId;
    }


}
