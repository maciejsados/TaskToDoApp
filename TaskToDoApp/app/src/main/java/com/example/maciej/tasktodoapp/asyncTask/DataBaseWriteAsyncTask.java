package com.example.maciej.tasktodoapp.asyncTask;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.maciej.tasktodoapp.MainActivity;
import com.example.maciej.tasktodoapp.db.TaskContract;
import com.example.maciej.tasktodoapp.db.TaskDbHelper;


public class DataBaseWriteAsyncTask extends AsyncTask<String, Void, String> {

    private final Context context;
    private final SQLiteDatabase database;
    private final WriteTaskListener writeTaskListener;

    public DataBaseWriteAsyncTask(Context context, WriteTaskListener writeTaskListener) {
        this.context = context;
        TaskDbHelper dbHelper = new TaskDbHelper(context);
        database = dbHelper.getWritableDatabase();
        this.writeTaskListener = writeTaskListener;
    }


    @Override
    protected String doInBackground(String... strings) {

        String tasks = strings[0];

        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.TASK_COLUMN_NAME, tasks);
        if (tasks.isEmpty()) {
            return null;
        }
        database.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);

        return null;
    }


    @Override
    protected void onPostExecute(String string) {
        writeTaskListener.writeTask();

    }

    public interface WriteTaskListener {
        void writeTask();
    }
}
