

package com.example.presentpal.view.adapter;

        import androidx.annotation.NonNull;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentActivity;
        import androidx.viewpager2.adapter.FragmentStateAdapter;

        import com.example.presentpal.view.fragment.EventTabEditFragment;
        import com.example.presentpal.view.fragment.EventTabIdeasFragment;
        import com.example.presentpal.view.fragment.EventTabPresentsFragment;
        import com.example.presentpal.view.fragment.PresentIdeaTabDetailsFragment;
        import com.example.presentpal.view.fragment.PresentIdeaTabEditFragment;

public class PresentIdeaViewPagerAdapter extends FragmentStateAdapter {
    public PresentIdeaViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position) {
            case 0:
                return new PresentIdeaTabDetailsFragment();
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
