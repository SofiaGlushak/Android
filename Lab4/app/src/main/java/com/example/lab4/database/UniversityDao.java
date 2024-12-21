package com.example.lab4.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.lab4.models.University;

import java.util.List;

@Dao
public interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<University> universities);

    @Query("SELECT * FROM University")
    List<University> getAllUniversities();
}