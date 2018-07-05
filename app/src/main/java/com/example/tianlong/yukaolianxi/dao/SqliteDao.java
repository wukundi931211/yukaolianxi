package com.example.tianlong.yukaolianxi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.tianlong.yukaolianxi.utils.SqliteOpen;

public class SqliteDao {
    private SqliteOpen sqliteOpen;
    private SQLiteDatabase sqLiteDatabase ;

    public SqliteDao(Context context) {
        super();
        sqliteOpen = new SqliteOpen(context);
        sqLiteDatabase = sqliteOpen.getReadableDatabase();
    }

    public boolean add (String name){
        ContentValues values  = new ContentValues();
        values.put("name",name);
        long user = sqLiteDatabase.insert("user", null, values);
        if (user!=0){
            return true;
        }else {
            return false;
        }
    };
}
