package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "person",
        foreignKeys = @ForeignKey(entity = Relationship.class,
                parentColumns = "relationshipName",
                childColumns = "relationshipName",
                onDelete = ForeignKey.SET_NULL),
        indices = {@Index(value = "relationshipName")})
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

    @ColumnInfo(name ="relationshipName")
    public String relationshipName; // Verweis auf den Primärschlüssel in Relationship
}
