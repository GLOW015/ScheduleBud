package com.example.schedulebud.main_activity_fragments.schedule;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.schedulebud.main_activity_fragments.schedule.TimetableFragment;
import com.example.schedulebud.main_activity_fragments.schedule.ToDoFragment;

public class ScheduleAdapter extends FragmentStateAdapter {
    private final Fragment[] mFragments = new Fragment[] {
            new TimetableFragment(),
            new ToDoFragment(),
    };
    public final String[] mFragmentNames = new String[] {
            "Timetable",
            "To Do"
    };

    public ScheduleAdapter(FragmentActivity fa){
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
