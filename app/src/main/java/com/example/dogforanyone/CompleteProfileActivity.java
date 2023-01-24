package com.example.dogforanyone;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class CompleteProfileActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Dog dInfo;
    Owner oInfo;
    Button bookings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);
        Intent intent = getIntent();
        dInfo = (Dog) intent.getSerializableExtra("dog");
        oInfo = (Owner) intent.getSerializableExtra("owner");
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
        bookings = findViewById(R.id.buttonBooking);
        bookings.setOnClickListener(v->{

            showDatePickerDialog();


        });
    }
    private void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        TextView txtMessage = findViewById(R.id.txtViewMessage);
        txtMessage.setText("You have successfully booked "+ dInfo.getDogName() +"("+dInfo.getDogBreed()+
                ") for the date "+i+"/"+i1+"/"+ i2);
        bookings.setVisibility(View.INVISIBLE);

    }
}
