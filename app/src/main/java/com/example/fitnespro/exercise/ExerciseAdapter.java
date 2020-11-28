package com.example.fitnespro.exercise;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnespro.R;
import com.example.fitnespro.database.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {
    private final Context context;
    private List<Exercise> exercises;

    public ExerciseAdapter(Context context, List<Exercise> exercises) {
        this.context = context;
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Exercise exercise = exercises.get(position);
        holder.title.setText(String.valueOf(exercise.getTitle()));
        holder.timeInMinutes.setText(String.valueOf(exercise.getTimeInMinutes()));
        holder.image.setImageResource(R.mipmap.fitness);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    public void updateList(List<Exercise> newList) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new ExerciseDiffUtilCallBack(this.exercises, newList));
        diffResult.dispatchUpdatesTo(this);
        exercises = newList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, timeInMinutes;
        ImageView image;
        LinearLayout exerciseLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.exercise_title);
            timeInMinutes = itemView.findViewById(R.id.time_in_minutes);
            image = itemView.findViewById(R.id.exerciseImg);

            exerciseLayout = itemView.findViewById(R.id.exerciseLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            exerciseLayout.setAnimation(translate_anim);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Exercise exercise = exercises.get(getAdapterPosition());
        }
    }
}