package com.example.fitnespro.exercise;

import android.content.Context;
import android.widget.Toast;

import com.example.fitnespro.database.model.Exercise;

import java.util.List;

public class ExerciseService {

    private final Context context;

    public ExerciseService(Context context) {
        this.context = context;
    }

    public List<Exercise> getExercises(String type) {
        return ExerciseDBClient
                .getInstance(context)
                .getExerciseDatabase()
                .exerciseDao()
                .getAllByType(type);
    }

    public void deleteExercise(final Exercise exercise) {
        ExerciseDBClient.getInstance(context).getExerciseDatabase()
                .exerciseDao()
                .delete(exercise.getId());

        Toast.makeText(context, "Deleted", Toast.LENGTH_LONG).show();
    }
}
