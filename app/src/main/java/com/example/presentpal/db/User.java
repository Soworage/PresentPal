package com.example.presentpal.db;

import androidx.room.Embedded;

import com.example.presentpal.view.LoginScreenActivity;

public class User {

    @Embedded
    public LogIn logIn;
    @Embedded
    public Person person;

}
