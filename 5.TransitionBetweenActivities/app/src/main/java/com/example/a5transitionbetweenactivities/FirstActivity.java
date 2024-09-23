package com.example.a5transitionbetweenactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FirstActivity extends AppCompatActivity {

    private Button goToNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);  // Используй правильный layout для этого Activity


        goToNext = findViewById(R.id.btnGoToSecondActivity);


        goToNext.setOnClickListener(v -> {

            Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
            startActivity(intent);
        });
    }
}

