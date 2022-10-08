package com.example.schedulebud.main_activity_fragments.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schedulebud.R;
import com.example.schedulebud.VPAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.homeTabLayout);
        viewPager2 = view.findViewById(R.id.homeViewPager);
        viewPager2.setAdapter(new VPAdapter(getActivity()));
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(((VPAdapter)(viewPager2.getAdapter())).mFragmentNames[position])).attach();
    }
}