package com.example.presentpal.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.presentpal.db.Person;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Entität, die ein Ereignis repräsentiert. Diese Klasse bildet Ereignisse auf eine Datenbanktabelle ab.
 * Beziehungen zu anderen Entitäten (z.B. Person) werden über Fremdschlüssel definiert.
 */
@Entity(tableName = "event",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.SET_NULL)
        },
        indices = {@Index(value = "personId")})
public class Event implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "eid")
    public int eid;

    @ColumnInfo(name = "personId")
    public Integer personId;

    @ColumnInfo(name = "title")
    public String title;

    @NotNull
    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "dateSortable")
    public int dateSortable;

    @ColumnInfo(name = "description")
    public String description;

    @ColumnInfo(name = "closed")
    public int closed;

    @ColumnInfo(name = "firstYear")
    public String firstYear;

    @ColumnInfo(name = "budget")
    public float budget;


    /**
     * Konstruktor für Event mit allen Feldern, wird vor allem intern und für die Datenbankoperationen genutzt.
     *
     * @param eid          Eindeutige ID des Ereignisses.
     * @param personId     ID der Person, die mit dem Ereignis verknüpft ist.
     * @param title        Titel des Ereignisses.
     * @param date         Datum des Ereignisses als String.
     * @param dateSortable Datum des Ereignisses in sortierbarer Form.
     * @param description  Beschreibung des Ereignisses.
     * @param closed       Status des Ereignisses (offen/geschlossen).
     * @param firstYear    Das erste Jahr, in dem das Ereignis stattfand.
     * @param budget       Budget für das Ereignis.
     */
    @Ignore
    public Event(int eid, Integer personId, String title, @NotNull String date, int dateSortable, String description, int closed, String firstYear, float budget) {
        this.eid = eid;
        this.personId = personId;
        this.title = title;
        this.date = date;
        this.dateSortable = dateSortable;
        this.description = description;
        this.closed = closed;
        this.firstYear = firstYear;
        this.budget = budget;
    }
    /**
     * Konstruktor für Event ohne ID, nützlich für das Erstellen neuer Ereignisse, bei denen die ID automatisch generiert wird.
     *
     * @param personId     ID der Person, die mit dem Ereignis verknüpft ist.
     * @param title        Titel des Ereignisses.
     * @param date         Datum des Ereignisses als String.
     * @param dateSortable Datum des Ereignisses in sortierbarer Form.
     * @param description  Beschreibung des Ereignisses.
     * @param closed       Status des Ereignisses (offen/geschlossen).
     * @param firstYear    Das erste Jahr, in dem das Ereignis stattfand.
     * @param budget       Budget für das Ereignis.
     */
    public Event(Integer personId, String title, @NotNull String date, int dateSortable, String description, int closed, String firstYear, float budget) {
        this.personId = personId;
        this.title = title;
        this.date = date;
        this.dateSortable = dateSortable;
        this.description = description;
        this.closed = closed;
        this.firstYear = firstYear;
        this.budget = budget;
    }


    public int getId() {
        return eid;
    }

    public void setId(int eid) {
        this.eid = eid;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    public String getDate() {
        return date;
    }

    public void setDate(@NotNull String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setClosed(int closed) {
        this.closed = closed;
    }


    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    /**
     * Konvertiert ein Integer-Datum in das Format "DD.MM.YYYY".
     *
     * @param dateInt Das Datum als Integer.
     * @return Das Datum als formatierter String.
     */
    public static String integerToDate(int dateInt) {
        String tmp = String.valueOf(dateInt);
        String year = tmp.substring(0, 4);
        String month = tmp.substring(4, 6);
        String day = tmp.substring(6, 8);
        return day + "." + month + "." + year;


    }

    /**
     * Konvertiert ein Datum im Format "DD.MM.YYYY" in einen Integer-Wert.
     *
     * @param dateString Das Datum als String.
     * @return Das Datum als Integer.
     */
    public static int dateToInteger(String dateString) {

        String year = dateString.substring(6, 10);
        String month = dateString.substring(3, 5);
        String day = dateString.substring(0, 2);
        String date = year + month + day;
        return Integer.parseInt(date);

    }

    /**
     * Gibt eine String-Repräsentation des Ereignisses zurück, üblicherweise für Debugging-Zwecke.
     *
     * @return Eine String-Repräsentation des Ereignisses.
     */
    @NonNull
    @Override
    public String toString() {
        if (this.title == null) {
            return "-";
        } else {
            return this.title + " (" + this.date + ")";
        }
    }
}
