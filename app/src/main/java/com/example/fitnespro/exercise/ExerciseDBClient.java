package com.example.fitnespro.exercise;

import android.content.Context;

import androidx.room.Room;

import com.example.fitnespro.database.ExerciseDatabase;

public class ExerciseDBClient {

    private final Context context;
    private static ExerciseDBClient exerciseDBClient;
    private final ExerciseDatabase exerciseDatabase;

    public ExerciseDBClient(Context context) {
        this.context = context;
        exerciseDatabase = Room.databaseBuilder(context, ExerciseDatabase.class, "Exercise")
                .allowMainThreadQueries().build();
    }

    public static synchronized ExerciseDBClient getInstance(Context mCtx) {
        if (exerciseDBClient == null) {
            exerciseDBClient = new ExerciseDBClient(mCtx);
        }
        return exerciseDBClient;
    }

    public ExerciseDatabase getExerciseDatabase() {
        return exerciseDatabase;
    }
}
