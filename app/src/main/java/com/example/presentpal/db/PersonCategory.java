package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "personcategory",
    foreignKeys = {
        @ForeignKey(entity = Person.class,
            parentColumns = "id",
            childColumns = "personId",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        @ForeignKey(entity = Category.class,
            parentColumns = "id",
            childColumns = "categoryId",
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    })
public class PersonCategory {

    @PrimaryKey
    @ColumnInfo(name ="personId")
    public int personId;

    @PrimaryKey
    @ColumnInfo(name ="categoryId")
    public int categoryId;
}
