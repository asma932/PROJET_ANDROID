package com.restaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class dbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "example.db";

    public static final String TABLE_NAME = "Example_table";
    public static final String TABLE2_NAME = "Example2_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_NUMBER = "NUMBER";
    public static final String COLUMN_ADDRESS = "ADDRESS";
    public static final String COLUMN_TELEPHONE = "TELEPHONE";
    public static final String COLUMN_QUANTITY = "QUANTITY";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_PRODUCT = "PRODUCT";
    public static final String COLUMN_IMG = "IMG";

    Context context;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Example_table
        String SQL_CREATE = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_NUMBER + " INTEGER, " +
                COLUMN_PRICE + " REAL, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_TELEPHONE + " TEXT, " +
                COLUMN_QUANTITY + " INTEGER, " +
                COLUMN_PRODUCT + " TEXT );";

        db.execSQL(SQL_CREATE);

        // Create Example2_table
        String SQL_CREATE_TABLE2 = "CREATE TABLE " + TABLE2_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_IMG + " TEXT, " +
                COLUMN_PRICE + " REAL, " +
                COLUMN_DATE + " TEXT );";

        db.execSQL(SQL_CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Log the version upgrade
        Log.d("Database Upgrade", "Upgrading database from version " + oldVersion + " to " + newVersion);

        // Upgrade steps for Example_table
        if (oldVersion < 2) {
            // Add specific upgrade steps for Example_table
            // For example, add a new column or create a new table related to Example_table
        }

        // Upgrade steps for Example2_table
        if (oldVersion < 3) {
            // Add specific upgrade steps for Example2_table
            // For example, add a new column or create a new table related to Example2_table
        }

        // Drop the existing tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);

        // Recreate the tables
        onCreate(db);
    }

    public void insertData(String name, int num, double price, String date, String address, String telephone, int quantity, String product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NUMBER, num);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_TELEPHONE, telephone);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_PRODUCT, product);

        long result = db.insert(TABLE_NAME, null, values);

        if (result == -1) {
            Toast.makeText(context, "Data not saved ", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, " Order added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertData2(String name, double price, String date, String img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_IMG, img);

        long result = db.insert(TABLE2_NAME, null, values);

        if (result == -1) {
            Toast.makeText(context, "Data not saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added to Panier successfully", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateData(String id, String name, int quantity, double price, String address, String telephone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_QUANTITY, quantity);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_ADDRESS, address);
        values.put(COLUMN_TELEPHONE, telephone);

        // Which row to update, based on the ID
        long result = db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{id});

        // To check if data was updated successfully
        if (result == -1) {
            Toast.makeText(context, "Data not Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Order Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // SQL Statement for Delete
        long result = db.delete(TABLE_NAME, "ID = ?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Data not Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Order Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }
    
    void deleteData2(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // SQL Statement for Delete
        long result = db.delete(TABLE2_NAME, "ID = ?", new String[]{id});

        if (result == -1) {
            Toast.makeText(context, "Data not Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "delete from Panier", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readData() {
        SQLiteDatabase db = this.getWritableDatabase();

        // SQL Statement
        String query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    
     public Cursor readData2() {
        SQLiteDatabase db = this.getWritableDatabase();

        // SQL Statement
        String query = "SELECT * FROM " + TABLE2_NAME;

        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public Cursor searchData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Which row to search, based on the ID
        String[] columns = new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_NUMBER};
        Cursor cursor = db.query(TABLE_NAME, columns, "ID = ?", new String[]{id},
                null, null, null, null);

        return cursor;
    }

    public void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
