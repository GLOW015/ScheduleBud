package com.example.schedulebud.main_activity_fragments.schedule;

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
    }

    //Returns the fragment instance to access instance methods
    public static TimetableFragment getInstance() {
        return instance;
    }

    //Changes UI to reflect currently viewed week
    public void changeWeek(int weekNumber) {
        switch(weekNumber){
            case 1:
                timetableMonth.setText("Aug");
                timetableDateMonday.setText("8");
                timetableDateTuesday.setText("9");
                timetableDateWednesday.setText("10");
                timetableDateThursday.setText("11");
                timetableDateFriday.setText("12");
                break;
            case 2:
                timetableMonth.setText("Aug");
                timetableDateMonday.setText("15");
                timetableDateTuesday.setText("16");
                timetableDateWednesday.setText("17");
                timetableDateThursday.setText("18");
                timetableDateFriday.setText("19");
                break;
            case 3:
                timetableMonth.setText("Aug");
                timetableDateMonday.setText("22");
                timetableDateTuesday.setText("22");
                timetableDateWednesday.setText("23");
                timetableDateThursday.setText("24");
                timetableDateFriday.setText("25");
                break;
            case 4:
                timetableMonth.setText("Aug");
                timetableDateMonday.setText("29");
                timetableDateTuesday.setText("30");
                timetableDateWednesday.setText("31");
                timetableDateThursday.setText("1");
                timetableDateFriday.setText("2");
                break;
            case 5:
                timetableMonth.setText("Sep");
                timetableDateMonday.setText("5");
                timetableDateTuesday.setText("6");
                timetableDateWednesday.setText("7");
                timetableDateThursday.setText("8");
                timetableDateFriday.setText("9");
                break;
            case 6:
                timetableMonth.setText("Sep");
                timetableDateMonday.setText("12");
                timetableDateTuesday.setText("13");
                timetableDateWednesday.setText("14");
                timetableDateThursday.setText("15");
                timetableDateFriday.setText("16");
                break;
            case 7:
                timetableMonth.setText("Sep");
                timetableDateMonday.setText("19");
                timetableDateTuesday.setText("20");
                timetableDateWednesday.setText("21");
                timetableDateThursday.setText("22");
                timetableDateFriday.setText("23");
                break;
            case 8:
                timetableMonth.setText("Sep");
                timetableDateMonday.setText("26");
                timetableDateTuesday.setText("27");
                timetableDateWednesday.setText("28");
                timetableDateThursday.setText("29");
                timetableDateFriday.setText("30");
                break;
            case 9:
                timetableMonth.setText("Oct");
                timetableDateMonday.setText("3");
                timetableDateTuesday.setText("4");
                timetableDateWednesday.setText("5");
                timetableDateThursday.setText("6");
                timetableDateFriday.setText("7");
                break;
            case 10:
                timetableMonth.setText("Oct");
                timetableDateMonday.setText("10");
                timetableDateTuesday.setText("11");
                timetableDateWednesday.setText("12");
                timetableDateThursday.setText("13");
                timetableDateFriday.setText("14");
                break;
            case 11:
                timetableMonth.setText("Oct");
                timetableDateMonday.setText("17");
                timetableDateTuesday.setText("18");
                timetableDateWednesday.setText("19");
                timetableDateThursday.setText("20");
                timetableDateFriday.setText("21");
                break;
            case 12:
                timetableMonth.setText("Oct");
                timetableDateMonday.setText("24");
                timetableDateTuesday.setText("25");
                timetableDateWednesday.setText("26");
                timetableDateThursday.setText("27");
                timetableDateFriday.setText("28");
                break;
            case 13:
                timetableMonth.setText("Oct");
                timetableDateMonday.setText("31");
                timetableDateTuesday.setText("1");
                timetableDateWednesday.setText("2");
                timetableDateThursday.setText("3");
                timetableDateFriday.setText("4");
                break;
            case 14:
                timetableMonth.setText("Nov");
                timetableDateMonday.setText("7");
                timetableDateTuesday.setText("8");
                timetableDateWednesday.setText("9");
                timetableDateThursday.setText("10");
                timetableDateFriday.setText("11");
                break;
            case 15:
                timetableMonth.setText("Nov");
                timetableDateMonday.setText("14");
                timetableDateTuesday.setText("15");
                timetableDateWednesday.setText("16");
                timetableDateThursday.setText("17");
                timetableDateFriday.setText("18");
                break;
            default:
                break;
        }
    }

    private void updateTimetable() {
        ArrayList<ArrayList<String>> schedule = prefConfig.loadScheduleFromPref(getContext());
        for (int i = 0; i < schedule.size(); i++) {
            for (int j = 0; j < schedule.get(i).size(); j++) {
                TextView timeslot = (TextView) linearLayoutArrayList.get(i).getChildAt(j+1);
                if (!schedule.get(i).get(j).equals("")) {
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