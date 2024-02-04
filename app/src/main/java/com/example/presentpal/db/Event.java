package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.example.presentpal.db.Person;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "event",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.SET_NULL)
        })
public class Event {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        public int id;

        @ColumnInfo(name = "personId")
        public Integer personId;

        @ColumnInfo(name = "title")
        public String title;

        @NotNull
        @ColumnInfo(name = "date")
        public String date;

        @ColumnInfo(name = "description")
        public String description;

        public Event(int id, Integer personId, String title, @NotNull String date, String description) {
                this.id = id;
                this.personId = personId;
                this.title = title;
                this.date = date;
                this.description = description;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
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
}
