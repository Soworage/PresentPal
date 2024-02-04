package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;


public class Relationship {

    @PrimaryKey
    @ColumnInfo(name = "relationshipName")
    public String relationshipName;

}


