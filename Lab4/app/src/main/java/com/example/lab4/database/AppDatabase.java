package com.example.lab4.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab4.models.University;

@Database(entities = {University.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UniversityDao universityDao();
}
