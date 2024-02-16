package com.example.presentpal.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.PresentIdea;

/**
 * ViewModel zur Verwaltung von Daten im Zusammenhang mit den Details einer Geschenkidee.
 */
public class PresentIdeaTabDetailsViewModel extends AndroidViewModel {

    // MutableLiveData zur Speicherung der aktuellen Geschenkidee
    MutableLiveData<PresentIdea> presentIdea = new MutableLiveData<>();

    /**
     * Konstruktor für das ViewModel.
     * @param application Der Anwendungskontext.
     */
    public PresentIdeaTabDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    /**
     * Methode zur Rückgabe der MutableLiveData-Instanz, die die aktuelle Geschenkidee enthält.
     * @return MutableLiveData-Instanz mit der aktuellen Geschenkidee.
     */
    public MutableLiveData<PresentIdea> getPresentIdea() {
        return presentIdea;
    }

    /**
     * Methode zum Setzen der aktuellen Geschenkidee.
     * @param presentIdea Die Geschenkidee, die gesetzt werden soll.
     */
    public void setPresentIdea(PresentIdea presentIdea) {
        this.presentIdea.setValue(presentIdea);
    }
}
