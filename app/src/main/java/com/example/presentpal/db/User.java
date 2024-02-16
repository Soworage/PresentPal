package com.example.presentpal.db;

import androidx.room.Embedded;

import com.example.presentpal.view.LoginScreenActivity;
/**
 * Repräsentiert einen Benutzer durch Zusammenführen von Login-Informationen und persönlichen Daten.
 */
public class User {

    @Embedded
    public LogIn logIn;
    @Embedded
    public Person person;

}
