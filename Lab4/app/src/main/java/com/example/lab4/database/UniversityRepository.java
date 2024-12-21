package com.example.lab4.database;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.lab4.models.University;
import com.example.lab4.network.UniversityApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UniversityRepository {
    private UniversityDao universityDao;
    private Retrofit retrofit;

    public UniversityRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, "university-db").build();
        universityDao = db.universityDao();
        retrofit = new Retrofit.Builder()
                .baseUrl("http://universities.hipolabs.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public LiveData<List<University>> getUniversities() {
        MutableLiveData<List<University>> data = new MutableLiveData<>();

        retrofit.create(UniversityApi.class).getUniversities("Ukraine").enqueue(new Callback<List<University>>() {
            @Override
            public void onResponse(Call<List<University>> call, Response<List<University>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<University> universities = response.body();
                    Log.d("UniversityRepository", "Universities loaded: " + universities.size());
                    data.postValue(universities);
                    new Thread(() -> universityDao.insertAll(universities)).start();
                } else {
                    Log.e("UniversityRepository", "Response failed: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<University>> call, Throwable t) {
                Log.e("UniversityRepository", "Error loading data: ", t);
                new Thread(() -> data.postValue(universityDao.getAllUniversities())).start();
            }
        });

        return data;
    }
}

