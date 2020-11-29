package com.example.fitnespro.exercise;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnespro.R;
import com.example.fitnespro.database.model.Exercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ExerciseActivity extends AppCompatActivity {

    ExerciseService exerciseService;
    RecyclerView recyclerView;

    FloatingActionButton add_button;
    String type;
    static List<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exersise_list);

        exerciseService = new ExerciseService(getApplicationContext());

        type = getIntent().getStringExtra("type");
        exercises = exerciseService.getExercises(type);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExerciseActivity.this));

        ExerciseAdapter adapter = new ExerciseAdapter(ExerciseActivity.this, exercises, exerciseService);
        recyclerView.setAdapter(adapter);

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(ExerciseActivity.this, AddExerciseActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("ExerciseAdapter", adapter);
            startActivity(intent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

}