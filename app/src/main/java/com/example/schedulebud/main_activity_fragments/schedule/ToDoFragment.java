package com.example.schedulebud.main_activity_fragments.schedule;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.schedulebud.R;
import com.example.schedulebud.main_activity_fragments.home.HomeRecyclerViewAdapter;
import com.example.schedulebud.prefConfig;

public class ToDoFragment extends Fragment {

    private RecyclerView toDoRecyclerView;
    private TextView toDoEmptyTextView;
    private ToDoTaskList toDoTaskList;
    private ToDoFragment instance;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do, container, false);
    }

    @Override
    public void onViewCreated (View view,
                               Bundle savedInstanceState) {
        toDoRecyclerView = view.findViewById(R.id.toDoRecyclerView);
        toDoEmptyTextView = view.findViewById(R.id.toDoEmptyTextView);
        instance = this;
        updateToDoRecyclerView();
    }

    @Override
    public void onResume() {
        ScheduleFragment.setCurrentScheduleFragment(2);
        updateToDoRecyclerView();
        super.onResume();
    }

    public void updateToDoRecyclerView() {
        toDoTaskList = prefConfig.loadToDoTaskListFromPref(getContext());
        if (toDoTaskList.getTasks().size()==0) {
            toDoEmptyTextView.setVisibility(View.VISIBLE);
        } else {
            toDoEmptyTextView.setVisibility(View.GONE);
        }
        ToDoRecyclerViewAdapter adapter = new ToDoRecyclerViewAdapter(instance, getContext(), toDoTaskList);
        toDoRecyclerView.setHasFixedSize(true);
        toDoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        toDoRecyclerView.setAdapter(adapter);
    }
}