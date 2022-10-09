package com.example.schedulebud.main_activity_fragments.schedule;

import android.app.Dialog;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;

import java.util.ArrayList;
import java.util.Calendar;

public class TimetableFragment extends Fragment {

    private TextView timetableMonth, timetableDateMonday, timetableDateTuesday, timetableDateWednesday, timetableDateThursday, timetableDateFriday;
    private static TimetableFragment instance;
    private LinearLayout timetableLinearLayoutMonday, timetableLinearLayoutTuesday, timetableLinearLayoutWednesday, timetableLinearLayoutThursday, timetableLinearLayoutFriday;
    private ArrayList<LinearLayout> linearLayoutArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timetable, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        timetableMonth = view.findViewById(R.id.timetableMonth);
        timetableDateMonday = view.findViewById(R.id.timetableDateMonday);
        timetableDateTuesday = view.findViewById(R.id.timetableDateTuesday);
        timetableDateWednesday = view.findViewById(R.id.timetableDateWednesday);
        timetableDateThursday = view.findViewById(R.id.timetableDateThursday);
        timetableDateFriday = view.findViewById(R.id.timetableDateFriday);
        timetableLinearLayoutMonday = view.findViewById(R.id.timetableLinearLayoutMonday);
        timetableLinearLayoutTuesday = view.findViewById(R.id.timetableLinearLayoutTuesday);
        timetableLinearLayoutWednesday = view.findViewById(R.id.timetableLinearLayoutWednesday);
        timetableLinearLayoutThursday = view.findViewById(R.id.timetableLinearLayoutThursday);
        timetableLinearLayoutFriday = view.findViewById(R.id.timetableLinearLayoutFriday);
        instance = this;

        linearLayoutArrayList = new ArrayList<>();
        linearLayoutArrayList.add(timetableLinearLayoutMonday);
        linearLayoutArrayList.add(timetableLinearLayoutTuesday);
        linearLayoutArrayList.add(timetableLinearLayoutWednesday);
        linearLayoutArrayList.add(timetableLinearLayoutThursday);
        linearLayoutArrayList.add(timetableLinearLayoutFriday);

        for (int i = 0; i < linearLayoutArrayList.size(); i++) {
            for (int j = 0; j < 32; j++) {
                TextView textView = new TextView(getContext());
                textView.setTextSize(8);
                textView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        30));
                linearLayoutArrayList.get(i).addView(textView);
            }
        }
        updateTimetable();
        changeWeek(1);
    }

    //Returns the fragment instance to access instance methods
    public static TimetableFragment getInstance() {
        return instance;
    }

    //Changes UI to reflect currently viewed week
    public void changeWeek(int weekNumber) {
        Calendar date = Calendar.getInstance();
        date.set(2022, 7, 8);
        int addWeek = 7 * (weekNumber - 1);
        date.add(Calendar.DATE, addWeek);
        timetableMonth.setText(prefConfig.getMonthFormat(date.get(Calendar.MONTH)));
        timetableDateMonday.setText(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));
        date.add(Calendar.DATE, 1);
        timetableDateTuesday.setText(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));
        date.add(Calendar.DATE, 1);
        timetableDateWednesday.setText(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));
        date.add(Calendar.DATE, 1);
        timetableDateThursday.setText(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));
        date.add(Calendar.DATE, 1);
        timetableDateFriday.setText(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));
    }

    private void updateTimetable() {
        ArrayList<ArrayList<String>> schedule = prefConfig.loadScheduleFromPref(getContext());
        for (int i = 0; i < schedule.size(); i++) {
            for (int j = 0; j < schedule.get(i).size(); j++) {
                TextView timeslot = (TextView) linearLayoutArrayList.get(i).getChildAt(j+1);
                if (!schedule.get(i).get(j).equals("")) {
                    timeslot.setOnClickListener(view -> {
                        EditEventDialogFragment dialogFragment = new EditEventDialogFragment();
                        Bundle bundle = new Bundle();
                        bundle.putString("eventDetails",timeslot.getText().toString());
                        dialogFragment.setArguments(bundle);
                        dialogFragment.show(getActivity().getSupportFragmentManager(),"Edit Event Dialog");
                    });
                    //TODO set onClick to handle edit and delete events
                    timeslot.setText(schedule.get(i).get(j));
                    timeslot.setTextColor(ResourcesCompat.getColor(getResources(), R.color.light_blue, null));
                    if (j - 1 < 0) {
                        timeslot.setTextColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
                        if (!schedule.get(i).get(j).equals(schedule.get(i).get(j+1))) {
                            timeslot.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_timetable_border_full, null));
                        } else {
                            timeslot.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_timetable_border_top, null));
                        }
                    } else if (j + 1 == schedule.get(i).size()) {
                        if (!schedule.get(i).get(j).equals(schedule.get(i).get(j-1))) {
                            timeslot.setTextColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
                            timeslot.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_timetable_border_full, null));
                        } else {
                            timeslot.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_timetable_border_bottom, null));
                        }
                    } else if (!schedule.get(i).get(j).equals(schedule.get(i).get(j-1)) && !schedule.get(i).get(j).equals(schedule.get(i).get(j+1))) {
                        timeslot.setTextColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
                        timeslot.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_timetable_border_full, null));
                    } else if (!schedule.get(i).get(j).equals(schedule.get(i).get(j-1))) {
                        timeslot.setTextColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
                        timeslot.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_timetable_border_top, null));
                    } else if (!schedule.get(i).get(j).equals(schedule.get(i).get(j+1))) {
                        timeslot.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_timetable_border_bottom, null));
                    } else {
                        timeslot.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.custom_timetable_border_middle, null));
                    }
                } else {
                    timeslot.setText("");
                    timeslot.setBackground(null);
                    timeslot.setOnClickListener(null);
                }
            }
        }
    }

    @Override
    public void onResume() {
        ScheduleFragment.setCurrentScheduleFragment(1);
        updateTimetable();
        super.onResume();
    }
}