package com.example.myfirstapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private String currentExpression = "";  // Переменная для хранения текущего выражения
    private Button zero, one, two, three, four, five, six, seven, eight, nine;
    private Button plus, minus, multiply, divide, equals;
    private TextView resultOfSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация кнопок
        zero = findViewById(R.id.buttonZero);
        one = findViewById(R.id.buttonOne);
        two = findViewById(R.id.buttonTwo);
        three = findViewById(R.id.buttonThree);
        four = findViewById(R.id.buttonFour);
        five = findViewById(R.id.buttonFive);
        six = findViewById(R.id.buttonSix);
        seven = findViewById(R.id.buttonSeven);
        eight = findViewById(R.id.buttonEight);
        nine = findViewById(R.id.buttonNine);

        plus = findViewById(R.id.buttonAddition);
        minus = findViewById(R.id.buttonSubtraction);
        /*multiply = findViewById(R.id.buttonMultiply);
        divide = findViewById(R.id.buttonDivide);*/
        equals = findViewById(R.id.buttonEquals);

        // Поле для вывода результата
        resultOfSum = findViewById(R.id.textResult);

        // Обработчики нажатий на кнопки с цифрами
        zero.setOnClickListener(view -> updateExpression("0"));
        one.setOnClickListener(view -> updateExpression("1"));
        two.setOnClickListener(view -> updateExpression("2"));
        three.setOnClickListener(view -> updateExpression("3"));
        four.setOnClickListener(view -> updateExpression("4"));
        five.setOnClickListener(view -> updateExpression("5"));
        six.setOnClickListener(view -> updateExpression("6"));
        seven.setOnClickListener(view -> updateExpression("7"));
        eight.setOnClickListener(view -> updateExpression("8"));
        nine.setOnClickListener(view -> updateExpression("9"));

        // Обработчики нажатий на кнопки операций
        plus.setOnClickListener(view -> updateExpression("+"));
        minus.setOnClickListener(view -> updateExpression("-"));
        /*multiply.setOnClickListener(view -> updateExpression("*"));
        divide.setOnClickListener(view -> updateExpression("/"));*/

        // Обработчик для кнопки "равно"
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Вычисляем результат выражения
                    double result = evaluateExpression(currentExpression);
                    resultOfSum.setText(String.valueOf(result));  // Показываем результат
                    currentExpression = String.valueOf(result);  // Сохраняем результат для дальнейших операций
                } catch (Exception e) {
                    resultOfSum.setText("Error");
                    currentExpression = "";  // Сбрасываем выражение в случае ошибки
                }
            }
        });
    }

    // Функция для обновления выражения на экране
    private void updateExpression(String value) {
        currentExpression += value;  // Добавляем значение к текущему выражению
        resultOfSum.setText(currentExpression);  // Обновляем текст на экране
    }

    // Функция для вычисления выражения
    private double evaluateExpression(String expression) {
        // Используем exp4j для вычисления математического выражения
        Expression exp = new ExpressionBuilder(expression).build();
        return exp.evaluate();
    }
}