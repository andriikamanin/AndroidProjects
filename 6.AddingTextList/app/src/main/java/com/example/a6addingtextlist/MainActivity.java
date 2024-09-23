package com.example.a6addingtextlist;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Button add;
    private EditText input;
    private ListView dataList;

    private ArrayList<String> list;
    private ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        add = findViewById(R.id.button);
        input = findViewById(R.id.editTextInput);
        dataList = findViewById((R.id.list));

        list = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

        dataList.setAdapter(arrayAdapter);

        add.setOnClickListener(v->{
            String inputText = input.getText().toString();
            if(!inputText.isEmpty()){
                list.add(inputText);
                arrayAdapter.notifyDataSetChanged();
                input.setText("");
            }
        });



    }
}