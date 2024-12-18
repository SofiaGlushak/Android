package com.example.lab1test;

import android.app.IntentService;
import android.content.Intent;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CurrencyCalculationService extends IntentService {

    public static final String ACTION_RESULT = "com.example.lab1test.RESULT";
    public static final String EXTRA_RESULT = "result";

    public CurrencyCalculationService() {
        super("CurrencyCalculationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            double income = intent.getDoubleExtra("income", 0);
            double percentage = intent.getDoubleExtra("percentage", 0);
            String currency = intent.getStringExtra("currency");

            // Завантажуємо курси валют
            HashMap<String, Double[]> rates = loadExchangeRates();
            if (rates == null || !rates.containsKey(currency)) return;

            Double[] rateRange = rates.get(currency);
            double C_START = rateRange[0];
            double C_END = rateRange[1];

            // Обчислення
            double SY = 12 * income;
            double Sc = percentage * SY;
            double W = 0;
            for (int i = 1; i <= 12; i++) {
                double C_i = C_START + i * (C_END - C_START) / 12;
                W += (percentage * income) / C_i;
            }
            double SH = W * C_END;
            double SL = SY - Sc;
            double H = SH + SL;
            double R = H - SY;

            String result = String.format("Гіпотетичний річний дохід у гривні: %.2f\n" +
                    "Кількість гривень витрачених на обмін валюти: %.2f\n" +
                    "Кількість валюти придбаної за рік: %.2f\n" +
                    "Гривнева вартість придбаної валюти на кінець року: %.2f\n" +
                    "Залишок у гривнях: %.2f\n" +
                    "Сума гривневого залишку та гривневої валюти на кінець року: %.2f\n" +
                    "Сума заощаджень: %.2f", SY, Sc, W, SH, SL, H, R);

            // Відправляємо результат
            Intent resultIntent = new Intent(ACTION_RESULT);
            resultIntent.putExtra(EXTRA_RESULT, result);
            LocalBroadcastManager.getInstance(this).sendBroadcast(resultIntent);
        }
    }

    private HashMap<String, Double[]> loadExchangeRates() {
        HashMap<String, Double[]> rates = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(getResources().openRawResource(R.raw.exchange_rates)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String currency = parts[0];
                Double startRate = Double.parseDouble(parts[1]);
                Double endRate = Double.parseDouble(parts[2]);
                rates.put(currency, new Double[]{startRate, endRate});
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return rates;
    }
}
