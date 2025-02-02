package com.example.uas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "uas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE Hewan (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +  // Kolom id dengan auto-increment
                "jenis TEXT, " +                            // Kolom jenis
                "kaki INTEGER)";                            // Kolom kaki
        db.execSQL(query);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addData(String jenis, String kaki){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("kaki", kaki);
        cv.put("jenis", jenis);
        long status = db.insert("Hewan", null, cv);
        if(status == -1) return false;
        return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM Hewan";
        Cursor c = db.rawQuery(query, null);
        return c;
    }
}
