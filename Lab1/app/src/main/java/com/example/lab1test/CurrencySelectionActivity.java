package com.example.lab1test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class CurrencySelectionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_selection);

        RadioButton euroButton = findViewById(R.id.radio_euro);
        RadioButton dollarButton = findViewById(R.id.radio_dollar);
        Button calculateButton = findViewById(R.id.btn_calculate);

        calculateButton.setOnClickListener(v -> {
            String selectedCurrency = euroButton.isChecked() ? "EUR" : "USD";

            // Отримуємо попередні дані з бандлу
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                bundle.putString("currency", selectedCurrency);
                Intent intent = new Intent(CurrencySelectionActivity.this, ResultActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}

