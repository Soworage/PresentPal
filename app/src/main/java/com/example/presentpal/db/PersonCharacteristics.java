package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
/**
 * Entität, die die Beziehung zwischen Personen und deren Charakteristiken darstellt.
 * Diese Klasse bildet eine Verknüpfungstabelle ab, um eine Many-to-Many-Beziehung
 * zwischen Personen und Charakteristiken zu ermöglichen. Jede Instanz repräsentiert
 * eine Verbindung zwischen einem Person-Objekt und einem Charakteristik-Objekt.
 */
@Entity(tableName = "personCharacteristics",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Characteristics.class,
                        parentColumns = "id",
                        childColumns = "characteristicsId",
                        onDelete = ForeignKey.CASCADE)
        },
        primaryKeys = {"personId", "characteristicsId"} // Zusammengesetzter Primärschlüssel
)
public class PersonCharacteristics {

    @ColumnInfo(name = "personId")
    public int personId;




}
