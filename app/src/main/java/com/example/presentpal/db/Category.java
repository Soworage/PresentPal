package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "category")
public class Category {

    @PrimaryKey
    @ColumnInfo(name ="name")
    public String name;
}
