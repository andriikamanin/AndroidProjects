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
    final static int SIZE = 3;

    private ImageView[][] board = new ImageView[SIZE][SIZE]; // Матрица для игрового поля
    private int[][] gameState = new int[SIZE][SIZE]; // Матрица для состояния игры (-1: пусто, 1: X, 0: O)

    Boolean player = false; // false - O, true - X
    TextView txtRisultato;

    String nome1 = "Roberto";
    String nome2 = "Joanna";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Инициализация ImageView для игрового поля
        board[0][0] = findViewById(R.id.img1);
        board[0][1] = findViewById(R.id.img2);
        board[0][2] = findViewById(R.id.img3);
        board[1][0] = findViewById(R.id.img4);
        board[1][1] = findViewById(R.id.img5);
        board[1][2] = findViewById(R.id.img6);
        board[2][0] = findViewById(R.id.img7);
        board[2][1] = findViewById(R.id.img8);
        board[2][2] = findViewById(R.id.img9);

        // Инициализация состояния игры
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                gameState[i][j] = -1; // Изначально все клетки пусты
            }
        }

        txtRisultato = findViewById(R.id.txtRisultato);

        // Установка обработчиков нажатий
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final int row = i;
                final int col = j;
                board[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        makeMove(row, col);
                    }
                });
            }
        }
    }

    public void makeMove(int row, int col) {
        if(gameState[row][col] == -1) { // Если клетка пуста
            if(player) {
                board[row][col].setImageDrawable(getDrawable(R.drawable.mossa_x));
                gameState[row][col] = 1; // Ход X
            } else {
                board[row][col].setImageDrawable(getDrawable(R.drawable.mossa_o));
                gameState[row][col] = 0; // Ход O
            }

            if (checkWinner()) { // Проверка победителя после каждого хода
                String winner = player ? nome1 : nome2;
                txtRisultato.setText(winner + " победил!");
                disableBoard(); // Отключить поле после победы
            } else if (isBoardFull()) {
                txtRisultato.setText("Ничья!");
            } else {
                player = !player; // Смена игрока
                showCurrentPlayer();
            }
        }
    }

    public void showCurrentPlayer() {
        if(player) {
            txtRisultato.setText("Ходит " + nome1);
        } else {
            txtRisultato.setText("Ходит " + nome2);
        }
    }

    public boolean checkWinner() {
        // Проверка строк
        for (int i = 0; i < SIZE; i++) {
            if (gameState[i][0] != -1 && gameState[i][0] == gameState[i][1] && gameState[i][1] == gameState[i][2]) {
                return true;
            }
        }
        // Проверка столбцов
        for (int i = 0; i < SIZE; i++) {
            if (gameState[0][i] != -1 && gameState[0][i] == gameState[1][i] && gameState[1][i] == gameState[2][i]) {
                return true;
            }
        }
        // Проверка диагоналей
        if (gameState[0][0] != -1 && gameState[0][0] == gameState[1][1] && gameState[1][1] == gameState[2][2]) {
            return true;
        }
        if (gameState[0][2] != -1 && gameState[0][2] == gameState[1][1] && gameState[1][1] == gameState[2][0]) {
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (gameState[i][j] == -1) {
                    return false;
                }
            }
        }
        return true;
    }

    public void disableBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j].setEnabled(false);
            }
        }
    }
}