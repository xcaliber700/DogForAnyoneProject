package com.example.dogforanyone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    Button btnGuestContinue, btnSignup, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnSignup = findViewById(R.id.btn_link_signup);
        btnGuestContinue = findViewById(R.id.btn_link_Main);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup.setOnClickListener(v->{
            startActivity(new Intent(LoginActivity.this,SignupActivity.class));
        });
        btnGuestContinue.setOnClickListener(view->{
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        });
        btnLogin.setOnClickListener(v->{
            //yet to be implemented
        });
    }
}