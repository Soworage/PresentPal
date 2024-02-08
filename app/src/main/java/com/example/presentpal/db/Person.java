package com.example.presentpal.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "person",
        foreignKeys = @ForeignKey(entity = Relationship.class,
                parentColumns = "relationshipName",
                childColumns = "relationshipName",
                onDelete = ForeignKey.SET_NULL),
        indices = {@Index(value = "relationshipName")})
public class Person implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    public int id;

    @ColumnInfo(name ="firstname")
    public String firstname;

    @ColumnInfo(name ="lastname")
    public String lastname;

    @NotNull
    @ColumnInfo(name ="nickname")
    public String nickname;

    @ColumnInfo(name ="user")
    public boolean user;

    @ColumnInfo(name ="relationshipName")
    public String relationshipName; // Verweis auf den Primärschlüssel in Relationship

    public Person(String firstname, String lastname,@NotNull String nickname, boolean user) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
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

    @NonNull
    @Override
    public String toString() {
        return this.nickname;
    }
}
