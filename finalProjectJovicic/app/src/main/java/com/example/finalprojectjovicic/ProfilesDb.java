package com.example.finalprojectjovicic;

/*
Marija Jovicic
Final Project
6/6/2020
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.InputStream;
import java.util.Scanner;

public class ProfilesDb extends SQLiteOpenHelper {
    private static final String DB_NAME = "activities.db";
    private static final int DB_VERSION = 1;
    private Context savedContext;

    ProfilesDb(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        savedContext = context;
    }

    @Override
    // Call this method to create a database if it does not exist
    public void onCreate(SQLiteDatabase db) {
        //Create 2 tables - one for user input, one for activities from the activities.txt file
        db.execSQL("create table profiles(" +
                "firstName text, lastName text, email text, age text, gender text," +
                "willingToSpend text, outdoors text, usedBored text);");
        db.execSQL("create table activities(" +
                "activityName text, money text, outdoors text);");


        //Load the values from activities.txt file into the database
        InputStream input = savedContext.getResources().openRawResource(R.raw.activities);
        Scanner s = new Scanner(input);
        while (s.hasNextLine()) {

            String line = s.nextLine();
            String[] fields = line.split(",");
            String name = fields[0];
            name = name.trim();
            String money = fields[1];
            money = money.trim();
            String outdoors = fields[2];
            outdoors = outdoors.trim();

            ContentValues activity = new ContentValues();
            activity.put("money", money);
            activity.put("activityName", name);
            activity.put("outdoors", outdoors);

            db.insert("activities", null, activity);

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

    }
}

