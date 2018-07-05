package com.example.tianlong.yukaolianxi.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteOpen extends SQLiteOpenHelper{
    public SqliteOpen(Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table user (id integer primary key autoincrement,"+
                        "name text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
