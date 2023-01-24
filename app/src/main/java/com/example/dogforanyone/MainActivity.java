package com.example.dogforanyone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    List<Dog> DogList = new ArrayList<Dog>();
    List<Owner> OwnerList = new ArrayList<Owner>();
    TextView txtViewAdoptSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawe_open, R.string.navigation_drawe_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //List view Code

        DogList = ReadDogData();
        OwnerList = ReadOwnerData();

        ListView listViewItems = findViewById(R.id.listViewItems);
        listViewItems.setAdapter(new DogAdapter(DogList));


        listViewItems.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {

            System.out.println("Enter clicking phase"+DogList.get(i).getDogBreed());
            Dog clickedInfo = DogList.get(i);
            Owner personInfo = new Owner();
            for(Owner info:OwnerList){
                if(info.getDogId() == clickedInfo.getId()){
                    personInfo = info;
                    break;
                }
            }

            System.out.println("Enter clicking phase"+personInfo.getFullName());
            Intent intent = new Intent(MainActivity.this, CompleteProfileActivity.class);
            intent.putExtra("dog",clickedInfo);
            intent.putExtra("owner",personInfo);
            startActivity(intent);

        });
    }

    private List<Owner> ReadOwnerData() {
        List<Owner> ListOfDogOwners = new ArrayList<Owner>();

        InputStream inputStream = getResources().openRawResource(R.raw.dogowner);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String csvLine;

            //if you have a header line, you must read it first before you enter the loop
            //for reading and parsing data lines

            while((csvLine = reader.readLine()) != null) {
                String[] eachRow = csvLine.split(",");
                int id = Integer.parseInt(eachRow[0]);
                String ownerPicName = eachRow[1];
                int ownerDrawable = getResources().getIdentifier(ownerPicName,
                        "drawable",getPackageName()); //gets the enumerated id of the image

                String fullname = eachRow[2];
                String phone = eachRow[3];
                String loc = eachRow[4];
                int dogId = Integer.parseInt(eachRow[5]);

                Owner eachPerson = new Owner(id,fullname,ownerDrawable,phone,loc,dogId);
                ListOfDogOwners.add(eachPerson);
            }

        } catch (IOException ex) {
            Log.d("FILEException: ",ex.getMessage());
        } catch (Exception ex){
            Log.d("FILEException: ",ex.getMessage());
        } finally{
            try {
                inputStream.close();
            } catch (IOException ex) {
                Log.d("FILEException: ",ex.getMessage());
            }
        }

        return ListOfDogOwners;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private List<Dog> ReadDogData(){
        List<Dog> DogList = new ArrayList<Dog>();

        InputStream inputStream = getResources().openRawResource(R.raw.doginfo);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String csvLine;

            //if you have a header line, you must read it first before you enter the loop
            //for reading and parsing data lines

            while((csvLine = reader.readLine()) != null) {
                String[] eachDogRow = csvLine.split(",");
                int id = Integer.parseInt(eachDogRow[0]);
                String dogPicName = eachDogRow[1];
                int dogDrawable = getResources().getIdentifier(dogPicName,
                        "drawable",getPackageName()); //gets the enumerated id of the image

                String dogBreed = eachDogRow[2];
                String dogName = eachDogRow[3];
                String dobStr = eachDogRow[4];

                //single d => one digit or 2 digit date vs. dd refers to always two digits for date (day of the month)
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                LocalDate dob = LocalDate.parse(dobStr,formatter);

                Dog eachDog = new Dog(id,dogBreed,dogName,dogDrawable,dob);
                DogList.add(eachDog);
            }

        } catch (IOException ex) {
            Log.d("FILEException: ",ex.getMessage());
        } catch (Exception ex){
            Log.d("FILEException: ",ex.getMessage());
        } finally{
            try {
                inputStream.close();
            } catch (IOException ex) {
                Log.d("FILEException: ",ex.getMessage());
            }
        }

        return DogList;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_customerService:
                Toast.makeText(this, "We will call you in few minutes.", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);

        switch (item.getItemId()){
            case R.id.nav_login:
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                break;
            case R.id.nav_signup:
                startActivity(new Intent(MainActivity.this,SignupActivity.class));
                break;
            case R.id.nav_service:
                Toast.makeText(this, "We will call you in few minutes.", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}