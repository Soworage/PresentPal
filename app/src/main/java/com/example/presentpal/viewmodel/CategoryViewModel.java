package com.example.presentpal.viewmodel;

import android.app.Application;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.R;
import com.example.presentpal.db.Category;
import com.example.presentpal.db.EventJoinPerson;
import com.example.presentpal.db.Person;
import com.example.presentpal.model.EventRepository;
import com.example.presentpal.model.PersonRepository;
import com.example.presentpal.view.adapter.recylerview.CategoryRecyclerViewAdapter;

import java.util.List;

public class CategoryViewModel extends AndroidViewModel {

    private EventRepository eventRepository;

    private PersonRepository personRepository;
    private MutableLiveData<Boolean> finish = new MutableLiveData<>();

    private MutableLiveData<Boolean> add = new MutableLiveData<>();

    public MutableLiveData<Boolean> showAddIcon = new MutableLiveData<>();

    private final Resources resources;
    public MutableLiveData<List<PersonWithEvents>> allEventsWithPersonByCategory = new MutableLiveData<>();



    public MutableLiveData<Integer> categorySymbol = new MutableLiveData<>();
    public MutableLiveData<String> categoryText = new MutableLiveData<>();

    public CategoryViewModel(@NonNull Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        personRepository = new PersonRepository(application);
        finish.setValue(false);
        showAddIcon.setValue(false);

        resources = application.getResources();

        categoryText.setValue(resources.getString(R.string.view_all));
        categorySymbol.setValue(R.drawable.baseline_groups_3_24);
    }

    public MutableLiveData<String> category = new MutableLiveData<>();

    public MutableLiveData<List<PersonWithEvents>> getAllEventsWithPersonByCategory() {
        return allEventsWithPersonByCategory;
    }


    public void getEventsWithPerson() {
        allEventsWithPersonByCategory.setValue(eventRepository.getAllPersonsWithEventsByCategory(category.getValue()));
   }

    public void goBack(){
        finish.setValue(true);
    }

    public void goToAdd(){
        add.setValue(true);
    }

    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }

    public MutableLiveData<Boolean> getAdd() {
        return add;
    }
    public MutableLiveData<String> getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category.setValue(category);
    }

    public LiveData<List<Person>> getAllPersonByCategory(String category){
        return personRepository.getAllPersonsByCategory(category);
    }

    public void setCategoryString(){
        if(getCategory().getValue() != null){
            Log.i("CategoryViewModel", "in setCategoryString()");
            switch (getCategory().getValue()){
                case "#friends":
                    showAddIcon.setValue(true);
                    categoryText.setValue(resources.getString(R.string.friends));
                    categorySymbol.setValue(R.drawable.baseline_person_24);
                    break;
                case "#family":
                    showAddIcon.setValue(true);
                    categoryText.setValue(resources.getString(R.string.family));
                    categorySymbol.setValue(R.drawable.baseline_groups_24);
                    break;
                case "#work":
                    showAddIcon.setValue(true);
                    categoryText.setValue(resources.getString(R.string.colleagues));
                    categorySymbol.setValue(R.drawable.baseline_work_24);
                    break;
                case "#favorites":
                    showAddIcon.setValue(true);
                    categoryText.setValue(resources.getString(R.string.favorites));
                    categorySymbol.setValue(R.drawable.baseline_favorite_24);
                    break;
                default:
                    showAddIcon.setValue(false);
                    categoryText.setValue(resources.getString(R.string.categories));
                    categorySymbol.setValue(R.drawable.baseline_groups_3_24);
            }
        }
    }

    public MutableLiveData<Integer> getCategorySymbol() {
        return categorySymbol;
    }




    public static class PersonWithEvents {
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
            if (number > 2) {
                this.more = "...";
            } else {
                this.more = "";
            }
        }

        public PersonWithEvents(List<EventJoinPerson> eventJoinPersonList) {
            if (eventJoinPersonList != null) {

                this.person = eventJoinPersonList.get(0).person;

                if (eventJoinPersonList.size() < 2) {
                    this.name = eventJoinPersonList.get(0).person.nickname;
                    this.dateA = eventJoinPersonList.get(0).event.date;
                    this.titleA = eventJoinPersonList.get(0).event.title;
                    this.dateB = "-";
                    this.titleB = "-";
                    this.number = eventJoinPersonList.size();
                    this.more = "";
                } else {
                    this.name = eventJoinPersonList.get(0).person.nickname;
                    this.dateA = eventJoinPersonList.get(0).event.date;
                    this.titleA = eventJoinPersonList.get(0).event.title;
                    this.dateB = eventJoinPersonList.get(1).event.date;
                    this.titleB = eventJoinPersonList.get(1).event.title;
                    this.number = eventJoinPersonList.size();
                    if (number > 2) {
                        this.more = "...";
                    } else {
                        this.more = "";
                    }
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
