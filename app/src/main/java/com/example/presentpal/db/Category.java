package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "category")
public class Category {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "name")
    public String name;


    public Category(@NotNull String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }
}
