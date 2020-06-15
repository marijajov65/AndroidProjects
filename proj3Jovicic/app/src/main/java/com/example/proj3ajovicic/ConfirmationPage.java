package com.example.proj3ajovicic;
/*
Marija Jovicic
Project 3b
5/12/2020
 */

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmationPage extends AppCompatActivity {
    //Declare variables for TextView where we will display data
    private TextView txtName;
    private TextView txtDob;
    private TextView txtPlayed;
    private TextView txtPlaysFor;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);


        //Retrieve data from MainActivity
        Intent intent = getIntent( );
        String info = intent.getStringExtra("info");
        String[ ] fields = info.split(";");
        String name = fields[0];
        String dob = fields[1];
        String playedtennis = fields[2];
        String compet = fields[3];
        String recr = fields[4];
        String meetPeople = fields[5];

        //Define widgets where we will display data
        txtName = findViewById(R.id.name);
        txtDob = findViewById(R.id.dob);
        txtPlayed = findViewById(R.id.playedTennis);
        txtPlaysFor = findViewById(R.id.playsFor);

        //Get the data form the bundle and change the widget text
        if (savedInstanceState != null) {
            txtName.setText(savedInstanceState.getString("name"));
            txtDob.setText(savedInstanceState.getString("DOB"));
            txtPlayed.setText(savedInstanceState.getString("playedT"));
            txtPlaysFor.setText(savedInstanceState.getString("playsFor"));
        }

        //Input data and display
        txtName.setText("Name: " + name);
        txtDob.setText("DOB: " + dob);
        txtPlayed.setText("Played tennis: " + playedtennis);
        if(compet.equals(" ")){
            compet = "";
        }
        if(recr.equals(" ")){
            recr = "";
        }
        if(meetPeople.equals(" ")){
            meetPeople = "";
        }
        txtPlaysFor.setText("Plays for: " + compet +" "+ recr +" "+ meetPeople );
    }

    @Override
    //When the device is rotated, save the data from the widgets in the bundle
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("name", txtName.getText().toString());
        savedInstanceState.putString("DOB", txtDob.getText().toString());
        savedInstanceState.putString("playedT", txtPlayed.getText().toString());
        savedInstanceState.putString("playsFor", txtPlaysFor.getText().toString());

    }
}
