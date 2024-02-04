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

    public Characteristics(int id, @NotNull String mainCategory, @NotNull String subCategory, @NotNull String description) {
        this.id = id;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(@NotNull String mainCategory) {
        this.mainCategory = mainCategory;
    }

    @NotNull
    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(@NotNull String subCategory) {
        this.subCategory = subCategory;
    }

    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NotNull String description) {
        this.description = description;
    }
}
