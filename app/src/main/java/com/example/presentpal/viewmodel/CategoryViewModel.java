package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Category;
import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.db.Person;
import com.example.presentpal.model.EventRepository;
import com.example.presentpal.model.PersonRepository;
import com.example.presentpal.view.adapter.recylerview.CategoryRecyclerViewAdapter;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private PersonRepository personRepository;
    private EventRepository eventRepository;
    public CategoryViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        eventRepository = new EventRepository(application);
       getEventsWithPerson();
    }

    public MutableLiveData<Category> category = new MutableLiveData<>();

    public MutableLiveData<List<PersonWithEvents>> getAllEventsWithPersonByCategory() {
        return allEventsWithPersonByCategory;
    }

    public MutableLiveData<List<PersonWithEvents>> allEventsWithPersonByCategory = new MutableLiveData<>();


    public void getEventsWithPerson(){

            allEventsWithPersonByCategory.setValue(eventRepository.getAllPersonsWithEventsByCategory(category.getValue()));

    }



    public static class PersonWithEvents{
        private String name;
        private String dateA;
        private String titleA;
        private String dateB;
        private String titleB;
        private int number;
        private String more;

        private Person person;

        public PersonWithEvents(String name, String dateA, String titleA, String dateB, String titleB, int number) {
            this.name = name;
            this.dateA = dateA;
            this.titleA = titleA;
            this.dateB = dateB;
            this.titleB = titleB;
            this.number = number;
            if (number > 2){this.more = "...";}
            else {this.more = "";}
        }

        public PersonWithEvents(List<EventJoinPerson> eventJoinPersonList){
            if(eventJoinPersonList != null){

                this.person = eventJoinPersonList.get(0).person;

                if (eventJoinPersonList.size() < 2){
                    this.name = eventJoinPersonList.get(0).person.nickname;
                    this.dateA = eventJoinPersonList.get(0).event.date;
                    this.titleA = eventJoinPersonList.get(0).event.title;
                    this.dateB = "-";
                    this.titleB = "-";
                    this.number = eventJoinPersonList.size();
                   this.more = "";
                }
                else {
                    this.name = eventJoinPersonList.get(0).person.nickname;
                    this.dateA = eventJoinPersonList.get(0).event.date;
                    this.titleA = eventJoinPersonList.get(0).event.title;
                    this.dateB = eventJoinPersonList.get(1).event.date;
                    this.titleB = eventJoinPersonList.get(1).event.title;
                    this.number = eventJoinPersonList.size();
                    if (number > 2){this.more = "...";}
                    else {this.more = "";}
                }
            }

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDateA() {
            return dateA;
        }

        public void setDateA(String dateA) {
            this.dateA = dateA;
        }

        public String getTitleA() {
            return titleA;
        }

        public void setTitleA(String titleA) {
            this.titleA = titleA;
        }

        public String getDateB() {
            return dateB;
        }

        public void setDateB(String dateB) {
            this.dateB = dateB;
        }

        public String getTitleB() {
            return titleB;
        }

        public void setTitleB(String titleB) {
            this.titleB = titleB;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getMore() {
            return more;
        }

        public void setMore(String more) {
            this.more = more;
        }

        public Person getPerson() {
            return person;
        }
    }

}
