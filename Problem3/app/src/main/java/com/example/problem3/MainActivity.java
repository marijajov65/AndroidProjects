package com.example.problem3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get the widgets and store them in variables
        LinearLayout layout = findViewById(R.id.linear_layout);
        final EditText editText = new EditText((this));
        final TextView textView = new TextView(this);
        final Button button = new Button(this);

        button.setText("Convert");

        //Set text size
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);

        //Set the size of the linear layout
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(50, 50, 0, 0);
        textView.setLayoutParams(params);
        editText.setLayoutParams(params);
        button.setLayoutParams(params);

        //Add views/widgets to the linear layout
        layout.addView(editText);
        layout.addView(textView);
        layout.addView(button);

        //Anonymous class to handle conversion
        button.setOnClickListener(
                new View.OnClickListener( ) {
                    @Override
                    public void onClick(View view) {
                        //Get the value in meters
                        double number = Double.parseDouble(editText.getText().toString());
                        int feet = 0;
                        double inches = 0;

                        //Create a decimal format for inches
                        DecimalFormat df = new DecimalFormat("#.##");

                        //Calculate feet and inches
                        feet = (int)(number*39.3700787/12);
                        inches = ((number*39.3700787/12) - feet)*12;

                        //Display result of conversion
                        String result = String.valueOf(feet) + "' " + String.valueOf(df.format(inches)) +"\"\"";
                        textView.setText(result);
                    }
                });
    }




}
