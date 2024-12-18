package com.example.lab1test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CurrencySelectionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency_selection, container, false);

        RadioButton euroButton = view.findViewById(R.id.radio_euro);
        RadioButton dollarButton = view.findViewById(R.id.radio_dollar);
        Button calculateButton = view.findViewById(R.id.btn_calculate);

        calculateButton.setOnClickListener(v -> {
            String selectedCurrency = euroButton.isChecked() ? "EUR" : "USD";

            Bundle arguments = getArguments();
            if (arguments != null) {
                arguments.putString("currency", selectedCurrency);

                ResultFragment fragment = new ResultFragment();
                fragment.setArguments(arguments);

                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
