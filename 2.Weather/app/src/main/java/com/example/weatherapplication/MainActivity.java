package com.example.weatherapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private final String apiKey = "ac3b458d44307389cac2426d3e387a25";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText cityInput = findViewById(R.id.cityInput);
        Button getWeatherButton = findViewById(R.id.getWeatherButton);
        TextView weatherResult = findViewById(R.id.weatherResult);

        getWeatherButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString();
            if (!city.isEmpty()) {
                fetchWeather(city, weatherResult);
            } else {
                weatherResult.setText("Введите название города!");
            }
        });
    }

    private void fetchWeather(String city, TextView weatherResult) {
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric&lang=ru";

        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(() -> weatherResult.setText("Ошибка: " + e.getMessage()));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseData = response.body().string();
                    try {
                        JSONObject json = new JSONObject(responseData);
                        String weatherDescription = json.getJSONArray("weather")
                                .getJSONObject(0).getString("description");
                        double temperature = json.getJSONObject("main").getDouble("temp");

                        String resultText = "Погода в " + city + ": " + temperature + "°C, " + weatherDescription;
                        runOnUiThread(() -> weatherResult.setText(resultText));

                    } catch (Exception e) {
                        runOnUiThread(() -> weatherResult.setText("Ошибка при обработке данных."));
                    }
                } else {
                    runOnUiThread(() -> weatherResult.setText("Ошибка при получении данных о погоде."));
                }
            }
        });
    }
}
