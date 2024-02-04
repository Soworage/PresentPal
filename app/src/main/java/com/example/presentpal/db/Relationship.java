package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "relationship")
public class Relationship {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "relationshipName")
    public String relationshipName;

    public Relationship(@NotNull String relationshipName) {
        this.relationshipName = relationshipName;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(@NotNull String relationshipName) {
        this.relationshipName = relationshipName;
    }
}
