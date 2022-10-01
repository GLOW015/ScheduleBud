package com.example.schedulebud.accountactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schedulebud.R;

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

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO register account attempt
            }
        });
    }
}