package com.example.schedulebud.main_activity_fragments.schedule;

import java.util.ArrayList;
import java.util.Collections;

public class ToDoTaskList {
    private ArrayList<ToDoTask> tasks;

    public ToDoTaskList(ArrayList<ToDoTask> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<ToDoTask> getTasks() {
        return tasks;
    }

    public void sortList() {
        Collections.sort(tasks);
    }
}
