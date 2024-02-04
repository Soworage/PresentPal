package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "person",
        foreignKeys = @ForeignKey(entity = Relationship.class,
                parentColumns = "relationshipName",
                childColumns = "relationshipName",
                onDelete = ForeignKey.SET_NULL),
        indices = {@Index(value = "relationshipName")})
public class Person {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    public int id;

    @NotNull
    @ColumnInfo(name ="firstname")
    public String firstname;

    @NotNull
    @ColumnInfo(name ="lastname")
    public String lastname;

    @ColumnInfo(name ="nickname")
    public String nickname;

    @ColumnInfo(name ="user")
    public boolean user;

    @ColumnInfo(name ="relationshipName")
    public String relationshipName; // Verweis auf den Primärschlüssel in Relationship

    public Person(int id, @NotNull String firstname, @NotNull String lastname, boolean user) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NotNull
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NotNull String firstname) {
        this.firstname = firstname;
    }

    @NotNull
    public String getLastname() {
        return lastname;
    }

    public void setLastname(@NotNull String lastname) {
        this.lastname = lastname;
    }

    @NotNull
    public String getNickname() {
        return nickname;
    }

    public void setNickname(@NotNull String nickname) {
        this.nickname = nickname;
    }

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public String getRelationshipName() {
        return relationshipName;
    }

    public void setRelationshipName(String relationshipName) {
        this.relationshipName = relationshipName;
    }
}
