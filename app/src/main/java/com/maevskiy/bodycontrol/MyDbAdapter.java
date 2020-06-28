package com.maevskiy.bodycontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDbAdapter {
    private myDbHelper myhelper;

    public MyDbAdapter(Context context) {
        myhelper = new myDbHelper(context);
        insertData("Beef", 180);
        insertData("Carrot_juice", 70);
        insertData("Caviar", 250);
        insertData("Chicken", 150);
        insertData("Duck", 400);
        insertData("Oat_flake", 70);
        insertData("Pink_salmon", 150);
        insertData("Veal", 200);
        insertData("Pork", 220);
    }

    public void insertData(String name, int calories) {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.CALORIES, calories);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
        System.out.println("inserted " + name + ", id = " + id);
    }

    public List<Food> getData() {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.NAME, myDbHelper.CALORIES};
        Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
        List<Food> foods = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            int calories = cursor.getInt(cursor.getColumnIndex(myDbHelper.CALORIES));
            foods.add(new Food(name, calories));
        }
        return foods;
    }

    public int delete(String uname) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs = {uname};

        int count = db.delete(myDbHelper.TABLE_NAME, myDbHelper.NAME + " = ?", whereArgs);
        return count;
    }

    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, newName);
        String[] whereArgs = {oldName};
        int count = db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.NAME + " = ?", whereArgs);
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "myDatabase2";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;    // Database Version
        private static final String NAME = "name";    //Column II
        private static final String CALORIES = "calories";    // Column III
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                + " (" + NAME + " VARCHAR(255) PRIMARY KEY," + CALORIES + " INT);";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context = context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                Message.message(context, "OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (Exception e) {
                Message.message(context, "" + e);
            }
        }
    }
}