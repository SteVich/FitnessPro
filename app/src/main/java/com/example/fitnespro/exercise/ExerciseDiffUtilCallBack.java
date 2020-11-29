package com.example.fitnespro.exercise;

import androidx.recyclerview.widget.DiffUtil;

import com.example.fitnespro.database.model.Exercise;

import java.util.List;

import static java.util.Objects.nonNull;

public class ExerciseDiffUtilCallBack extends DiffUtil.Callback {

    private final List<Exercise> oldExercise;
    private final List<Exercise> newExercise;

    public ExerciseDiffUtilCallBack(List<Exercise> oldExercise, List<Exercise> newExercise) {
        this.oldExercise = oldExercise;
        this.newExercise = newExercise;
    }

    @Override
    public int getOldListSize() {
        return oldExercise.size();
    }

    @Override
    public int getNewListSize() {
        return newExercise.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        long oldId = oldExercise.get(oldItemPosition).getId();
        long newId = newExercise.get(newItemPosition).getId();

        return oldId == newId;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Exercise oldEx = oldExercise.get(oldItemPosition);
        Exercise newEx = newExercise.get(newItemPosition);

        return oldEx.getId() == newEx.getId()
                && oldEx.getTitle().equals(newEx.getTitle())
                && oldEx.getType().equals(newEx.getType())
                && oldEx.getTimeInMinutes() == newEx.getTimeInMinutes()
                && oldEx.getImageId() == newEx.getImageId();
    }
}
