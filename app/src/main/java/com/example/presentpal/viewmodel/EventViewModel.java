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

/**
 * ViewModel zur Verwaltung der Event-bezogenen Daten und Geschäftslogik.
 * Ermöglicht die Interaktion mit der Datenbank über das Repository für die Präsentationsideen und Ereignisse.
 */
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

    /**
     * Konstruktor, der eine neue Instanz von {@link EventViewModel} initialisiert.
     *
     * @param application Die Anwendung, die das ViewModel besitzt.
     */
    public EventViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
        readyState.setValue(0);
        resources = application.getResources();
    }

    /**
     * Liefert alle Ideen für Präsente.
     *
     * @return Eine Liste aller Präsentideen in Verbindung mit Personen.
     */
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

    /**
     * Holt alle Ideen für Präsente und Präsente und aktualisiert die entsprechenden LiveData-Objekte.
     */
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

    /**
     * Holt eine Person anhand ihrer ID.
     *
     * @param id Die ID der Person.
     * @return Die LiveData der angeforderten Person.
     */
    public LiveData<Person> getPeronById(int id) {
        return presentIdeaRepository.getPersonById(id);
    }

    /**
     * Hilfsmethode zur Ermittlung des Monatsnamens basierend auf einem Index.
     *
     * @param context Der Kontext der Anwendung.
     * @param index   Der Index des Monats.
     * @return Der Name des Monats als String.
     */
    public String getMonthName(Context context, int index) {
        String[] months = context.getResources().getStringArray(R.array.months_array);
        return months[index - 1];
    }

    /**
     * Berechnet die verbleibenden Tage bis zum Ereignis und aktualisiert `daysLeft`.
     *
     * @throws ParseException Wenn das Datumsformat nicht analysiert werden kann.
     */
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

    /**
     * Berechnet den Gesamtpreis aller Präsente und aktualisiert `price`.
     */
    public void calculatePrice() {
        float priceSum = 0f;
        for (PresentIdeaJoinPerson present : allPresents.getValue()) {
            priceSum += present.presentIdea.price;
        }
        price.setValue(String.valueOf(priceSum) + " €");
    }

    private MutableLiveData<Boolean> finish = new MutableLiveData<>();

    /**
     * Signalisiert den Wunsch, den aktuellen Vorgang zu beenden und zurückzukehren.
     */
    public void goBack() {
        finish.setValue(true);
    }

    /**
     * Liefert die LiveData, die angibt, ob der Vorgang beendet werden soll.
     *
     * @return Die MutableLiveData, die den Beendigungswunsch enthält.
     */
    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }

    /**
     * Schließt das aktuelle Ereignis und aktualisiert die Datenbank entsprechend.
     */
    public void closeEvent() {
        Event eventClosed = event.getValue();
        eventClosed.setClosed(1);
        presentIdeaRepository.updateEvent(eventClosed);
        goBack();
    }
}
