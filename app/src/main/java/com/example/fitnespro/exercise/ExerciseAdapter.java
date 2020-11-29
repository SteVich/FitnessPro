package com.example.fitnespro.exercise;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnespro.R;
import com.example.fitnespro.database.model.Exercise;
import com.example.fitnespro.databinding.ExerciseRowBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> implements Serializable {
    private transient final Context context;
    private final transient ExerciseService exerciseService;
    private List<Exercise> exercises;

    public ExerciseAdapter(Context context, List<Exercise> exercises, ExerciseService exerciseService) {
        this.context = context;
        this.exercises = exercises;
        this.exerciseService = exerciseService;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ExerciseRowBinding inflate = DataBindingUtil.inflate(inflater, R.layout.exercise_row, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Exercise exercise = exercises.get(position);
        holder.bind(exercise);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void updateList(List<Exercise> newList) {
        ExerciseDiffUtilCallBack diffUtilCallBack = new ExerciseDiffUtilCallBack(exercises, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffUtilCallBack);
        diffResult.dispatchUpdatesTo(this);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ExerciseRowBinding exerciseRowBinding;

        public MyViewHolder(ExerciseRowBinding exerciseRowBinding) {
            super(exerciseRowBinding.getRoot());
            this.exerciseRowBinding = exerciseRowBinding;
        }

        public void bind(Exercise exercise) {
            exerciseRowBinding.setExercise(exercise);
            exerciseRowBinding.executePendingBindings();
            exerciseRowBinding.setVariable(BR.handler, new ExerciseHandler());
        }
    }

    public class ExerciseHandler {
        public void removeExercise(final Exercise exercise) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Delete");
            alert.setMessage("Are you sure you want to delete this exercise?");
            alert.setPositiveButton("Yes", (dialog, which) -> {
                int position = exercises.indexOf(exercise);
                List<Exercise> newExercises = new ArrayList<>(exercises);
                newExercises.remove(position);
                updateList(newExercises);
                exercises.remove(position);
                exerciseService.deleteExercise(exercise);

                dialog.dismiss();
            });
            alert.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            alert.show();
        }
    }
}