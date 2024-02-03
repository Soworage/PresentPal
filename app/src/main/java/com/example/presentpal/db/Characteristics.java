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
    @ColumnInfo(name ="maincategory")
    public String maincategory;

    @NotNull
    @ColumnInfo(name ="subcategory")
    public String subcategory;

    @NotNull
    @ColumnInfo(name ="description")
    public String description;

}
