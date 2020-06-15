package com.example.proj3ajovicic;
/*
Marija Jovicic
Project 3b
5/12/2020
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Create variables for widgets
private EditText name;
private EditText DOB;
private CheckBox playedT;
private Switch competition;
private Switch recreation;
private Switch meetNewPeople;
private TextView txtDisplay;

String fullName;
String dateOfBirth;
String compet;
String playedTennis;
String recr;
String meetPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Define widgets
        name = findViewById(R.id.name);
        DOB = findViewById(R.id.date);
        playedT = findViewById(R.id.checkBox);
        competition = findViewById(R.id.competition);
        recreation = findViewById(R.id.recreation);
        meetNewPeople = findViewById(R.id.newPeople);


        //Get the data fromm the bundle and change the widget text
        if (savedInstanceState != null) {
            name.setText(savedInstanceState.getString("name"));
            DOB.setText(savedInstanceState.getString("DOB"));
            playedT.setChecked(savedInstanceState.getBoolean("playedT"));

            competition.setChecked(savedInstanceState.getBoolean("competition"));
            recreation.setChecked(savedInstanceState.getBoolean("recreation"));
            meetNewPeople.setChecked(savedInstanceState.getBoolean("meetNewPeople"));
        }


    }
    //Call this method when the button from MainActivity is clicked
    public void callConfirmationPage(View view) {

        //Get the data from widgets
        fullName =  name.getText( ).toString( );
        dateOfBirth =  DOB.getText( ).toString( );
        playedTennis = playedT.isChecked( ) ? "Yes" : "No";
        compet = competition.isChecked( ) ? "\nCompetition" : " ";
        recr = recreation.isChecked( ) ? "\nRecreation" : " ";
        meetPeople = meetNewPeople.isChecked( ) ? "\nMeeting new people" : " ";

        //Convert the data into one String that we will pass to another activity
        String info = String.format("%s;%s;%s;%s;%s;%s",
                fullName, dateOfBirth, playedTennis, compet, recr, meetPeople);

        //Add the data to the intent
        Intent intent = new Intent(this,ConfirmationPage.class);
        intent.putExtra("info",info);
        startActivity(intent);
    }

    @Override
    //When the device is rotated, save the data from the widgets in the bundle
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("name", name.getText( ).toString( ));
        savedInstanceState.putString("DOB", DOB.getText( ).toString( ));
        savedInstanceState.putBoolean("playedT", playedT.isChecked( ) ? true : false);
        savedInstanceState.putBoolean("competition", competition.isChecked( ) ? true : false);
        savedInstanceState.putBoolean("recreation", recreation.isChecked( ) ? true : false);
        savedInstanceState.putBoolean("meetNewPeople", meetNewPeople.isChecked( ) ? true : false);

    }
}
