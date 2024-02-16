package com.example.presentpal.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.model.PresentIdeaRepository;

/**
 * ViewModel zur Bearbeitung von Geschenkideen.
 */
public class PresentIdeaTabEditViewModel extends AndroidViewModel {

    private PresentIdeaRepository presentIdeaRepository;

    // MutableLiveData zur Speicherung der aktuellen Geschenkidee
    public MutableLiveData<PresentIdea> presentIdea = new MutableLiveData<>();

    /**
     * Konstruktor für das ViewModel.
     * @param application Die Anwendungsinstanz.
     */
    public PresentIdeaTabEditViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
    }

    /**
     * Methode zur Behandlung des Klicks auf die Speichern-Schaltfläche.
     * Es aktualisiert die Geschenkidee mithilfe des Repositorys.
     */
    public void onclickSave(){
        presentIdeaRepository.updatePresentIdea(presentIdea.getValue());
    }
}
