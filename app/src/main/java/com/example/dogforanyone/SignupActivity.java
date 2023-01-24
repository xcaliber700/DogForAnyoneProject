package com.example.dogforanyone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    AppDatabase db;
    TextView userName;
    TextView userEmail;
    TextView userPhone;
    TextView userPassword;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        userName = findViewById(R.id.signup_input_name);
        userEmail = findViewById(R.id.signup_input_email);
        userPhone = findViewById(R.id.signup_input_phone);
        userPassword = findViewById(R.id.signup_input_password);
        registerBtn = findViewById(R.id.btn_register);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "User.db").allowMainThreadQueries().build();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    String userNameToAdd = userName.getText().toString();
                    String userEmailToAdd = userEmail.getText().toString();
                    int userPhoneToAdd = Integer.parseInt(userPhone.getText().toString());
                    String userPasswordToAdd = userPassword.getText().toString();
                    User userToAdd = new User(userNameToAdd, userEmailToAdd, userPhoneToAdd, userPasswordToAdd);

                    db.userDao().insertOneUser(userToAdd);

                    startActivity(new Intent(SignupActivity.this,LoginActivity.class));
                }
                catch(Exception err) {
                    Log.d("DBTAG", "onClick: " + err.getMessage());
                }

            }
        });
    }
}