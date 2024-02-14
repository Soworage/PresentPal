package com.example.presentpal.viewmodel.fragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.db.PresentIdea;

public class PresentIdeaTabDetailsViewModel extends AndroidViewModel {


    MutableLiveData<PresentIdea> presentIdea = new MutableLiveData<>();

    public PresentIdeaTabDetailsViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<PresentIdea> getPresentIdea() {
        return presentIdea;
    }

    public void setPresentIdea(PresentIdea presentIdea) {
        this.presentIdea.setValue(presentIdea);
    }



}
