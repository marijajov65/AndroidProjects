package com.example.proj4jovicic;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import android.view.MotionEvent;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;



public class MyView extends View {
    private InputStream is;
    private InputStream isHouses;
    private InputStream isHouses2;
    private Scanner s;
    private Scanner scannerHouses;
    private Scanner scannerHousesDraw;
    private ArrayList<House> housesData;
    final private Context savedContext;

    public MyView(Context context) {
        super(context);
        savedContext = context;

        is =  getResources( ).openRawResource(R.raw.rivers);
        isHouses = getResources( ).openRawResource(R.raw.houses);
        s  = new Scanner(is);
        scannerHouses = new Scanner(isHouses);
        isHouses2 = getResources( ).openRawResource(R.raw.houses);
        scannerHousesDraw = new Scanner(isHouses2);


        housesData = new ArrayList<House>();
        scannerHouses.nextLine();


        while(scannerHouses.hasNextLine()){
            String[] fields = scannerHouses.nextLine().split(",");

            int id = Integer.parseInt(fields[0]);
            float x = Float.parseFloat(fields[1]);
            float y = Float.parseFloat(fields[2]);
            String address = fields[3];
            String city = fields[4];
            float bedrooms = Float.parseFloat(fields[5]);
            float bathrooms = Float.parseFloat(fields[6]);
            float price = Float.parseFloat(fields[7]);

            House house = new House(id,x,y,address,city,bedrooms,bathrooms,price);
            housesData.add(house);
        }
        scannerHouses.close();



        this.setOnTouchListener(new View.OnTouchListener( ) {


            @Override
            public boolean onTouch(View v, MotionEvent e) {
                int foundId = 0;
                String foundAddress = "";
                String foundCity = "";
                float bedrooms = 0;
                float bathrooms = 0;
                float price = 0;

                String info = null;
                if (e.getAction() == MotionEvent.ACTION_UP) {

                    for (House h : housesData) {
                        if (Math.abs(e.getX() - 20.0f * h.x) < 20.0f &&
                                Math.abs(e.getY() - 20.0f * h.y) < 20.0f) {
                            foundId = h.id;
                            foundAddress = h.address;
                            foundCity = h.city;
                            bedrooms = h.bedrooms;
                            bathrooms = h.bathrooms;
                            price = h.price;

                            info = String.format("%s;%s;%s;%s;%s;%s",
                                    foundId, foundAddress, foundCity, bedrooms, bathrooms, price);

                        }
                    }
                }
                Intent intent = new Intent(savedContext.getApplicationContext(), DisplayHouseInfo.class);
                intent.putExtra("info", info);
                savedContext.startActivity(intent);
                return true;

            }
        });
    }



    @Override
    public void onDraw(Canvas canvas) {
        float prevx = 0.0f;
        float prevy = 0.0f;
        Paint paint = new Paint( );
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(3.0f);

        s.nextLine();

        while(s.hasNextLine( )) {
            String line = s.nextLine( );
            String[ ] fields = line.split(",");
            float x = 30.0f * Float.parseFloat(fields[0]);
            float y = 30.0f * Float.parseFloat(fields[1]);
            int startPoint = Integer.parseInt(fields[2]);
            if (startPoint == 0) {
                canvas.drawLine(prevx, prevy, x, y, paint);
            }
            prevx = x;
            prevy = y;
        }
        s.close( );

        paint.setColor(Color.RED);
        scannerHousesDraw.nextLine( );
        while(scannerHousesDraw.hasNextLine( )) {
            String line = scannerHousesDraw.nextLine( );
            String[ ] fields = line.split(",");
            float x = Float.parseFloat(fields[1]);
            float y = Float.parseFloat(fields[2]);
            System.out.println(x +" "+ y);
            canvas.drawCircle(20.0f * x,
                    20.0f * y, 10.0f, paint);
        }
        scannerHousesDraw.close();




    }
}