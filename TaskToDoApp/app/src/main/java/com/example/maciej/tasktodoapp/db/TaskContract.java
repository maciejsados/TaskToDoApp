package com.example.maciej.tasktodoapp.db;

import android.provider.BaseColumns;


public class TaskContract {

    public static class TaskEntry implements BaseColumns {

        public static final String TABLE_NAME = "taks_to_do";
        public static final String TASK_COLUMN_NAME = "task";
    }
}
