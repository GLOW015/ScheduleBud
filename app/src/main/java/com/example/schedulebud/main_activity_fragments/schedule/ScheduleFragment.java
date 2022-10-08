package com.example.schedulebud.main_activity_fragments.schedule;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedulebud.R;
import com.example.schedulebud.VPAdapter2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ScheduleFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private int weekNumber;
    private ImageButton scheduleSettings, scheduleAdd, scheduleWeekLeft, scheduleWeekRight;
    private TextView scheduleWeekText;
    private TimetableFragment timetableFragment;
    private static Integer currentScheduleFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schedule, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        tabLayout = view.findViewById(R.id.scheduleTabLayout);
        viewPager2 = view.findViewById(R.id.scheduleViewPager);
        viewPager2.setAdapter(new VPAdapter2(getActivity()));
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(((VPAdapter2)(viewPager2.getAdapter())).mFragmentNames[position])).attach();
        weekNumber = 1;
        scheduleSettings = view.findViewById(R.id.scheduleSettings);
        scheduleAdd = view.findViewById(R.id.scheduleAdd);
        scheduleWeekLeft = view.findViewById(R.id.scheduleWeekLeft);
        scheduleWeekRight = view.findViewById(R.id.scheduleWeekRight);
        scheduleWeekText = view.findViewById(R.id.scheduleWeekText);
        currentScheduleFragment = 1;

        scheduleSettings.setOnClickListener(view1 -> {
            //TODO delete or set timetable
            Toast.makeText(getContext(),"In development",Toast.LENGTH_SHORT).show();
        });

        scheduleAdd.setOnClickListener(view12 -> {
            switch(currentScheduleFragment) {
                case 1:
                    //TODO add event
                    Toast.makeText(getContext(),"In development",Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    //TODO add todo
                    Intent intent = new Intent(getContext(), AddEditToDoTaskActivity.class);
                    intent.putExtra("add", true);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        });

        scheduleWeekLeft.setOnClickListener(view13 -> {
            if (weekNumber>1) {
                weekNumber--;
                if (weekNumber>8) {
                    scheduleWeekText.setText("Week "+(weekNumber-1));
                } else if (weekNumber==8) {
                    scheduleWeekText.setText("Recess Week");
                } else {
                    scheduleWeekText.setText("Week "+weekNumber);
                }
                timetableFragment = TimetableFragment.getInstance();
                timetableFragment.changeWeek(weekNumber);
                if (weekNumber==1) {
                    scheduleWeekLeft.setVisibility(View.INVISIBLE);
                }
                scheduleWeekRight.setVisibility(View.VISIBLE);
            }
        });

        scheduleWeekRight.setOnClickListener(view14 -> {
            if (weekNumber<15) {
                weekNumber++;
                if (weekNumber>8) {
                    scheduleWeekText.setText("Week "+(weekNumber-1));
                } else if (weekNumber==8) {
                    scheduleWeekText.setText("Recess Week");
                } else {
                    scheduleWeekText.setText("Week "+weekNumber);
                }
                timetableFragment = TimetableFragment.getInstance();
                timetableFragment.changeWeek(weekNumber);
                if (weekNumber==15) {
                    scheduleWeekRight.setVisibility(View.INVISIBLE);
                }
                scheduleWeekLeft.setVisibility(View.VISIBLE);
            }
        });

        scheduleWeekLeft.setVisibility(View.INVISIBLE);
    }

    /**
     * Sets Integer to track current child fragment in view
     */
    public static void setCurrentScheduleFragment(Integer fragmentNumber) {
        currentScheduleFragment = fragmentNumber;
    }
}