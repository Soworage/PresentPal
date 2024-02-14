package com.example.presentpal.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "presentIdea",
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "id",
                        childColumns = "personId",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Event.class,
                        parentColumns = "eid",
                        childColumns = "eventId",
                        onDelete = ForeignKey.SET_NULL)
        },
        indices = {@Index(value = "personId"),@Index(value = "eventId")})

public class PresentIdea implements Serializable {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "piid")
        public int piid;

        @ColumnInfo(name = "personId")
        public int personId;

        @ColumnInfo(name = "eventId")
        public Integer eventId; // Integer erlaubt auch 0 als ID

        @NotNull
        @ColumnInfo(name = "title")
        public String title;

        @ColumnInfo(name = "description")
        public String description;

        @ColumnInfo(name = "shortDescription")
        public String shortDescription;

        @ColumnInfo(name = "price")
        public float price;

        @ColumnInfo(name = "availableAt")
        public String availableAt;

        @ColumnInfo(name = "isPresent")
        public boolean isPresent;

        public PresentIdea(int personId, Integer eventId, @NotNull String title, String description, String shortDescription, float price, String availableAt, boolean isPresent) {
                this.personId = personId;
                this.eventId = eventId;
                this.title = title;
                this.description = description;
                this.shortDescription = shortDescription;
                this.price = price;
                this.availableAt = availableAt;
                this.isPresent = isPresent;
        }

        public int getId() {
                return piid;
        }

        public void setId(int piid) {
                this.piid = piid;
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

        public String getShortDescription() {
                return shortDescription;
        }

        public void setShortDescription(String shortDescription) {
                this.shortDescription = shortDescription;
        }

        public boolean isPresent() {
                return isPresent;
        }

        public void setPresent(boolean present) {
                isPresent = present;
        }
}
