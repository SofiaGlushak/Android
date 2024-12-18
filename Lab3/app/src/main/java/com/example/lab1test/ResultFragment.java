package com.example.lab1test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView resultText = view.findViewById(R.id.result_text);
        Button okButton = view.findViewById(R.id.btn_ok);

        // Стартуємо потік для розрахунків
        Bundle arguments = getArguments();
        if (arguments != null) {
            double income = arguments.getDouble("income", 0);
            double percentage = arguments.getDouble("percentage", 0);
            String currency = arguments.getString("currency");

            CurrencyCalculationThread thread = new CurrencyCalculationThread(
                    income,
                    percentage,
                    currency,
                    result -> requireActivity().runOnUiThread(() -> resultText.setText(result)),
                    requireContext()
            );
            thread.start();
        }

        // Повернення на головний екран
        okButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });

        return view;
    }
}
