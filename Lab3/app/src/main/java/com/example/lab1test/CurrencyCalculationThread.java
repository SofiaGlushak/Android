package com.example.lab1test;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CurrencyCalculationThread extends Thread {
    private final double income;
    private final double percentage;
    private final String currency;
    private final Callback callback;

    private final Context context;

    public interface Callback {
        void onResult(String result);
    }

    public CurrencyCalculationThread(double income, double percentage, String currency, Callback callback, Context context) {
        this.income = income;
        this.percentage = percentage;
        this.currency = currency;
        this.callback = callback;
        this.context = context;
    }

    @Override
    public void run() {
        // Завантажуємо курси валют
        HashMap<String, Double[]> rates = loadExchangeRates();
        if (rates == null || !rates.containsKey(currency)) {
            callback.onResult("Помилка завантаження курсів валют");
            return;
        }

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

        // Викликаємо callback з результатом
        callback.onResult(result);
    }


    private HashMap<String, Double[]> loadExchangeRates() {
        HashMap<String, Double[]> rates = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(context.getResources().openRawResource(R.raw.exchange_rates)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String currency = parts[0].trim();
                    double startRate = Double.parseDouble(parts[1].trim());
                    double endRate = Double.parseDouble(parts[2].trim());
                    rates.put(currency, new Double[]{startRate, endRate});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rates;
    }
}
