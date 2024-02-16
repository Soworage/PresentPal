package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.Person;
import com.example.presentpal.model.PersonRepository;

import java.util.List;

/**
 * Das ViewModel für den Vorgang des Einfügens neuer Kategorien.
 * Es ist zuständig für die Kommunikation zwischen der Repository-Schicht und der UI.
 */
public class CategoryInsertViewModel extends AndroidViewModel {

    private final PersonRepository personRepository;
    public LiveData<List<Person>> allPersons;


    private MutableLiveData<Boolean> finish = new MutableLiveData<>();

    public MutableLiveData<Integer> position = new MutableLiveData<>();

    public MutableLiveData<Person> selectedPerson = new MutableLiveData<>();

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String category;


    /**
     * Konstruktor, der eine Instanz von CategoryInsertViewModel erstellt.
     *
     * @param application Die Anwendung, zu der das ViewModel gehört.
     */
    public CategoryInsertViewModel(@NonNull Application application) {
        super(application);
        personRepository = new PersonRepository(application);
        allPersons = personRepository.getAllPersons();
        position.setValue(0);
    }

    /**
     * Setzt die ausgewählte Person basierend auf der Position in der Liste.
     *
     * @param personPosition Die Position der ausgewählten Person in der Liste.
     */
    public void setSelectedPerson(int personPosition) {
        if (allPersons.getValue() != null) {
            selectedPerson.setValue(allPersons.getValue().get(personPosition));
        }
    }

    /**
     * Fügt eine neue Kategorie zur ausgewählten Person hinzu.
     * Signalisiert das Ende des Vorgangs, wenn die Kategorie erfolgreich hinzugefügt wurde.
     */
    public void addCategory() {
        if (selectedPerson.getValue() != null) {
            long returnValue = personRepository.insertPersonCatergory(selectedPerson.getValue().id, category);
            if (returnValue >= 0) {
                goBack();
            }
        }
    }

    /**
     * Getter für die Position der ausgewählten Person.
     *
     * @return Die Position als LiveData, um Beobachtung zu ermöglichen.
     */
    public MutableLiveData<Integer> getPosition() {
        return position;
    }

    /**
     * Signalisiert, dass der Vorgang beendet werden soll, z.B. durch Schließen der aktuellen Aktivität.
     */
    public void goBack() {
        finish.setValue(true);
    }

    public MutableLiveData<Boolean> getFinish() {
        return finish;
    }
}
