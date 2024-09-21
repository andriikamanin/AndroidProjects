package com.example.a3todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.a3todolist.TodoDatabaseHelper; // Замените на актуальный пакет

import java.util.ArrayList;
import java.util.List;

public class TodoListActivity extends AppCompatActivity {

    private ListView listViewTasks;
    private EditText editTextTask;
    private Button buttonAddTask;
    private TodoDatabaseHelper todoDatabaseHelper;
    private List<String> taskList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);

        listViewTasks = findViewById(R.id.listViewTasks);
        editTextTask = findViewById(R.id.editTextTask);
        buttonAddTask = findViewById(R.id.buttonAddTask);
        todoDatabaseHelper = new TodoDatabaseHelper(this);
        taskList = new ArrayList<>();

        loadTasks();

        buttonAddTask.setOnClickListener(v -> addTask());

        listViewTasks.setOnItemClickListener((parent, view, position, id) -> {
            String selectedTask = taskList.get(position);
            todoDatabaseHelper.deleteTask(selectedTask);
            loadTasks();
            Toast.makeText(TodoListActivity.this, "Task deleted", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadTasks() {
        taskList.clear();
        taskList.addAll(todoDatabaseHelper.getAllTasks());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter);
    }

    private void addTask() {
        String task = editTextTask.getText().toString().trim();
        if (!task.isEmpty()) {
            todoDatabaseHelper.addTask(task);
            editTextTask.setText("");
            loadTasks();
            Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please enter a task", Toast.LENGTH_SHORT).show();
        }
    }
}
