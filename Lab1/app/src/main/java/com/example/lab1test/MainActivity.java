package com.example.lab1test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Кнопка для переходу до екрана введення даних
        Button startCalcButton = findViewById(R.id.btn_start_calc);
        startCalcButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, IncomeActivity.class);
            startActivity(intent);
        });

        // Кнопка для виходу з додатка
        Button exitButton = findViewById(R.id.btn_exit);
        exitButton.setOnClickListener(v -> finish());
    }
}
