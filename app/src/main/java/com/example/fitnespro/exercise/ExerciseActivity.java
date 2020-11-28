package com.example.fitnespro.exercise;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnespro.R;
import com.example.fitnespro.database.model.Exercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static java.util.Objects.nonNull;

public class ExerciseActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;
    String type;

    static List<Exercise> exerciseList;
    ExerciseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exersise_list);


        type = getIntent().getStringExtra("type");

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ExerciseActivity.this));

        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(ExerciseActivity.this, AddExerciseActivity.class);
            intent.putExtra("type", type);
            startActivity(intent);
        });

        getExercises(type);
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

        if (nonNull(exerciseList))
             adapter.updateList(exerciseList);
    }

    private void getExercises(String type) {
        class GetExercises extends AsyncTask<Void, Void, List<Exercise>> {

            @Override
            protected List<Exercise> doInBackground(Void... voids) {
                return ExerciseDBClient
                        .getInstance(getApplicationContext())
                        .getExcerciseDatabase()
                        .exerciseDao()
                        .getAllByType(type);
            }

            @Override
            protected void onPostExecute(List<Exercise> exercises) {
                super.onPostExecute(exercises);
                adapter = new ExerciseAdapter(ExerciseActivity.this, exercises);
                recyclerView.setAdapter(adapter);
                exerciseList = exercises;
                // adapter.updateList(exercises);
            }
        }

        GetExercises gt = new GetExercises();
        gt.execute();
    }

    private void deleteTask(final Exercise exercise) {
        class DeleteExercise extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                ExerciseDBClient.getInstance(getApplicationContext()).getExcerciseDatabase()
                        .exerciseDao()
                        .delete(exercise);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                super.onPostExecute(aVoid);
            }
        }

        DeleteExercise dt = new DeleteExercise();
        dt.execute();
    }
}