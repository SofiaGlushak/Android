package com.example.lab1test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class IncomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_income, container, false);

        EditText incomeInput = view.findViewById(R.id.income_input);
        EditText percentageInput = view.findViewById(R.id.percentage_input);
        Button nextButton = view.findViewById(R.id.btn_next);

        nextButton.setOnClickListener(v -> {
            try {
                double income = Double.parseDouble(incomeInput.getText().toString());
                double percentage = Double.parseDouble(percentageInput.getText().toString());

                if (percentage > 0 && percentage < 1) {
                    Bundle bundle = new Bundle();
                    bundle.putDouble("income", income);
                    bundle.putDouble("percentage", percentage);

                    CurrencySelectionFragment fragment = new CurrencySelectionFragment();
                    fragment.setArguments(bundle);

                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                } else {
                    Toast.makeText(getContext(), "Відсоток повинен бути між 0 та 1", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Будь ласка, введіть коректні значення", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
