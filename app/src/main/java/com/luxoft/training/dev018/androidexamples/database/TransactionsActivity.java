package com.luxoft.training.dev018.androidexamples.database;

import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

import com.luxoft.training.dev018.androidexamples.R;

public class TransactionsActivity extends AppCompatActivity {
    final String TAG = "TransactionsActivity";

    DBHelper dbh;
    SQLiteDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_transactions);
        Log.d(TAG, "--- onCreate Activity ---");
        dbh = new DBHelper(this);
        myActions();
    }

    void myActions() {
        db = dbh.getWritableDatabase();
        delete(db, "mytable");
        insert(db, "mytable", "val1");
        read(db, "mytable");
        dbh.close();
    }

//    void myActions() {
//        db = dbh.getWritableDatabase();
//        delete(db, "mytable");
//        db.beginTransaction();
//        insert(db, "mytable", "val1");
//        db.endTransaction();
//        insert(db, "mytable", "val2");
//        read(db, "mytable");
//        dbh.close();
//    }

//    void myActions() {
//        db = dbh.getWritableDatabase();
//        delete(db, "mytable");
//        db.beginTransaction();
//        insert(db, "mytable", "val1");
//        db.setTransactionSuccessful();
//        insert(db, "mytable", "val2");
//        db.endTransaction();
//        insert(db, "mytable", "val3");
//        read(db, "mytable");
//        dbh.close();
//    }

//    void myActions() {
//        try {
//            db = dbh.getWritableDatabase();
//            delete(db, "mytable");
//
//            db.beginTransaction();
//            insert(db, "mytable", "val1");
//
//            Log.d(TAG, "create DBHelper");
//            DBHelper dbh2 = new DBHelper(this);
//            Log.d(TAG, "get db");
//            SQLiteDatabase db2 = dbh2.getWritableDatabase();
//            read(db2, "mytable");
//            dbh2.close();
//
//            db.setTransactionSuccessful();
//            db.endTransaction();
//
//            read(db, "mytable");
//            dbh.close();
//
//        } catch (Exception ex) {
//            Log.d(TAG, ex.getClass() + " error: " + ex.getMessage());
//        }
//    }

    void insert(SQLiteDatabase db, String table, String value) {
        Log.d(TAG, "Insert in table " + table + " value = " + value);
        ContentValues cv = new ContentValues();
        cv.put("val", value);
        db.insert(table, null, cv);
    }

    void read(SQLiteDatabase db, String table) {
        Log.d(TAG, "Read table " + table);
        Cursor c = db.query(table, null, null, null, null, null, null);
        if (c != null) {
            Log.d(TAG, "Records count = " + c.getCount());
            if (c.moveToFirst()) {
                do {
                    Log.d(TAG, c.getString(c.getColumnIndex("val")));
                } while (c.moveToNext());
            }
            c.close();
        }
    }

    void delete(SQLiteDatabase db, String table) {
        Log.d(TAG, "Delete all from table " + table);
        db.delete(table, null, null);
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, "transactionsDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db) {
            Log.d(TAG, "--- onCreate database ---");

            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "val text"
                    + ");");
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
