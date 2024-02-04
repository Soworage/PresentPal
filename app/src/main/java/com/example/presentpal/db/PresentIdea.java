
package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
@Entity(tableName = "presentIdea",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Event.class,
                        parentColumns = "id",
                        childColumns = "eventId",
                        onDelete = ForeignKey.CASCADE)
        })
public class PresentIdea {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "personId")
    public int personId; // Verbindung zu Person

    @ColumnInfo(name = "eventId")
    public int eventId; // Verbindung zu Event

    @NotNull
    public String titel;

    @NotNull
    public String description;

    @NotNull
    public String whereToBuy;

    public int price;


}
