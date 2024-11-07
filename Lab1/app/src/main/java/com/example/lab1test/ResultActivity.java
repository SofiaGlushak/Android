package com.example.lab1test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultText = findViewById(R.id.result_text);
        Button okButton = findViewById(R.id.btn_ok);

        // Отримуємо дані з попередніх екранів
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            double income = bundle.getDouble("income");
            double percentage = bundle.getDouble("percentage");
            String currency = bundle.getString("currency");

            double SY = 12 * income; // Гіпотетичний річний дохід
            double Sc = percentage * SY; // Гроші на обмін валюти

            double C_START = (currency.equals("EUR")) ? 40.0 : 36.0;
            double C_END = (currency.equals("EUR")) ? 42.0 : 38.0;

            // Інтерполяція курсу та обчислення придбаної валюти
            double W = 0;
            for (int i = 1; i <= 12; i++) {
                double C_i = C_START + i * (C_END - C_START) / 12;
                W += (percentage * income) / C_i;
            }

            double SH = W * C_END; // Гривнева вартість валюти
            double SL = SY - Sc;   // Гривневий залишок
            double H = SH + SL;    // Загальна сума
            double R = H - SY;     // Сума заощаджень

            String result = String.format("Гіпотетичний річний дохід у гривні: %.2f\n" +
                    "Кількість гривень витрачених на обмін валюти: %.2f\n" +
                    "Кількість валюти придбаної за рік: %.2f\n" +
                    "Гривнева вартість придбаної валюти на кінець року: %.2f\n" +
                    "Залишок у гривнях: %.2f\n" +
                    "Сума гривневого залишку та гривневої валюти на кінець року: %.2f\n" +
                    "Сума заощаджень: %.2f", SY, Sc, W, SH, SL, H, R);
            resultText.setText(result);
        }

        // Повернення на головний екран
        okButton.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }
}
