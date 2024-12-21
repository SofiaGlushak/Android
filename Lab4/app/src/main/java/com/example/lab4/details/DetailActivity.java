package com.example.lab4.details;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab4.R;
import com.example.lab4.models.University;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tvUniversityDetailName = findViewById(R.id.tvUniversityDetailName);
        TextView tvUniversityDetailCountry = findViewById(R.id.tvUniversityDetailCountry);
        TextView tvUniversityDetailWebPage = findViewById(R.id.tvUniversityDetailWebPage);
        TextView tvUniversityDetailDomain = findViewById(R.id.tvUniversityDetailDomain);

        University university = (University) getIntent().getSerializableExtra("university");

        if (university != null) {
            tvUniversityDetailName.setText(university.getName());
            tvUniversityDetailCountry.setText("Country: " + university.getCountry());
            tvUniversityDetailWebPage.setText("Web Page: " + university.getWebPage());
            tvUniversityDetailDomain.setText("Domain: " + university.getDomain());
        } else {
            Log.e("DetailActivity", "University object is null!");
        }


    }
}
