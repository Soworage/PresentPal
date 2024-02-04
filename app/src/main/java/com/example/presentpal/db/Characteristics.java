package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName="characteristics")
public class Characteristics {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    public int id;

    @NotNull
    @ColumnInfo(name ="mainCategory")
    public String mainCategory;

    @NotNull
    @ColumnInfo(name ="subCategory")
    public String subCategory;

    @NotNull
    @ColumnInfo(name ="description")
    public String description;
}
