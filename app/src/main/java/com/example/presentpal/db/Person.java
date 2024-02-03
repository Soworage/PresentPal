package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "person",
foreignKeys = {
        @ForeignKey(entity = Relationship.class,
            parentColumns = "desricption",
            childColumns = "relationship",
            onDelete = ForeignKey.SET_NULL,
            onUpdate = ForeignKey.CASCADE)
})
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    public int id;

    @NotNull
    @ColumnInfo(name ="firstname")
    public String firstname;
    @NotNull
    @ColumnInfo(name ="lastname")
    public String lastname;
    @NotNull
    @ColumnInfo(name ="nickname")
    public String nickname;

    @ColumnInfo(name ="user")
    public boolean user;

    @ColumnInfo(name ="relationship")
    public String relationship;
}
