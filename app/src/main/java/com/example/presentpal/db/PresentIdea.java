package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "presentidea",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Event.class,
                        parentColumns = "id",
                        childColumns = "eventId",
                        onDelete = ForeignKey.SET_NULL,
                        onUpdate = ForeignKey.CASCADE
                )
        })
public class PresentIdea {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name ="id")
        public int id;
        @PrimaryKey
        @ColumnInfo(name ="personId")
        public int personId;

        @NotNull
        @ColumnInfo(name ="title")
        public String title;

        @ColumnInfo(name ="description")
        public String description;

        @ColumnInfo(name ="price")
        public float price;

        @ColumnInfo(name ="availableAt")
        public String availableAt;

}
