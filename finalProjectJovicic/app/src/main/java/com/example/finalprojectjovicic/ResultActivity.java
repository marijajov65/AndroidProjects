package com.example.finalprojectjovicic;

/*
Marija Jovicic
Final Project
6/6/2020
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    public static final String DATA = "message";

    private TextView fname;
    private TextView lname;
    private TextView emailWidg;
    private TextView ageWidg;
    private TextView genderWidg;
    private TextView recommendedA;

    private String recommended;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        fname = findViewById(R.id.firstName);
        lname = findViewById(R.id.lastName);
        emailWidg = findViewById(R.id.emailAddress);
        ageWidg = findViewById(R.id.age);
        genderWidg = findViewById(R.id.gender);
        recommendedA = findViewById(R.id.rActivities);

        //Get the data fromm the bundle and change the widget text
        if (savedInstanceState != null) {
            fname.setText(savedInstanceState.getString("firstname"));
            lname.setText(savedInstanceState.getString("lastname"));
            ageWidg.setText(savedInstanceState.getString("age"));
            genderWidg.setText(savedInstanceState.getString("gender"));
            emailWidg.setText(savedInstanceState.getString("email"));
            recommendedA.setText(savedInstanceState.getString("recommendations"));
        }




        SQLiteOpenHelper dbh = new ProfilesDb(this);
        SQLiteDatabase db = dbh.getReadableDatabase();


        //Query the profiles table to get all the values that the user entered
        Cursor cursor = db.query("profiles",
                new String[]{"firstName", "lastName", "email", "age", "gender", "willingToSpend", "outdoors", "usedBored"},
                null, null, null, null, null);

        if (cursor.moveToLast()) {
            String firstname = cursor.getString(0);
            String lastname = cursor.getString(1);
            String email = cursor.getString(2);
            String age = cursor.getString(3);
            String gender = cursor.getString(4);


            fname.setText("First Name: " + firstname);
            lname.setText("Last Name: "+ lastname);
            emailWidg.setText("Email: "+ email);
            ageWidg.setText("Age: " + age);
            genderWidg.setText("Gender: " + gender);


            recommended = "";

            String willingToSpend = cursor.getString(5);
            String outdoors = cursor.getString(6);

            //Query the activities table to find activities that correspond to user's criteria
            Cursor c = db.rawQuery("SELECT * FROM activities WHERE money = '" +willingToSpend+ "' AND outdoors = '" +outdoors+ "'", null);

            String activities = "";

            //Create a String with all the recommended activities and display it
            int index = 1;
            while (c.moveToNext()) {
                activities = activities + "    " + index + "." +(c.getString(0)) + "\n";
                index++;
            }
            c.close();
            recommendedA.setText("Recommended Activities: \n"+ activities);

        }
    }
    //Save the widget values in the Bundle
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("firstname", fname.getText( ).toString( ));
        savedInstanceState.putString("lastname", lname.getText( ).toString( ));
        savedInstanceState.putString("age", ageWidg.getText( ).toString( ));
        savedInstanceState.putString("gender", genderWidg.getText( ).toString( ));
        savedInstanceState.putString("email", emailWidg.getText( ).toString( ));
        savedInstanceState.putString("recommendations", recommendedA.getText( ).toString( ));

    }

}

