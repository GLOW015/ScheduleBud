package com.example.schedulebud.account_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity {

    private EditText editTextTextEmailAddress, editTextTextPassword;
    private TextView forgotPassword, continueWithoutSigningUp, signUp;
    private Button loginButton;
    private boolean enableBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        enableBack = false;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle!=null) {
            enableBack = (boolean) bundle.get("enableBack");
        }

        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        forgotPassword = findViewById(R.id.forgotPassword);
        loginButton = findViewById(R.id.loginButton);
        continueWithoutSigningUp = findViewById(R.id.continueWithoutSigningUp);
        signUp = findViewById(R.id.signUp);

        loginButton.setOnClickListener(view -> login(editTextTextEmailAddress.getText().toString(), editTextTextPassword.getText().toString()));

        forgotPassword.setOnClickListener(view -> {
            Intent intent12 = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
            startActivity(intent12);
        });

        continueWithoutSigningUp.setOnClickListener(view -> {
            prefConfig.saveNoLoginInPref(getApplicationContext(),true);
            finish();
        });

        signUp.setOnClickListener(view -> {
            Intent intent1 = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(intent1);
        });
    }

    private void login(String email, String password) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String URL = prefConfig.getServer()+"user/login";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
            Log.i("VOLLEY", response);
            try {
                JSONObject res = new JSONObject(response);
                String message = res.get("message").toString();
                JSONObject data = (JSONObject) res.get("data");
                String token = data.get("token").toString();
                prefConfig.saveLoginTokenPref(getApplicationContext(),token);
                prefConfig.saveEmailPref(getApplicationContext(),email);
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
                params.put("email", email);
                params.put("password", password);
                return params;
            }

/*            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }*/
        };
        Log.d("string", stringRequest.toString());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        if (enableBack) {
            super.onBackPressed();
        } else {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
    }
}