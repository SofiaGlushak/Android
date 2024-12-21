package com.example.lab4.network;

import com.example.lab4.models.University;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UniversityApi {
    @GET("search")
    Call<List<University>> getUniversities(@Query("country") String country);
}


