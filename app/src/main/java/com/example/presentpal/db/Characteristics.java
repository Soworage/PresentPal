package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "characteristic")
public class Characteristics {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NotNull
    @ColumnInfo(name = "mainCategory")
    public String mainCategory;

    @NotNull
    @ColumnInfo(name = "subcategory")
    public String subcategory;

    @NotNull
    @ColumnInfo(name = "description")
    public String description;


}
