package com.example.schedulebud.main_activity_fragments.schedule;

import java.util.Calendar;

public class ToDoTask implements Comparable<ToDoTask> {
    private String name;
    private boolean important;
    private Calendar deadline;
    private String remarks;

    public ToDoTask(String name, boolean important, Calendar deadline, String remarks) {
        this.name = name;
        this.important = important;
        this.deadline = deadline;
        this.remarks = remarks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public void setDeadline(Calendar deadline) {
        this.deadline = deadline;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public int compareTo(ToDoTask o) {
        return getDeadline().compareTo(o.getDeadline());
    }
}
