package com.songout.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.songout.R;
import com.songout.ml.BPMmodel;
import com.songout.model.Song;
import com.spotify.android.appremote.api.SpotifyAppRemote;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ChosenExerciseActivity extends AppCompatActivity {
    TextView selected_name, selected_description;
    ImageView selected_image;
    public SpotifyAppRemote mSpotifyAppRemote;
    private @NonNull float[] BPMfromModel;

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

        //invokeModel(2);
        //Toast.makeText(this, BPMfromModel.toString(), Toast.LENGTH_LONG).show();




    }

    public void playSong(Song song) {
        String songToPlay = "spotify:track:" + song.getId();
        //mSpotifyAppRemote.getPlayerApi().play(songToPlay);
    }

    public void invokeModel(int intensity){

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(intensity);
        try {
            BPMmodel model = BPMmodel.newInstance(this);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 1}, DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer);

            // Runs model inference and gets result.
            BPMmodel.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();
            BPMfromModel = outputFeature0.getFloatArray();

            // Releases model resources if no longer used.
            model.close();
        } catch (IOException e) {
            // TODO Handle the exception
        }

    }


}