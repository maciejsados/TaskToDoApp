package com.example.maciej.tasktodoapp;

;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.maciej.tasktodoapp.asyncTask.DataBaseReadAsyncTask;
import com.example.maciej.tasktodoapp.asyncTask.DataBaseWriteAsyncTask;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataBaseReadAsyncTask.ReadTaskListener, DataBaseWriteAsyncTask.WriteTaskListener {

    private EditText editText;
    private Button button;
    private ListView listView;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.type_task_editText);
        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.list_item);
        textView = (TextView) findViewById(R.id.task_textView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = editText.getText().toString();
                new DataBaseWriteAsyncTask(MainActivity.this, MainActivity.this).execute(s);
            }
        });
        refreshTask();
    }

    public void refreshTask() {
        new DataBaseReadAsyncTask(this, this).execute();
    }

    @Override
    public void GetTask(List<String> results) {
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, results);

        listView.setAdapter(adapter);
    }


    @Override
    public void writeTask() {


        String task = editText.getText().toString();
        textView.setText(task);

        refreshTask();
    }


}
