package com.example.schedulebud.main_activity_fragments.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.schedulebud.R;

import java.util.Calendar;

public class AddEditToDoTaskActivity extends AppCompatActivity {

    private ImageButton toDoCloseBtn;
    private Button toDoSaveBtn;
    private EditText toDoNameEditText, toDoDateEditText, toDoRemarksEditText;
    private CheckBox toDoImportanceCheckBox;
    private boolean add;
    private ToDoTask toDoTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_to_do_task_acticity);
        toDoCloseBtn = findViewById(R.id.toDoCloseBtn);
        toDoSaveBtn = findViewById(R.id.toDoSaveBtn);
        toDoNameEditText = findViewById(R.id.toDoNameEditText);
        toDoDateEditText = findViewById(R.id.toDoDateEditText);
        toDoRemarksEditText = findViewById(R.id.toDoRemarksEditText);
        toDoImportanceCheckBox = findViewById(R.id.toDoImportanceCheckBox);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            add = (boolean) bundle.get("add");
        } else {
            finish();
        }

        if (!add) {
            toDoTask = (ToDoTask) bundle.get("toDoTask");
            toDoNameEditText.setText(toDoTask.getName());
            toDoDateEditText.setText(toDoTask.getDeadline().toString());
            toDoRemarksEditText.setText(toDoTask.getRemarks());
            toDoImportanceCheckBox.setChecked(toDoTask.isImportant());
        }

        toDoCloseBtn.setOnClickListener(view -> finish());

        toDoSaveBtn.setOnClickListener(view -> {
            //TODO change temp
            Calendar temp = Calendar.getInstance();
            if (add) {
                addToDo(toDoNameEditText.getText().toString(), toDoRemarksEditText.getText().toString(), temp, toDoImportanceCheckBox.isChecked());
            } else {
                editToDo(toDoTask, toDoNameEditText.getText().toString(), toDoRemarksEditText.getText().toString(), temp, toDoImportanceCheckBox.isChecked());
            }
        });

    }

    private void addToDo(String name, String remarks, Calendar deadline, boolean isImportant) {
        //TODO configure add todo
    }
    private void editToDo(ToDoTask toDoTask, String name, String remarks, Calendar deadline, boolean isImportant) {
        //TODO configure edit todo
    }
}