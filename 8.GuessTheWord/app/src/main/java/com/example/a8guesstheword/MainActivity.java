package com.example.a8guesstheword;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView numeroTentativi;
    private TextView parolaNascosta;
    private EditText campoDiImmissione;
    private Button buttonProva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        numeroTentativi   =     findViewById(R.id.numeroTentativi);
        parolaNascosta    =     findViewById(R.id.parolaNascosta);
        campoDiImmissione =     findViewById(R.id.campoDiImmissione);
        buttonProva       =     findViewById(R.id.buttonProva);

    }
}