package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import java.util.Date;

@Entity(tableName = "event",
        foreignKeys = @ForeignKey(entity = Person.class,
                parentColumns = "id",
                childColumns = "personId",
                onDelete = ForeignKey.CASCADE))
public class Event {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    public int id;

    @ColumnInfo(name ="personId")
    public int personId; // Fremdschlüssel, der auf die Person-Entität verweist

    @NotNull
    @ColumnInfo(name ="date")
    public Date date;

    @NotNull
    @ColumnInfo(name ="title")
    public String title;

    @NotNull
    @ColumnInfo(name ="description")
    public String description;


}
