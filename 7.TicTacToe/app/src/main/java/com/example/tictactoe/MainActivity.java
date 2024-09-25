package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    final static int WIDTH = 3;
    final static int HEIGHT = 3;

    private ImageView img1, img2,img3,img4,img5, img6,img7, img8,img9;
    Boolean player = false;

    private ArrayList<String> tria = new ArrayList<String>();

    TextView txtRisultato;

    String nome1 = "Roberto";
    String nome2 = "Joanna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);


        for(int i = 0; i<WIDTH*HEIGHT; i++){
            tria.add("-1");
        }

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(0);
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(1);
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(2);
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(3);
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(4);
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(5);
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(6);
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(7);
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mossa(8);
            }
        });
    }
    public void mossa(int m) {
        if(tria.get(m).equals("-1")) {
            if(player) {
                switch (m) {
                    case 0: img1.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                    case 1: img2.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                    case 2: img3.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                    case 3: img4.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                    case 4: img5.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                    case 5: img6.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                    case 6: img7.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                    case 7: img8.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                    case 8: img9.setImageDrawable(getDrawable(R.drawable.mossa_x)); break;
                }
                tria.set(m,"1");
            }
            else {
                switch (m) {
                    case 0:
                        img1.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                    case 1:
                        img2.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                    case 2:
                        img3.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                    case 3:
                        img4.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                    case 4:
                        img5.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                    case 5:
                        img6.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                    case 6:
                        img7.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                    case 7:
                        img8.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                    case 8:
                        img9.setImageDrawable(getDrawable(R.drawable.mossa_o));
                        break;
                }
                tria.set(m, "0");
            }
            player = !player;
            mostraGiocatore();
        }
    }

    public void mostraGiocatore() {
        if(player) {
            txtRisultato.setText("Gioca " + nome1);
        }
        else {
            txtRisultato.setText("Gioca " + nome2);
        }
    }
}