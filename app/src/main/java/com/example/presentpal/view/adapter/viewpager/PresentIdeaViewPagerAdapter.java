

package com.example.presentpal.view.adapter.viewpager;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentActivity;
        import androidx.viewpager2.adapter.FragmentStateAdapter;

        import com.example.presentpal.db.PresentIdea;
        import com.example.presentpal.view.fragment.EventTabEditFragment;
        import com.example.presentpal.view.fragment.EventTabIdeasFragment;
        import com.example.presentpal.view.fragment.EventTabPresentsFragment;
        import com.example.presentpal.view.fragment.PresentIdeaTabDetailsFragment;
        import com.example.presentpal.view.fragment.PresentIdeaTabEditFragment;

public class PresentIdeaViewPagerAdapter extends FragmentStateAdapter {

    public PresentIdea presentIdea;
    public PresentIdeaViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, PresentIdea presentIdea) {
        super(fragmentActivity);
        this.presentIdea =presentIdea;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return PresentIdeaTabDetailsFragment.newInstance(presentIdea);
            case 1:
                return new PresentIdeaTabEditFragment();
            default:
                return new PresentIdeaTabDetailsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
