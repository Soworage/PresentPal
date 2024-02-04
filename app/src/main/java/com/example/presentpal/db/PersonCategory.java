package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "personCategory",
        primaryKeys = {"personId", "categoryName"},
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Category.class,
                        parentColumns = "name",
                        childColumns = "categoryName",
                        onDelete = ForeignKey.CASCADE)
        })
public class PersonCategory {
    @NotNull
    @ColumnInfo(name = "personId")
    public int personId;

    @NotNull
    @ColumnInfo(name = "categoryName")
    public String categoryName;

   }
