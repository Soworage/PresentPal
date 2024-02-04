package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import org.jetbrains.annotations.NotNull;

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
        primaryKeys = {"personId", "categoryId"},
        indices = {@Index(value = "categoryId")}
)
public class PersonCategory {

    @NotNull
    @ColumnInfo(name = "personId")
    public int personId;

    @NotNull
    @ColumnInfo(name = "categoryId")
    public String categoryId;

    public PersonCategory(int personId, @NotNull String categoryId) {
        this.personId = personId;
        this.categoryId = categoryId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    @NotNull
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(@NotNull String categoryId) {
        this.categoryId = categoryId;
    }
}
