package com.example.test.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.test.models.City;
import com.example.test.models.GoiY;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {
    private static final String DBname = "dbcity.db";
    private static final int VERSION = 1;
    private static final String TABLE_NAME = "Place";
    private static final String TABLE_NAME_GOIY = "Suggestions";
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String WEATHER = "weather";
    private static final String COUNTRY = "country";
    private static final String GOIY = "goiy";
    private static final String LAT = "lat";
    private static final String LNG = "lng";
    private SQLiteDatabase myDB;

    public DBhelper(@Nullable Context context) {
        super(context, DBname, null, VERSION);
    }

    public static String getID() {
        return ID;
    }

    public static String getCOUNTRY() {
        return COUNTRY;
    }

    public static String getNAME() {
        return NAME;
    }

    public static String getLAT() {
        return LAT;
    }

    public static String getLNG() {
        return LNG;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryTB = "CREATE TABLE " + TABLE_NAME + "( " +
                ID + " INTEGER PRIMARY KEY, " +
                NAME + " TEXT NOT NULL, " +
                COUNTRY + " TEXT NOT NULL, " +
                LAT + " TEXT NOT NULL, " +
                LNG + " TEXT NOT NULL )";
        db.execSQL(queryTB);

        queryTB = "CREATE TABLE " + TABLE_NAME_GOIY + "( " +
                ID + " INTEGER PRIMARY KEY, " +
                WEATHER + " TEXT NOT NULL, " +
                GOIY + " TEXT NOT NULL )";
        db.execSQL(queryTB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDB() {
        myDB = getWritableDatabase();
    }

    public void closeDB() {
        if (myDB != null && myDB.isOpen()) {
            myDB.close();
        }
    }

    public long Insert(int id, String name, String country , String lat, String lng) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(COUNTRY, country);
        contentValues.put(LAT, lat);
        contentValues.put(LNG, lng);
        return myDB.insert(TABLE_NAME, null, contentValues);
    }

    public long InsertGoiY(int id, String wt, String goiy) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(WEATHER, wt);
        contentValues.put(GOIY, goiy);
        return myDB.insert(TABLE_NAME_GOIY, null, contentValues);
    }

    public long Update(int id, String name, String country , String lat, String lng) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NAME, name);
        contentValues.put(COUNTRY, country);
        contentValues.put(LAT, lat);
        contentValues.put(LNG, lng);
        String where = ID + " = " + id;
        return myDB.update(TABLE_NAME, contentValues, where, null);
    }

    public long Delete(int ID1) {
        String where = ID +" = "+ID1;
        return myDB.delete(TABLE_NAME, where, null);
    }


    public Cursor getAllRecord() {
        String query = "SELECT * FROM " + TABLE_NAME_GOIY;
        return myDB.rawQuery(query, null);
    }

    public Cursor getGoiY() {
        String query = "SELECT "+GOIY+" FROM " + TABLE_NAME_GOIY;
        return myDB.rawQuery(query, null);
    }

    public ArrayList<City> getAllWords() {
        ArrayList<City> wordList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                City word = new City();
               word.setID(cursor.getInt(0));
               word.setCity_Name(cursor.getString(1));
               word.setCountry(cursor.getString(2));
               word.setLat(cursor.getString(3));// vi do
               word.setLng(cursor.getString(4));
                // Thêm vào danh sách.
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        // return note list
        return wordList;
    }
    public ArrayList<GoiY> getAllNotes() {
        ArrayList<GoiY> wordList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_GOIY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                GoiY word = new GoiY();
                word.setID(cursor.getInt(0));
                word.setWeather(cursor.getString(1));
                word.setGoiY(cursor.getString(2));
                // Thêm vào danh sách.
                wordList.add(word);
            } while (cursor.moveToNext());
        }
        // return note list
        return wordList;
    }
}
