package com.example.presentpal.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.model.PresentIdeaRepository;

public class PresentIdeaTabEditViewModel extends AndroidViewModel {

    private PresentIdeaRepository presentIdeaRepository;

    public MutableLiveData<PresentIdea> presentIdea = new MutableLiveData<>();
    public PresentIdeaTabEditViewModel(@NonNull Application application) {
        super(application);
        presentIdeaRepository = new PresentIdeaRepository(application);
    }

    public void onclickSave(){
        presentIdeaRepository.updatePresentIdea(presentIdea.getValue());
    }
}
