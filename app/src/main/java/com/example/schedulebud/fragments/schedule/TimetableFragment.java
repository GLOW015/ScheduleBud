package com.example.schedulebud.fragments.schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schedulebud.R;

public class TimetableFragment extends Fragment {

    private TextView timetableMonth, timetableDateMonday, timetableDateTuesday, timetableDateWednesday, timetableDateThursday, timetableDateFriday;
    private static TimetableFragment instance;

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
        instance = this;
    }

    /**
     * Returns the fragment instance to access instance methods
     */
    public static TimetableFragment getInstance() {
        return instance;
    }

    /**
     * Changes UI to reflect currently viewed week
     */
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
                timetableMonth.setText("Aug/Sep");
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
                timetableMonth.setText("Oct/Nov");
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

    @Override
    public void onResume() {
        ScheduleFragment.setCurrentScheduleFragment(1);
        super.onResume();
    }
}