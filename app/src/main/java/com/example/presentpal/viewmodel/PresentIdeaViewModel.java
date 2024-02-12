package com.example.presentpal.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.model.PresentIdeaRepository;

public class PresentIdeaViewModel extends AndroidViewModel {

    private final PresentIdeaRepository presentIdeaRepository;

    public PresentIdeaViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
    }

    public LiveData<PresentIdea> getPresentIdeaById(int presentIdeaId) {
        return presentIdeaRepository.getPresentIdeaById(presentIdeaId);
    }
}
