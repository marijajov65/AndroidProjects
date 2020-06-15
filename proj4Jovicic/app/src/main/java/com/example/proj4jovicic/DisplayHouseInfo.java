package com.example.proj4jovicic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayHouseInfo extends AppCompatActivity {

    private TextView idView;
    private TextView addressView;
    private TextView cityView;
    private TextView bedroomsView;
    private TextView bathroomsView;
    private TextView priceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_house_info);

        Intent intent = getIntent( );
        String info = intent.getStringExtra("info");

        if(info !=null){
            String[ ] fields = info.split(";");
            int id = Integer.parseInt(fields[0]);
            String address = fields[1];
            String city = fields[2];
            float bedrooms = Float.parseFloat(fields[3]);
            float bathrooms = Float.parseFloat(fields[4]);
            float price = Float.parseFloat(fields[5]);


            idView = findViewById(R.id.houseId);
            addressView = findViewById(R.id.address);
            cityView = findViewById(R.id.city);
            bedroomsView = findViewById(R.id.bedrooms);
            bathroomsView= findViewById(R.id.bathrooms);
            priceView= findViewById(R.id.price);

            idView.setText("House Id: " + id);
            addressView.setText("Address: " + address);
            cityView.setText("City : " + city);
            bedroomsView.setText("Bedrooms: " + bedrooms);
            bathroomsView.setText("Bathrooms: " + bathrooms);
            priceView.setText("Price: " + price);


        }

    }

    public void returnToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
