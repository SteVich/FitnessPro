package com.example.fitnespro.exercise;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnespro.R;
import com.example.fitnespro.database.model.Exercise;

import java.util.List;

public class AddExerciseActivity extends AppCompatActivity {

    private EditText addTitle, addTimeInMinutes;
    private String type;
    private ExerciseAdapter exerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_exercise);

        addTitle = findViewById(R.id.title_input);
        addTimeInMinutes = findViewById(R.id.time_input);

        findViewById(R.id.add_button_submit).setOnClickListener(view -> saveExercise());

        type = getIntent().getStringExtra("type");
        exerciseAdapter = (ExerciseAdapter) getIntent().getSerializableExtra("ExerciseAdapter");
    }

    private void saveExercise() {
        final String sTitle = addTitle.getText().toString().trim();
        final String sTimeInMinutes = addTimeInMinutes.getText().toString().trim();

        if (sTitle.isEmpty()) {
            addTitle.setError("Title required");
            addTitle.requestFocus();
            return;
        }

        if (sTimeInMinutes.isEmpty()) {
            addTimeInMinutes.setError("Time in minutes required");
            addTimeInMinutes.requestFocus();
            return;
        }

        class SaveExercise extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                Exercise exercise = new Exercise();
                exercise.setTitle(sTitle);
                exercise.setTimeInMinutes(Integer.parseInt(sTimeInMinutes));
                exercise.setType(type);
                exercise.setImageId(R.mipmap.fitness);

                ExerciseDBClient.getInstance(getApplicationContext()).getExerciseDatabase()
                        .exerciseDao()
                        .insert(exercise);

                List<Exercise> exercises = ExerciseActivity.exercises;
                exercises.add(exercise);
                exerciseAdapter.updateList(exercises);

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();

                Intent intent = new Intent(getApplicationContext(), ExerciseActivity.class);
                intent.putExtra("type", type);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveExercise st = new SaveExercise();
        st.execute();
    }

}
