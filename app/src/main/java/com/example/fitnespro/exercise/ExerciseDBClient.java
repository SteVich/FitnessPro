package com.example.fitnespro.exercise;

import android.content.Context;

import androidx.room.Room;

import com.example.fitnespro.database.ExerciseDatabase;

public class ExerciseDBClient {

    private final Context context;
    private static ExerciseDBClient exerciseDBClient;
    private final ExerciseDatabase excerciseDatabase;

    public ExerciseDBClient(Context context) {
        this.context = context;
        excerciseDatabase = Room.databaseBuilder(context, ExerciseDatabase.class, "MyExercise").build();
    }

    public static synchronized ExerciseDBClient getInstance(Context mCtx) {
        if (exerciseDBClient == null) {
            exerciseDBClient = new ExerciseDBClient(mCtx);
        }
        return exerciseDBClient;
    }

    public ExerciseDatabase getExcerciseDatabase() {
        return excerciseDatabase;
    }
}
