package com.example.a5transitionbetweenactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private Button goToBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        goToBack = findViewById(R.id.btnGoToFirstActivity);

        goToBack.setOnClickListener(v->{
            Intent intent = new Intent(SecondActivity.this,FirstActivity.class);
            startActivity(intent);
        });
    }

}
