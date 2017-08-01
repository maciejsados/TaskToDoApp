package com.example.maciej.tasktodoapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class TaskDbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "task_data.db";
    public static final int VERSION = 1;
    public static final String CREATE_TASK_SQL_TABLE = "create table "
            + TaskContract.TaskEntry.TABLE_NAME + "("
            + TaskContract.TaskEntry._ID + " integer primary key autoincrement,"
            + TaskContract.TaskEntry.TASK_COLUMN_NAME + " string)";
    public static final String DROP_IP_INFO_SQL_TABLE = " drop table if exists "
            + TaskContract.TaskEntry.TABLE_NAME;


    public TaskDbHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TASK_SQL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_IP_INFO_SQL_TABLE);
        onCreate(sqLiteDatabase);

    }
}
