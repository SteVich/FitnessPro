package com.example.fitnespro.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.fitnespro.database.dao.ExerciseDao;
import com.example.fitnespro.database.model.Exercise;

@Database(entities = {Exercise.class}, version = 1)
public abstract class ExerciseDatabase extends RoomDatabase {

    public abstract ExerciseDao exerciseDao();
}
