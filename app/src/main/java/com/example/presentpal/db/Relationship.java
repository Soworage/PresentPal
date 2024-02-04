package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "relationship")
public class Relationship {

    @PrimaryKey
    @ColumnInfo(name = "relationship")
    public String despription;
}
