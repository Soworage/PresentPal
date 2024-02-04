package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

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

    @ColumnInfo(name = "characteristicsId")
    public int characteristicsId;


}
