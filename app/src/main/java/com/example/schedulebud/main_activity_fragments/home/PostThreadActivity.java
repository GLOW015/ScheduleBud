package com.example.schedulebud.main_activity_fragments.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class PostThreadActivity extends AppCompatActivity {

    private ImageButton homeHomeCloseBtn;
    private Button homeHomePostBtn;
    private EditText homeHomeTitleEditText, homeHomeContentEditText;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_thread);
        homeHomeCloseBtn = findViewById(R.id.homeHomeCloseBtn);
        homeHomePostBtn = findViewById(R.id.homeHomePostBtn);
        homeHomeTitleEditText = findViewById(R.id.homeHomeTitleEditText);
        homeHomeContentEditText = findViewById(R.id.homeHomeContentEditText);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            token = (String) bundle.get("token");
        } else {
            finish();
        }

        homeHomeCloseBtn.setOnClickListener(view -> finish());

        homeHomePostBtn.setOnClickListener(view -> postThread(homeHomeTitleEditText.getText().toString(), homeHomeContentEditText.getText().toString(), token));
    }

    private void postThread(String title, String content, String token) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = prefConfig.getServer()+"thread/";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            Log.i("VOLLEY", response);
            try {
                JSONObject res = new JSONObject(response);
                String message = res.get("message").toString();
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
                params.put("title", title);
                params.put("content", content);
                params.put("isAnonymous", "2"); //1 for anonymous, 2 for not
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("user",token);
                return params;
            }
        };
        Log.d("string", stringRequest.toString());
        requestQueue.add(stringRequest);
    }
}