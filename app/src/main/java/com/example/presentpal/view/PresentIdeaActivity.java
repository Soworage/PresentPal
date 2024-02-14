package com.example.presentpal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityPresentIdeaBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.view.fragment.NavbarFragment;
import com.example.presentpal.viewmodel.PresentIdeaViewModel;

public class PresentIdeaActivity extends AppCompatActivity {

    private ActivityPresentIdeaBinding activityPresentIdeaBinding;

    private PresentIdeaViewModel presentIdeaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_idea);
        activityPresentIdeaBinding = DataBindingUtil.setContentView(this, R.layout.activity_present_idea);
        presentIdeaViewModel = new ViewModelProvider(this).get(PresentIdeaViewModel.class);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.navbar_present_idea, new NavbarFragment())
                    .commit();
        }
        PresentIdea presentIdeaId = (PresentIdea) getIntent().getSerializableExtra("presentIdea");

        presentIdeaViewModel.getPresentIdeaById(presentIdeaId.piid).observe(this, new Observer<PresentIdea>() {
            @Override
            public void onChanged(PresentIdea presentIdea) {
                activityPresentIdeaBinding.setPresentIdea(presentIdea);
            }
        });

        activityPresentIdeaBinding.setLifecycleOwner(this);
    }


}