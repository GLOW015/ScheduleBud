package com.example.schedulebud.main_activity_fragments.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.schedulebud.main_activity_fragments.home.HomeHomeFragment;
import com.example.schedulebud.main_activity_fragments.home.IndexSwapFragment;

public class HomeAdapter extends FragmentStateAdapter {
    private final Fragment[] mFragments = new Fragment[] {
            new HomeHomeFragment(),
            new IndexSwapFragment(),
    };
    public final String[] mFragmentNames = new String[] {
            "Home",
            "Index Swap"
    };

    public HomeAdapter(FragmentActivity fa){
        super(fa);
    }

    @Override
    public int getItemCount() {
        return mFragments.length;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return mFragments[position];
    }
}
