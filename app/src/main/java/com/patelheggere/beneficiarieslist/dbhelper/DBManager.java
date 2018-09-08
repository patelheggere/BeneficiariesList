package com.patelheggere.beneficiarieslist.dbhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.patelheggere.beneficiarieslist.model.DetailsModel;

public class DBManager {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(DetailsModel object) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, object.getName());
        contentValue.put(DatabaseHelper.BENEFIT, object.getBenefit());
        contentValue.put(DatabaseHelper.ENTERED_NAME, object.getEnteredBy());
        contentValue.put(DatabaseHelper.ENTERED_MOBILE, object.getEnteredMobile());
        contentValue.put(DatabaseHelper.VILLAGE, object.getVillage());
        contentValue.put(DatabaseHelper.WARD, object.getWard());
        contentValue.put(DatabaseHelper.MOBILE, object.getMobile());
        contentValue.put(DatabaseHelper.EPIC, object.getEpic());

        System.out.println("Inserted:"+database.insert(DatabaseHelper.TABLE_NAME, null, contentValue));
    }

    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID,  DatabaseHelper.NAME };
        Cursor cursor;// = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        cursor = database.rawQuery("SELECT * FROM "+DatabaseHelper.TABLE_NAME, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
/*
    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }*/

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
}