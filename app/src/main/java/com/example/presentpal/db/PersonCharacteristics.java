package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "personCharacteristic",
        primaryKeys = {"personId", "characteristicId"},
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Characteristics.class,
                        parentColumns = "id",
                        childColumns = "characteristicId",
                        onDelete = ForeignKey.CASCADE)
        })

public class PersonCharacteristics {
    @NotNull
    @ColumnInfo(name = "personId")
    public int personId;

    @NotNull
    @ColumnInfo(name = "characteristicId")
    public int characteristicId;


}
