package com.example.schedulebud.main_activity_fragments.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedulebud.NotificationUtils;
import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;
import com.google.gson.Gson;

import java.util.Calendar;

public class AddEditToDoTaskActivity extends AppCompatActivity {

    private ImageButton toDoCloseBtn;
    private Button toDoSaveBtn;
    private TextView toDoDatePickerBtn, toDoTimePickerBtn;
    private EditText toDoNameEditText, toDoRemarksEditText;
    private CheckBox toDoDateCheckBox, toDoImportanceCheckBox;
    private boolean add;
    private ToDoTask toDoTask;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_to_do_task_activity);
        toDoCloseBtn = findViewById(R.id.toDoCloseBtn);
        toDoSaveBtn = findViewById(R.id.toDoSaveBtn);
        toDoDatePickerBtn = findViewById(R.id.toDoDatePickerBtn);
        toDoTimePickerBtn = findViewById(R.id.toDoTimePickerBtn);
        toDoNameEditText = findViewById(R.id.toDoNameEditText);
        toDoRemarksEditText = findViewById(R.id.toDoRemarksEditText);
        toDoImportanceCheckBox = findViewById(R.id.toDoImportanceCheckBox);
        toDoDateCheckBox = findViewById(R.id.toDoDateCheckBox);
        toDoDatePickerBtn.setText(getTodayDate());
        toDoTimePickerBtn.setText(getTodayTime());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            add = (boolean) bundle.get("add");
        } else {
            finish();
        }

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        if (!add) {
            String json = (String) bundle.get("toDoTask");
            Gson gson = new Gson();
            toDoTask = gson.fromJson(json, ToDoTask.class);
            toDoNameEditText.setText(toDoTask.getName());
            toDoDateCheckBox.setChecked(toDoTask.isHasDeadline());
            if (toDoTask.isHasDeadline()) {
                year = toDoTask.getDeadline().get(Calendar.YEAR);
                month = toDoTask.getDeadline().get(Calendar.MONTH);
                day = toDoTask.getDeadline().get(Calendar.DAY_OF_MONTH);
                String deadlineDate = prefConfig.makeDateString(day, month, year);
                toDoDatePickerBtn.setText(deadlineDate);
                hour = toDoTask.getDeadline().get(Calendar.HOUR_OF_DAY);
                minute = toDoTask.getDeadline().get(Calendar.MINUTE);
                String deadlineTime = prefConfig.makeTimeString(hour, minute);
                toDoTimePickerBtn.setText(deadlineTime);
            }
            toDoRemarksEditText.setText(toDoTask.getRemarks());
            toDoImportanceCheckBox.setChecked(toDoTask.isImportant());
        }

        initDatePicker(year, month, day);
        initTimePicker(hour, minute);

        toDoCloseBtn.setOnClickListener(view -> finish());

        toDoSaveBtn.setOnClickListener(view -> {
            if (toDoDateCheckBox.isChecked()) {
                Calendar date = Calendar.getInstance();
                int[] dateInt = prefConfig.getDateFromString(toDoDatePickerBtn.getText().toString());
                int[] timeInt = prefConfig.getTimeFromString(toDoTimePickerBtn.getText().toString());
                date.set(Calendar.DAY_OF_MONTH, dateInt[0]);
                date.set(Calendar.MONTH, dateInt[1]);
                date.set(Calendar.YEAR, dateInt[2]);
                date.set(Calendar.HOUR_OF_DAY, timeInt[0]);
                date.set(Calendar.MINUTE, timeInt[1]);
                date.set(Calendar.SECOND, 0);
                date.set(Calendar.MILLISECOND, 0);
                if (add) {
                    addToDo(toDoNameEditText.getText().toString(), toDoRemarksEditText.getText().toString(), date, toDoImportanceCheckBox.isChecked(), toDoDateCheckBox.isChecked());
                } else {
                    editToDo(toDoTask, toDoNameEditText.getText().toString(), toDoRemarksEditText.getText().toString(), date, toDoImportanceCheckBox.isChecked(), toDoDateCheckBox.isChecked());
                }
            } else {
                Calendar date = Calendar.getInstance();
                date.set(Calendar.DAY_OF_MONTH, 1);
                date.set(Calendar.MONTH, 0);
                date.set(Calendar.YEAR, 3000);
                date.set(Calendar.HOUR_OF_DAY, 0);
                date.set(Calendar.MINUTE, 0);
                date.set(Calendar.SECOND, 0);
                date.set(Calendar.MILLISECOND, 0);

                if (add) {
                    addToDo(toDoNameEditText.getText().toString(), toDoRemarksEditText.getText().toString(), date, toDoImportanceCheckBox.isChecked(), toDoDateCheckBox.isChecked());
                } else {
                    editToDo(toDoTask, toDoNameEditText.getText().toString(), toDoRemarksEditText.getText().toString(), date, toDoImportanceCheckBox.isChecked(), toDoDateCheckBox.isChecked());
                }
            }
        });

        toDoDatePickerBtn.setOnClickListener(view -> datePickerDialog.show());

        toDoTimePickerBtn.setOnClickListener(view -> timePickerDialog.show());
    }

    private void addToDo(String name, String remarks, Calendar deadline, boolean isImportant, boolean hasDeadline) {
        ToDoTaskList toDoTaskList = prefConfig.loadToDoTaskListFromPref(getApplicationContext());
        toDoTaskList.getTasks().add(new ToDoTask(name, isImportant, deadline, remarks, false, hasDeadline));
        toDoTaskList.sortList();
        prefConfig.saveToDoTaskListPref(getApplicationContext(),toDoTaskList);
        finish();
    }
    private void editToDo(ToDoTask toDoTask, String name, String remarks, Calendar deadline, boolean isImportant, boolean hasDeadline) {
        ToDoTaskList toDoTaskList = prefConfig.loadToDoTaskListFromPref(getApplicationContext());
        ToDoTask toDoTaskToEdit;
        for (int i = 0; i < toDoTaskList.getTasks().size(); i++) {
            toDoTaskToEdit = toDoTaskList.getTasks().get(i);
            if (toDoTaskToEdit.getName().equals(toDoTask.getName())
                    && toDoTaskToEdit.getRemarks().equals(toDoTask.getRemarks())
                    && toDoTaskToEdit.getDeadline().equals(toDoTask.getDeadline())
                    && toDoTaskToEdit.isImportant()==toDoTask.isImportant()
                    && toDoTaskToEdit.isNotify()==toDoTask.isNotify()
                    && toDoTaskToEdit.isHasDeadline()==toDoTask.isHasDeadline()) {
                toDoTaskToEdit.setName(name);
                toDoTaskToEdit.setImportant(isImportant);
                toDoTaskToEdit.setRemarks(remarks);
                toDoTaskToEdit.setDeadline(deadline);
                toDoTaskToEdit.setHasDeadline(hasDeadline);
                toDoTaskToEdit.setNotify(false);
                toDoTaskList.sortList();
                prefConfig.saveToDoTaskListPref(getApplicationContext(),toDoTaskList);
                removeNotificationsAfterEdit(toDoTask);
                finish();
                break;
            }
        }
    }

    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return prefConfig.makeDateString(day, month, year);
    }

    private String getTodayTime() {
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        return prefConfig.makeTimeString(hour, minute);
    }

    private void initDatePicker(int year, int month, int day) {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, yr, mth, d) -> {
            String date = prefConfig.makeDateString(d, mth, yr);
            toDoDatePickerBtn.setText(date);
        };
        int style = AlertDialog.THEME_HOLO_LIGHT;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private void initTimePicker(int hour, int minute) {
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hr, min) -> {
            String time = prefConfig.makeTimeString(hr, min);
            toDoTimePickerBtn.setText(time);
        };
        int style = AlertDialog.THEME_HOLO_LIGHT;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hour, minute, false);
    }

    private void removeNotificationsAfterEdit(ToDoTask toDoTask) {
        if (toDoTask.isNotify()) {
            Toast.makeText(getApplicationContext(), "Reminder cancelled after edit", Toast.LENGTH_SHORT).show();
            NotificationUtils _notificationUtils = new NotificationUtils(getApplicationContext());
            long currentTime = Calendar.getInstance().getTimeInMillis();
            long toDoTaskTime = toDoTask.getDeadline().getTimeInMillis();
            long oneHourBefore = toDoTaskTime - 1000*60*60;
            long twelveHoursBefore = toDoTaskTime - 1000*60*60*12;
            long oneDayBefore = toDoTaskTime - 1000*60*60*24;
            if (toDoTaskTime > currentTime) {
                _notificationUtils.cancelReminder(toDoTaskTime, toDoTask.getName() + " NOW", toDoTask.getRemarks(), toDoTask.getCounterNum());
            }
            if (oneHourBefore > currentTime) {
                _notificationUtils.cancelReminder(oneHourBefore, toDoTask.getName() + " 1h", toDoTask.getRemarks(), toDoTask.getCounterNum() + 1);
            }
            if (twelveHoursBefore > currentTime) {
                _notificationUtils.cancelReminder(twelveHoursBefore, toDoTask.getName() + " 12h", toDoTask.getRemarks(), toDoTask.getCounterNum() + 2);
            }
            if (oneDayBefore > currentTime) {
                _notificationUtils.cancelReminder(oneDayBefore, toDoTask.getName() + " 1d", toDoTask.getRemarks(), toDoTask.getCounterNum() + 3);
            }
        }
    }
}