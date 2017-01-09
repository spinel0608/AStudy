package com.samsung.astudy;

/**
 * Created by 심성보 on 2017-01-08.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

class myDailyWordDBManager extends SQLiteOpenHelper{

    public myDailyWordDBManager(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql="CREATE TABLE english "+
                "(num INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "word TEXT,mean TEXT);";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS english");
        onCreate(db);
    }
}
