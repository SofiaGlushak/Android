package com.example.lab1test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ResultFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        TextView resultText = view.findViewById(R.id.result_text);
        Button okButton = view.findViewById(R.id.btn_ok);

        // Слухаємо оновлення від сервісу
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String result = intent.getStringExtra(CurrencyCalculationService.EXTRA_RESULT);
                        resultText.setText(result);
                    }
                },
                new IntentFilter(CurrencyCalculationService.ACTION_RESULT)
        );

        // Стартуємо сервіс для розрахунків
        Bundle arguments = getArguments();
        if (arguments != null) {
            Intent intent = new Intent(requireContext(), CurrencyCalculationService.class);
            intent.putExtras(arguments);
            requireContext().startService(intent);
        }

        // Повернення на головний екран
        okButton.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        });

        return view;
    }
}
