package com.example.food_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "asma.db";
    private static final String TABLE_NAME = "quiz_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "TITLE";
    private static final String COL_3 = "QUESTION";
    private static final String COL_4 = "OPTION1";
    private static final String COL_5 = "OPTION2";
    private static final String COL_6 = "OPTION3";
    private static final String COL_7 = "IMAGE_RESOURCE_ID";



    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, QUESTION TEXT, OPTION1 TEXT, OPTION2 TEXT, OPTION3 TEXT, IMAGE_RESOURCE_ID INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public void addQuiz(String title, String question, String option1, String option2, String option3, int imageResourceId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, title);
        contentValues.put(COL_3, question);
        contentValues.put(COL_4, option1);
        contentValues.put(COL_5, option2);
        contentValues.put(COL_6, option3);
        contentValues.put(COL_7, imageResourceId);

        long result = db.insert(TABLE_NAME, null, contentValues);


        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();        }
        else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void updateData(String row_id, String title, String question, String option1, String option2, String option3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, question);
        contentValues.put(COL_4, option1);
        contentValues.put(COL_5, option2);
        contentValues.put(COL_6, option3);

        long result = db.update(TABLE_NAME, contentValues, "ID=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }



    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }
    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "ID=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }


    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
