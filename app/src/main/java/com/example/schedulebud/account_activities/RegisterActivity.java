package com.example.schedulebud.account_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextTextEmailAddress, editTextTextPassword;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddressRegister);
        editTextTextPassword = findViewById(R.id.editTextTextPasswordRegister);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(view -> register(editTextTextEmailAddress.getText().toString(), editTextTextPassword.getText().toString()));
    }

    public void register(String email, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = prefConfig.getServer()+"user/register";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            Log.i("VOLLEY", response);
            try {
                JSONObject res = new JSONObject(response);
                String message = res.get("message").toString();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> {
            Log.e("VOLLEY", error.toString());
            try {
                String responseBody = new String(error.networkResponse.data, "utf-8");
                JSONObject data = new JSONObject(responseBody);
                String message = data.get("message").toString();
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            } catch (UnsupportedEncodingException e) {
                Log.e("VOLLEY", "here");
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e("VOLLEY", "there");
                e.printStackTrace();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
        Log.d("string", stringRequest.toString());
        requestQueue.add(stringRequest);
    }
}