package com.example.dogforanyone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.format.DateTimeFormatter;

public class CompleteProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        Intent intent = getIntent();
        Dog dInfo = (Dog) intent.getSerializableExtra("dog");
        Owner oInfo = (Owner) intent.getSerializableExtra("owner");
//        Log.d("Main", dInfo.getDogBreed()+oInfo.getFullName());
        ImageView dImage = findViewById(R.id.imgViewdog);
        TextView dBreed = findViewById(R.id.dogBreedID);
        TextView dName = findViewById(R.id.dogNameID);
        TextView dDOB = findViewById(R.id.dobID);
        ImageView oImage = findViewById(R.id.imgViewOwner);
        TextView oName = findViewById(R.id.fullNameID);
        TextView oNumber = findViewById(R.id.phoneID);
        TextView oLocation = findViewById(R.id.locID);

        oImage.setImageResource(oInfo.getOwnerPicId());
        dImage.setImageResource(dInfo.getDogPicDrawable());

        oName.setText("Name: "+oInfo.getFullName());
        oLocation.setText("City: "+oInfo.getLocation());
        oNumber.setText("Contact: "+oInfo.getPhoneNumber());

        dName.setText("Dog's Name: "+dInfo.getDogName());
        dBreed.setText("Dog's Breed: "+dInfo.getDogBreed());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        dDOB.setText("DOB: "+formatter.format(dInfo.getDob()));
        Button bookings = findViewById(R.id.buttonBooking);
        bookings.setOnClickListener(v->{



        });
    }

}
