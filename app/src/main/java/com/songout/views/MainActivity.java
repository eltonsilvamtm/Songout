package com.songout.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.songout.R;
import com.songout.connectors.SongService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SongService savedtracks;
    private final ArrayList<Exercise> allExercises = new ArrayList<>();
    private RecyclerView exercisesRecyclerView;
    private ExerciseAdapter exerciseAdapter;
    private RequestQueue queue;

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

        queue = Volley.newRequestQueue(this);
        //savedtracks.getLikedTracks(queue);


    }

    public void fetchLikedSongs(){
        /* TODO: save the information regarding the user liked songs*/

    }
}
