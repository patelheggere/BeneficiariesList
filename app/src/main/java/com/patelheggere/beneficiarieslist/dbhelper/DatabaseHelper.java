package com.patelheggere.beneficiarieslist.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Table Name
    public static final String TABLE_NAME = "BENEFIT";

    // Table columns
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String ENTERED_NAME = "entered_name";
    public static final String ENTERED_MOBILE = "entered_mob";
    public static final String VILLAGE = "village";
    public static final String WARD = "ward";
    public static final String EPIC = "epic";
    public static final String DATE = "date";
    public static final String MOBILE = "mobile";
    public static final String BENEFIT = "benefit";
    // Database Information
    static final String DB_NAME = "BENEFIT.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
   /* private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT , " + CATEGORY + " TEXT , "+ SUB_CAT +" TEXT ,"
            +BUILD_NAME +" TEXT , "+BUILD_NUMBER+" TEXT ,"+NO_OF_FLOOR+" TEXT , "+BRAND+" TEXT , "+LAND_MARK+" TEXT, "
            +STREET+" TEXT , "+LOCALITY+" TEXT , "+PINCODE+" TEXT , "+COMMENT+" TEXT ,"+POI_NUMBER+" TEXT ,"+LAT+" TEXT , "+LON+" TEXT ,"+PHONE+" TEXT ,"+PERSON_NAME+" TEXT ,"+DATE+" );";
*/
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT , " + ENTERED_NAME + " TEXT , "+ ENTERED_MOBILE +" TEXT ,"
            +VILLAGE +" TEXT , "+WARD+" TEXT ,"+BENEFIT+" TEXT ,"+MOBILE+" TEXT ,"+EPIC+" TEXT ,"+DATE+" );";



    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
