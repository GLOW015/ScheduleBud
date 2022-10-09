package com.example.schedulebud.main_activity_fragments.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;

import java.util.ArrayList;

public class EditEventActivity extends AppCompatActivity {
    private ImageButton editEventCloseBtn;
    private TextView deleteEvent, eventDetailsTextView;
    private EditText eventDetailsEditText;
    private Button saveEventBtn;
    private String eventDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            eventDetails = (String) bundle.get("eventDetails");
        }

        editEventCloseBtn = findViewById(R.id.editEventCloseBtn);
        deleteEvent = findViewById(R.id.deleteEvent);
        eventDetailsTextView = findViewById(R.id.eventDetailsTextView);
        eventDetailsEditText = findViewById(R.id.eventDetailsEditText);
        saveEventBtn = findViewById(R.id.saveEventBtn);

        editEventCloseBtn.setOnClickListener(view -> finish());

        deleteEvent.setOnClickListener(view -> deleteEvent());

        eventDetailsTextView.setText(eventDetails);
        eventDetailsEditText.setText(eventDetails);

        saveEventBtn.setOnClickListener(view -> editEvent(eventDetailsEditText.getText().toString()));
    }

    private void deleteEvent() {
        editEvent("");
    }

    private void editEvent(String editedEventDetails) {
        boolean stop = false;
        ArrayList<ArrayList<String>> schedule = prefConfig.loadScheduleFromPref(getApplicationContext());
        for (int i = 0; i < schedule.size(); i++) {
            for (int j = 0; j < schedule.get(i).size(); j++) {
                if (schedule.get(i).get(j).equals(eventDetails)) {
                    schedule.get(i).set(j, editedEventDetails);
                    stop = true;
                } else if (stop) {
                    prefConfig.saveSchedulePref(getApplicationContext(), schedule);
                    finish();
                }
            }
        }
    }
}