package com.songout.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.songout.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private final ArrayList<Exercise> allExercises = new ArrayList<>();
    private RecyclerView exercisesRecyclerView;
    private ExerciseAdapter exerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allExercises.add(new Exercise("Abdominal", "Work out the muscles of the abdomen", R.drawable.abdominal));
        allExercises.add(new Exercise("Pushups", "It is arms and shoulders time!", R.drawable.pushups));
        allExercises.add(new Exercise("Running", "Running to your goals!", R.drawable.running));
        allExercises.add(new Exercise("Streching", "Why wait? start streching now!", R.drawable.stretching));

        exercisesRecyclerView = findViewById(R.id.exercisesRecyclerView);
        exerciseAdapter = new ExerciseAdapter(allExercises, this);
        exercisesRecyclerView.setAdapter(exerciseAdapter);
        exercisesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }
}
