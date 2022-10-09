package com.example.schedulebud.main_activity_fragments.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CopyScheduleActivity extends AppCompatActivity {

    private ImageButton copyScheduleBackBtn;
    private Button copyScheduleGenerateBtn;
    private EditText copyScheduleEditText;
    private TextView copyScheduleStep1, copyScheduleStep2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy_schedule);

        copyScheduleBackBtn = findViewById(R.id.copyScheduleBackBtn);
        copyScheduleGenerateBtn = findViewById(R.id.copyScheduleGenerateBtn);
        copyScheduleEditText = findViewById(R.id.copyScheduleEditText);
        copyScheduleStep1 = findViewById(R.id.copyScheduleStep1);
        copyScheduleStep2 = findViewById(R.id.copyScheduleStep2);

        copyScheduleBackBtn.setOnClickListener(view -> finish());

        copyScheduleStep1.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://sso.wis.ntu.edu.sg/webexe88/owa/sso_login1.asp?t=1&p2=https://wish.wis.ntu.edu.sg/pls/webexe/aus_stars_check.check_subject_web2&extra=&pg="));
            startActivity(browserIntent);
        });

        copyScheduleGenerateBtn.setOnClickListener(view -> generateSchedule(copyScheduleEditText.getText().toString()));
    }

    private void generateSchedule(String scheduleString) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = prefConfig.getServer()+"schedule/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            Log.i("VOLLEY", response);
            try {
                JSONObject res = new JSONObject(response);
                String message = res.get("message").toString();
                String jsonString = res.get("data").toString();
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<ArrayList<String>>>(){}.getType();
                ArrayList<ArrayList<String>> schedule = gson.fromJson(jsonString, type);
                prefConfig.saveSchedulePref(getApplicationContext(), schedule);
                Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            finish();
        }, error -> {
            Log.e("VOLLEY", error.toString());
            try {
                String responseBody = new String(error.networkResponse.data, "utf-8");
                JSONObject data = new JSONObject(responseBody);
                String message = data.get("message").toString();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            } catch (UnsupportedEncodingException | JSONException e) {
                e.printStackTrace();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("schedule", scheduleString);
                return params;
            }

        };
        Log.d("string", stringRequest.toString());
        requestQueue.add(stringRequest);
    }
}