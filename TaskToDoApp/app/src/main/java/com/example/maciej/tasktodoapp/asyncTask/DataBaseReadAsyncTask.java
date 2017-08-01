package com.example.maciej.tasktodoapp.asyncTask;

import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.maciej.tasktodoapp.db.TaskContract;
import com.example.maciej.tasktodoapp.db.TaskDbHelper;


import java.util.ArrayList;
import java.util.List;

public class DataBaseReadAsyncTask extends AsyncTask<Void, Void, List<String>> {

    private final Context context;
    private final SQLiteDatabase readData;
    private final ReadTaskListener readTaskListener;

    public DataBaseReadAsyncTask(Context context, ReadTaskListener readTaskListener) {
        this.context = context;
        TaskDbHelper dbHelper = new TaskDbHelper(context);
        readData = dbHelper.getReadableDatabase();
        this.readTaskListener = readTaskListener;
    }


    @Override
    protected List<String> doInBackground(Void... voids) {

        Cursor cursor = readData.query(TaskContract.TaskEntry.TABLE_NAME, null, null, null, null, null, null);
        List<String> results = new ArrayList<>();

        while (cursor.moveToNext()) {
            String taskName = cursor.getString(1);

            results.add(taskName);
        }

        return results;
    }

    @Override
    protected void onPostExecute(List<String> results) {
        readTaskListener.GetTask(results);
    }

    public interface ReadTaskListener {
        void GetTask(List<String> results);
    }
}
