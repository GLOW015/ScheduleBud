package com.example.schedulebud.main_activity_fragments.schedule;

import java.util.Calendar;

public class ToDoTask implements Comparable<ToDoTask> {
    private String name;
    private boolean important;
    private Calendar deadline;
    private String remarks;
    private boolean notify;
    private boolean hasDeadline;
    private static int counter = 0;
    private int counterNum;

    public ToDoTask(String name, boolean important, Calendar deadline, String remarks, boolean notify, boolean hasDeadline) {
        if (counter > 10000) {
            counter = 0;
        }
        counter += 4;
        this.name = name;
        this.important = important;
        this.deadline = deadline;
        this.remarks = remarks;
        this.notify = notify;
        this.hasDeadline = hasDeadline;
        this.counterNum = counter;
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

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public boolean isHasDeadline() {
        return hasDeadline;
    }

    public void setHasDeadline(boolean hasDeadline) {
        this.hasDeadline = hasDeadline;
    }

    public int getCounterNum() {
        return counterNum;
    }

    @Override
    public int compareTo(ToDoTask o) {
        return getDeadline().compareTo(o.getDeadline());
    }
}
