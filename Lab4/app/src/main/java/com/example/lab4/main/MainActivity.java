package com.example.lab4.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.details.DetailActivity;
import com.example.lab4.R;
import com.example.lab4.database.UniversityRepository;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private UniversityRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repository = new UniversityRepository(getApplication());
        repository.getUniversities().observe(this, universities -> {
            if (universities != null && !universities.isEmpty()) {
                Log.d("MainActivity", "Universities received: " + universities.size());
                UniversityAdapter adapter = new UniversityAdapter(universities, university -> {
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("university", university);
                    startActivity(intent);
                });
                recyclerView.setAdapter(adapter);
            } else {
                Log.e("MainActivity", "No universities to display.");
            }
        });
    }
}
