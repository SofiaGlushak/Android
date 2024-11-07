package com.example.lab1test;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IncomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);

        EditText incomeInput = findViewById(R.id.income_input);
        EditText percentageInput = findViewById(R.id.percentage_input);
        Button nextButton = findViewById(R.id.btn_next);

        nextButton.setOnClickListener(v -> {
            try {
                double income = Double.parseDouble(incomeInput.getText().toString());
                double percentage = Double.parseDouble(percentageInput.getText().toString());

                // Перевірка, що відсоток доходу в межах від 0 до 1
                if (percentage > 0 && percentage < 1) {
                    Intent intent = new Intent(IncomeActivity.this, CurrencySelectionActivity.class);
                    // Передаємо дані через Bundle
                    Bundle bundle = new Bundle();
                    bundle.putDouble("income", income);
                    bundle.putDouble("percentage", percentage);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(IncomeActivity.this, "Відсоток повинен бути між 0 та 1", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(IncomeActivity.this, "Будь ласка, введіть коректні значення", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
