package com.example.presentpal.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "login")
public class LogIn {

    @NotNull
    @PrimaryKey
    @ColumnInfo(name = "password" )
    public String password;

    public LogIn(@NonNull String password) {
        this.password = password;
    }


    public void setPassword(@NonNull String password) {
        this.password = password;
    }
}
