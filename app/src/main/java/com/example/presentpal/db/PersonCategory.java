package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "personCategory",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Category.class,
                        parentColumns = "name",
                        childColumns = "categoryId",
                        onDelete = ForeignKey.CASCADE)
        },
        primaryKeys = {"personId", "categoryId"}
)
public class PersonCategory {

    @ColumnInfo(name = "personId")
    public int personId;

    @ColumnInfo(name = "categoryId")
    public String categoryId;
}
