package com.example.schedulebud.accountactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schedulebud.R;
import com.example.schedulebud.prefConfig;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextTextEmailAddress, editTextTextPassword;
    private TextView forgotPassword, continueWithoutSigningUp, signUp;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        forgotPassword = findViewById(R.id.forgotPassword);
        loginButton = findViewById(R.id.loginButton);
        continueWithoutSigningUp = findViewById(R.id.continueWithoutSigningUp);
        signUp = findViewById(R.id.signUp);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO login attempt
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        continueWithoutSigningUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefConfig.saveNoLoginInPref(getApplicationContext(),true);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}