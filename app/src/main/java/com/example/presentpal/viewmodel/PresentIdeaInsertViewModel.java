package com.example.presentpal.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.presentpal.model.PresentIdeaModel;

public class PresentIdeaInsertViewModel extends AndroidViewModel {

    private final PresentIdeaModel presentIdeaModel;
    public PresentIdeaInsertViewModel(@NonNull Application application) {
        super(application);
        presentIdeaModel = new PresentIdeaModel(application);
    }


    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();
    public MutableLiveData<Integer> personId = new MutableLiveData<>();


    //private MutableLiveData<>
    public void addPresentIdea(View view){
        presentIdeaModel.addPresentIdea(personId.getValue(), title.getValue(), description.getValue());
    }
}
