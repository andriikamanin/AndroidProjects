package com.example.a8guesstheword;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private String parolaDaIndovinare = "parola";
    private String statoCorrenteParola;
    private int numeroDiTentativi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        numeroTentativi   =     findViewById(R.id.numeroTentativi);
        parolaNascosta    =     findViewById(R.id.parolaNascosta);
        campoDiImmissione =     findViewById(R.id.campoDiImmissione);
        buttonProva       =     findViewById(R.id.buttonProva);

        inizializzaGioco();

        buttonProva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = campoDiImmissione.getText().toString().trim();
                if (!input.isEmpty()) {
                    controllaTentativo(input);
                    aggiornaNumeroTentativi();
                }
            }
        });
    }

    private void inizializzaGioco() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parolaDaIndovinare.length(); i++) {
            sb.append("_");
        }
        statoCorrenteParola = sb.toString();
        parolaNascosta.setText(statoCorrenteParola);
    }

    private void controllaTentativo(String input) {
        if (input.equals(parolaDaIndovinare)) {
            parolaNascosta.setText(parolaDaIndovinare);
            mostraMessaggio("Вы угадали слово!");
        } else {
            aggiornaStatoParola(input);
        }
    }

    private void aggiornaStatoParola(String input) {
        StringBuilder sb = new StringBuilder(statoCorrenteParola);
        for (int i = 0; i < parolaDaIndovinare.length(); i++) {
            if (parolaDaIndovinare.charAt(i) == input.charAt(0)) {
                sb.setCharAt(i, input.charAt(0));
            }
        }
        statoCorrenteParola = sb.toString();
        parolaNascosta.setText(statoCorrenteParola);
    }

    private void aggiornaNumeroTentativi() {
        numeroDiTentativi++;
        numeroTentativi.setText("Tentativi: " + numeroDiTentativi);
    }

    private void mostraMessaggio(String messaggio) {
        Toast.makeText(this, messaggio, Toast.LENGTH_SHORT).show();
    }
}
