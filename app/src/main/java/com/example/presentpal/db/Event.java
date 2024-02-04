package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "event",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.SET_NULL,
                        onUpdate = ForeignKey.CASCADE)
        })
public class Event {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name ="id")
        public int id;
        @PrimaryKey
        @ColumnInfo(name ="personId")
        public int personId;
        @ColumnInfo(name ="title")
        public String title;
        @NotNull
        @ColumnInfo(name ="date")
        public String date;
        @ColumnInfo(name ="description")
        public String description;
}
