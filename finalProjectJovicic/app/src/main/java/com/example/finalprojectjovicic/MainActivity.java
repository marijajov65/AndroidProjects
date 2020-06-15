package com.example.finalprojectjovicic;

/*
Marija Jovicic
Final Project
6/6/2020
 */

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    EditText firstName, lastName, Age, Gender, Email;
    Spinner spinnerWidg;
    CheckBox Checkbox;
    RadioGroup Rbutton;
    RadioButton selectedRButton;
    Button Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create Spinner values
        spinnerWidg = findViewById(R.id.spinner);
        String[] options = new String[]{"<$10", ">$10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        spinnerWidg.setAdapter(adapter);


        //Create the database
        SQLiteOpenHelper dbh = new ProfilesDb(this);
        try {
            db = dbh.getWritableDatabase();
        }
        catch(SQLiteException e) {
            Toast toast = Toast.makeText(this,
                    "Database not created.", Toast.LENGTH_LONG);
        }


        //Initialize widgets
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        Age = findViewById(R.id.profileAge);
        Gender = findViewById(R.id.profileGender);
        Email = findViewById(R.id.emailAddress);
        Checkbox = findViewById(R.id.checkBox);
        spinnerWidg = findViewById(R.id.spinner);
        Rbutton = findViewById(R.id.radioGroup);
        Button = findViewById(R.id.button);


        //Get the data fromm the bundle and change the widget text
        if (savedInstanceState != null) {
            firstName.setText(savedInstanceState.getString("firstname"));
            lastName.setText(savedInstanceState.getString("lastname"));
            Age.setText(savedInstanceState.getString("age"));
            Gender.setText(savedInstanceState.getString("gender"));
            Email.setText(savedInstanceState.getString("email"));

            spinnerWidg.setSelection(savedInstanceState.getInt("spinner"));
            selectedRButton = (RadioButton) findViewById(savedInstanceState.getInt("spinner"));
            if(selectedRButton!=null) {
                selectedRButton.setChecked((true));
            }
            Checkbox.setChecked(savedInstanceState.getBoolean("usedBored"));

        }
    }

    //Perform this method after the user clicks on Submit button
    public void submit(View view) {

        String firstname, lastname, age, gender, email;
        String moneySpent, outdoors, usedApp;

        //Get teh values from widgets
        firstname = firstName.getText().toString();
        lastname = lastName.getText().toString();
        age = Age.getText().toString();
        gender = Gender.getText().toString();
        email = Email.getText().toString();
        moneySpent = spinnerWidg.getSelectedItem().toString();

        int selectedId = Rbutton.getCheckedRadioButtonId();
        selectedRButton = (RadioButton) findViewById(selectedId);
        if(selectedRButton!=null){
            outdoors = selectedRButton.getText().toString().toLowerCase();
        }else{
            outdoors = "yes";
        }
        usedApp = Checkbox.isChecked()?"yes":"no";


        //Create ContentValues object to format values for the database
        ContentValues activity = new ContentValues( );

        activity.put("firstname",firstname);
        activity.put("lastname",lastname);
        activity.put("age",age);
        activity.put("gender",gender);
        activity.put("email",email);
        activity.put("willingToSpend",moneySpent);
        activity.put("outdoors",outdoors);
        activity.put("usedBored",usedApp);

        //Insert user's info into the database
        db.insert("profiles", null, activity);

        Intent intent = new Intent(this, ResultActivity.class);

        // Start new activity
        startActivity(intent);
    }


    //Save the widget values in the Bundle
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("firstname", firstName.getText( ).toString( ));
        savedInstanceState.putString("lastname", lastName.getText( ).toString( ));
        savedInstanceState.putString("age", Age.getText( ).toString( ));
        savedInstanceState.putString("gender", Gender.getText( ).toString( ));
        savedInstanceState.putString("email", Email.getText( ).toString( ));
        savedInstanceState.putInt("spinner", spinnerWidg.getSelectedItemPosition());
        savedInstanceState.putInt("radioButton", Rbutton.getCheckedRadioButtonId());
        savedInstanceState.putBoolean("usedBored", Checkbox.isChecked( ) ? true : false);


    }
}
