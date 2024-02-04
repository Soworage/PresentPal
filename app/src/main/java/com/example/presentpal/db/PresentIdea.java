package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "presentIdea",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Event.class,
                        parentColumns = "id",
                        childColumns = "eventId",
                        onDelete = ForeignKey.SET_NULL)
        })
public class PresentIdea {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        public int id;

        @ColumnInfo(name = "personId")
        public int personId;

        @ColumnInfo(name = "eventId")
        public Integer eventId; // Integer erlaubt auch 0 als ID

        @NotNull
        @ColumnInfo(name = "title")
        public String title;

        @ColumnInfo(name = "description")
        public String description;

        @ColumnInfo(name = "price")
        public float price;

        @ColumnInfo(name = "availableAt")
        public String availableAt;

        public PresentIdea(int id, int personId, Integer eventId, @NotNull String title, String description, float price, String availableAt) {
                this.id = id;
                this.personId = personId;
                this.eventId = eventId;
                this.title = title;
                this.description = description;
                this.price = price;
                this.availableAt = availableAt;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public int getPersonId() {
                return personId;
        }

        public void setPersonId(int personId) {
                this.personId = personId;
        }

        public Integer getEventId() {
                return eventId;
        }

        public void setEventId(Integer eventId) {
                this.eventId = eventId;
        }

        @NotNull
        public String getTitle() {
                return title;
        }

        public void setTitle(@NotNull String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public float getPrice() {
                return price;
        }

        public void setPrice(float price) {
                this.price = price;
        }

        public String getAvailableAt() {
                return availableAt;
        }

        public void setAvailableAt(String availableAt) {
                this.availableAt = availableAt;
        }
}
