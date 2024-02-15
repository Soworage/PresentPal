package com.example.presentpal.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.R;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.Person;
import com.example.presentpal.db.PresentIdeaJoinPerson;
import com.example.presentpal.model.PresentIdeaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class EventViewModel extends AndroidViewModel {

    public MutableLiveData<Event> event = new MutableLiveData<>();


    public MutableLiveData<Person> person = new MutableLiveData<>();

    public MutableLiveData<List<PresentIdeaJoinPerson>> allPresentIdeas = new MutableLiveData<>();
    public MutableLiveData<List<PresentIdeaJoinPerson>> allPresents = new MutableLiveData<>();

    public MutableLiveData<Integer> readyState = new MutableLiveData<>();

    public MutableLiveData<String> daysLeft = new MutableLiveData<>();

    public MutableLiveData<String> price = new MutableLiveData<>();

    public MutableLiveData<String> monthString = new MutableLiveData<>();
    public MutableLiveData<Integer> dayInt = new MutableLiveData<>();

    private Resources resources;

    private PresentIdeaRepository presentIdeaRepository;

    public EventViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
        readyState.setValue(0);
        resources = application.getResources();
    }

    public MutableLiveData<List<PresentIdeaJoinPerson>> getAllPresentIdeas() {
        return allPresentIdeas;
    }

    public LiveData<Event> getEventById(int eid) {
        return presentIdeaRepository.getEventById(eid);
    }

    public MutableLiveData<List<PresentIdeaJoinPerson>> getAllPresents() {
        return allPresents;
    }

    public MutableLiveData<Integer> getReadyState() {
        return readyState;
    }

    public void getAllPresentIdeasAndPresents() {

        if (event.getValue() != null) {
            allPresentIdeas.setValue(presentIdeaRepository.getAllPresentIdeasWithPersonByPersonByEvent(event.getValue().personId, event.getValue().eid));
            allPresents.setValue(presentIdeaRepository.getAllPresentWithPersonByPersonByEvent(event.getValue().personId, event.getValue().eid));

            int monthIndex = Integer.parseInt(event.getValue().date.substring(3, 5));
            monthString.setValue(getMonthName(this.getApplication(), monthIndex));
            int dayIndex = Integer.parseInt(event.getValue().date.substring(0, 2));
            dayInt.setValue(dayIndex);
            try {
                daysLeft();
            } catch (ParseException pe) {
                Log.e("EventViewModel", "Date format error!");
            }

        }

    }

    public LiveData<Person> getPeronById(int id){
        return presentIdeaRepository.getPersonById(id);
    }


    public String getMonthName(Context context, int index) {
        String[] months = context.getResources().getStringArray(R.array.months_array);
        return months[index - 1];
    }

    //https://stackoverflow.com/questions/7103064/java-calculate-the-number-of-days-between-two-dates
    public void daysLeft() throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        String startDateString = sdf.format(new Date());

        String endDateString = event.getValue().date;

        long daysDifference = 0;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            // Define the date formatter for the input format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

            // Parse the input strings into LocalDate objects
            LocalDate startDate = LocalDate.parse(startDateString, formatter);
            LocalDate endDate = LocalDate.parse(endDateString, formatter);

            // Calculate the period between the two dates
            daysDifference = ChronoUnit.DAYS.between(startDate, endDate);

            // Get the difference in days

            if (daysDifference >= 0) {
                daysLeft.setValue(resources.getString(R.string.in_days) + daysDifference + resources.getString(R.string.days));
            } else {
                daysLeft.setValue(resources.getString(R.string.since_days) + daysDifference * -1 + resources.getString(R.string.days));
            }
        }

    }

    public void calculatePrice(){
        float priceSum = 0f;
        for (PresentIdeaJoinPerson present: allPresents.getValue()) {
            priceSum += present.presentIdea.price;
        }
        price.setValue(String.valueOf(priceSum) + " €");
    }

    private MutableLiveData<Boolean> finish = new MutableLiveData<>();
    public void goBack(){
        finish.setValue(true);
    }
    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }

    public void closeEvent(){
        Event eventClosed = event.getValue();
        eventClosed.setClosed(1);
        presentIdeaRepository.updateEvent(eventClosed);
        goBack();
    }
}
