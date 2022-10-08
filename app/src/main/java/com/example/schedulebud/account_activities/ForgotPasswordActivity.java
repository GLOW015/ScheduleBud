package com.example.schedulebud.account_activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.schedulebud.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText editTextTextEmailAddress;
    private Button resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddressForgotPassword);
        resetButton = findViewById(R.id.resetButton);

        resetButton.setOnClickListener(view -> {
            //TODO reset password attempt
        });
    }
}