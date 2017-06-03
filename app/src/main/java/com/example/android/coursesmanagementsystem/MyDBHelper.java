package com.example.android.coursesmanagementsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/5/28.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    public static final String CREATE_ROSTER = "create table roster ("+
            "id integer primary key autoincrement,"+
            "name text," +
            "sno text," +
            "coursesinfo1602 text," +
            "coursesinfo1601 text)";
    public static final String NAME = "name";
    public static final String SNO = "sno";
    public static final String COURSESINFO1602 = "coursesinfo1602";
    public static final String ROSTER = "roster";
    private Context mContext;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_ROSTER);
        //Log.i("MyDBHelper","create successful!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
