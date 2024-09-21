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
    private Button plus, minus, multiply, divide, equals, dot, delete, percent;
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
        multiply = findViewById(R.id.buttonMultiplicationOperation);
       divide = findViewById(R.id.buttonDivide);
        equals = findViewById(R.id.buttonEquals);
        dot = findViewById(R.id.buttonDot);
        delete = findViewById(R.id.buttonDelete);
        percent = findViewById(R.id.buttonPercent);
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
        multiply.setOnClickListener(view -> updateExpression("*"));
        divide.setOnClickListener(view -> updateExpression("/"));
        dot.setOnClickListener(view -> updateExpression("."));
        percent.setOnClickListener(view -> updateExpression("%"));


        // Обработчик для кнопки "равно"
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Создаем объект ExpressionBuilder для вычисления выражения
                    Expression expression = new ExpressionBuilder(currentExpression).build();

                    // Вычисляем результат
                    double result = expression.evaluate();

                    // Проверяем, является ли результат целым числом
                    if (result == (long) result) {
                        // Если результат целое число, выводим его без дробной части
                        resultOfSum.setText(String.valueOf((long) result));
                    } else {
                        // Если результат не целое число, выводим с дробной частью
                        resultOfSum.setText(String.valueOf(result));
                    }

                    // Сохраняем результат для дальнейших операций
                    currentExpression = String.valueOf(result);
                } catch (Exception e) {
                    resultOfSum.setText("Error");
                }
            }
        });

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    // Проверяем, что выражение не пустое
                    if (!currentExpression.isEmpty()) {
                        // Преобразуем текущее выражение в число
                        double value = Double.parseDouble(currentExpression);

                        // Делим значение на 100 для получения процента
                        double result = value / 100;

                        // Показываем результат
                        resultOfSum.setText(removeTrailingZeros(result));

                        // Сохраняем результат как текущее выражение
                        currentExpression = String.valueOf(result);
                    }
                } catch (Exception e) {
                    resultOfSum.setText("Error");
                }
            }
        });




        delete = findViewById(R.id.buttonDelete);

        // Обработчик нажатия для кнопки Delete
        delete.setOnClickListener(view -> {
            if (!currentExpression.isEmpty()) {
                // Убираем последний символ из currentExpression
                currentExpression = currentExpression.substring(0, currentExpression.length() -currentExpression.length() );
                // Обновляем отображение результата
                resultOfSum.setText(currentExpression);
            }
        });

    }



    // Функция для удаления лишних нулей (например, 15.0 станет 15)
    private String removeTrailingZeros(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            return String.format("%s", result);
        }
    }


    // Функция для обновления выражения на экране
    private void updateExpression(String value) {

        currentExpression += value;// Добавляем значение к текущему выражению
        resultOfSum.setText(currentExpression);  // Обновляем текст на экране
    }

    // Функция для вычисления выражения
    private double evaluateExpression(String expression) {
        // Используем exp4j для вычисления математического выражения
        Expression exp = new ExpressionBuilder(expression).build();
        return exp.evaluate();
    }




}