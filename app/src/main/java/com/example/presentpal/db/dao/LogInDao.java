package com.example.presentpal.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.presentpal.db.LogIn;
import com.example.presentpal.db.User;

/**
 * Data Access Object (DAO) für den Zugriff auf LogIn-Informationen in der Datenbank.
 */
@Dao
public interface LogInDao {

    /**
     * Fügt ein neues LogIn-Objekt in die Datenbank ein.
     *
     * @param logIn Das einzufügende LogIn-Objekt.
     */
    @Insert
    void insert(LogIn logIn);

    /**
     * Aktualisiert ein bestehendes LogIn-Objekt in der Datenbank.
     *
     * @param logIn Das zu aktualisierende LogIn-Objekt.
     */
    @Update
    void update(LogIn logIn);

    /**
     * Löscht ein LogIn-Objekt aus der Datenbank.
     *
     * @param logIn Das zu löschende LogIn-Objekt.
     */
    @Delete
    void delete(LogIn logIn);

    /**
     * Überprüft, ob ein Passwort in der Datenbank existiert.
     *
     * @param password Das zu überprüfende Passwort.
     * @return Ein LiveData-Objekt mit der Anzahl der Einträge, die das Passwort enthalten.
     */
    @Query("SELECT COUNT(password) FROM login WHERE password = :password")
    LiveData<Integer> checkPassword(String password);

    /**
     * Überprüft, ob ein Passwort in der Datenbank existiert (nicht LiveData).
     *
     * @param password Das zu überprüfende Passwort.
     * @return Die Anzahl der Einträge, die das Passwort enthalten.
     */
    @Query("SELECT COUNT(*) FROM login WHERE password = :password")
    Integer checkPasswordLiveData(String password); 

    /**
     * Überprüft, ob bereits ein Passwort gesetzt wurde.
     *
     * @return Ein LiveData-Objekt mit dem Wert 1, wenn ein Passwort gesetzt ist, sonst 0.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM login)")
    LiveData<Integer> isPasswordSet();

    /**
     * Ruft den Benutzer ab, der aktuell eingeloggt ist.
     *
     * @return Ein LiveData-Objekt des eingeloggten Benutzers.
     */
    @Query("SELECT p.*, l.* FROM person p, login l WHERE p.user = 1")
    LiveData<User> getUser();
}
