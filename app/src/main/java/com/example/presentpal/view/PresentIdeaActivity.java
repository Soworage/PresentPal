package com.example.presentpal.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.res.Resources;
import android.os.Bundle;

import com.example.presentpal.R;
import com.example.presentpal.databinding.ActivityPresentIdeaBinding;
import com.example.presentpal.db.Event;
import com.example.presentpal.db.PresentIdea;
import com.example.presentpal.view.adapter.viewpager.PersonViewPagerAdapter;
import com.example.presentpal.view.adapter.viewpager.PresentIdeaViewPagerAdapter;
import com.example.presentpal.view.fragment.NavbarFragment;
import com.example.presentpal.viewmodel.PresentIdeaViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PresentIdeaActivity extends AppCompatActivity {

    private ActivityPresentIdeaBinding activityPresentIdeaBinding;

    private PresentIdeaViewModel presentIdeaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_present_idea);
        activityPresentIdeaBinding = DataBindingUtil.setContentView(this, R.layout.activity_present_idea);
        presentIdeaViewModel = new ViewModelProvider(this).get(PresentIdeaViewModel.class);

        Resources resources = this.getResources();
        activityPresentIdeaBinding.setLifecycleOwner(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.navbar_present_idea, new NavbarFragment())
                    .commit();
        }
        PresentIdea presentIdeaId = (PresentIdea) getIntent().getSerializableExtra("presentIdea");

        TabLayout tabLayout = findViewById(R.id.person_tabs);
        ViewPager2 viewPager = findViewById(R.id.presentIdea_viewpager);

        presentIdeaViewModel.getPresentIdeaById(presentIdeaId.piid).observe(this, presentIdea -> {

            activityPresentIdeaBinding.setPresentIdea(presentIdea);

            PresentIdeaViewPagerAdapter adapter = new PresentIdeaViewPagerAdapter(this);
            viewPager.setAdapter(adapter);


            new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                @Override
                public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {


                    switch (position) {
                        case 0:
                            tab.setText(resources.getString(R.string.details));
                            break;
                        case 1:
                            tab.setText(resources.getString(R.string.edit_idea));
                            break;
                        default:
                            tab.setText("Error");
                            break;
                    }

                }
            }).attach();
        });


    }


}