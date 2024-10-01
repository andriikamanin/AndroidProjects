package com.example.application;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button goToTria;
    private Button goToMaps;
    private Button goToListaContatti;
    private Button goToHangman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        goToTria = findViewById(R.id.goToTria);
        goToMaps = findViewById(R.id.goToMaps);
        goToListaContatti = findViewById(R.id.goToListaContatti);
        goToHangman = findViewById(R.id.goToHangman);

        goToTria.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, TriaActivity.class);
            startActivity(i);
        });
        goToMaps.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(i);
        });

        goToListaContatti.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, ListaContattiActivity.class);
            startActivity(i);
        });

        goToHangman.setOnClickListener(v->{
            Intent i = new Intent(MainActivity.this, HangmanActivity.class);
            startActivity(i);
        });



    }
}