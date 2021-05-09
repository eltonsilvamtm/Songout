package com.songout.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.songout.R;
import com.songout.model.Song;
import com.spotify.android.appremote.api.SpotifyAppRemote;

public class ChosenExerciseActivity extends AppCompatActivity {
    TextView selected_name, selected_description;
    ImageView selected_image;
    public SpotifyAppRemote mSpotifyAppRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_exercise);

        selected_name = findViewById(R.id.selected_name);
        selected_description = findViewById(R.id.selected_description);
        selected_image = findViewById(R.id.selected_image);


        Intent intent = getIntent();
        selected_name.setText(intent.getStringExtra("exercise_name"));
        selected_description.setText(intent.getStringExtra("exercise_description"));
        selected_image.setImageResource(intent.getIntExtra("exercise_image", 0));





    }

    public void playSong(Song song) {
        String songToPlay = "spotify:track:" + song.getId();
        //mSpotifyAppRemote.getPlayerApi().play(songToPlay);
    }



}