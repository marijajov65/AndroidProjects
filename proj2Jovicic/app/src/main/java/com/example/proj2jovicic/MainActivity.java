package com.example.proj2jovicic;
/*
Marija Jovicic
Project 2
4/23/2020
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Activated when the "Calculate" button is pressed
    public void getMonthlyPayment(View view) {
        //Get all the EditText ids
        EditText principal = findViewById(R.id.principal);
        EditText interestRate = findViewById(R.id.interestRate);
        EditText loanTerm = findViewById(R.id.loanTerm);

        //Get the values for principal, MIR and the term of the loan
        double p = Double.parseDouble(principal.getText().toString());
        double r = Double.parseDouble(interestRate.getText().toString());
        double n = Double.parseDouble(loanTerm.getText().toString());


        //Calculate the monthly payment and round the number to 2 decimal points
        TextView display = findViewById(R.id.display);
        double monthlyPayment;
        DecimalFormat df = new DecimalFormat("#.##");
        monthlyPayment = (p*r/1200.0)/(1-Math.pow((1.0+r/1200.0),(-12*n)));

        //Display the number in TextView
        display.setText(String.valueOf(df.format(monthlyPayment)));


    }
}
