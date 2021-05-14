package com.songout.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.songout.R;
import com.songout.connectors.SongService;
import com.songout.connectors.SpotifyConnector;
import com.songout.ml.BPMmodel;
import com.songout.model.Song;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ChosenExerciseActivity extends AppCompatActivity {
    TextView selected_name, selected_description;
    ImageView selected_image;
    Button button;
    SpotifyConnector connect;

    private @NonNull float[] BPMfromModel;
    private ArrayList<Song> userSongs;
    private SongService saveTracks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_exercise);
        button = findViewById(R.id.button);

        connect = new SpotifyConnector(ChosenExerciseActivity.this);
        SharedPreferences sharedPreferences = this.getSharedPreferences("SPOTIFY", 0);

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                userSongs = connect.getLikedTracks(() -> {
                    userSongs = connect.getSongs();
                    Log.d("response", userSongs.toString());
                    connect.playSong(userSongs.get(0));
                });
            }
        });




       // Log.d("1",userSongs.get(0).toString());



        //saveTracks = new SongService(this);

        selected_name = findViewById(R.id.selected_name);
        selected_description = findViewById(R.id.selected_description);
        selected_image = findViewById(R.id.selected_image);

        Intent intent = getIntent();
        selected_name.setText(intent.getStringExtra("exercise_name"));
        selected_description.setText(intent.getStringExtra("exercise_description"));
        selected_image.setImageResource(intent.getIntExtra("exercise_image", 0));

        //invokeModel(2);
        //Toast.makeText(this, BPMfromModel.toString(), Toast.LENGTH_LONG).show();

        //fetchLikedSongs();

        //playSong(userSongs.get(0));




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        //userSongs = connect.getRecentlyPlayedTracks();

        Log.d("1",userSongs.get(0).toString());

        connect.playSong(userSongs.get(0));


    }

    public void playSong(Song song) {
        String songToPlay = "spotify:track:" + song.getId();
        //mSpotifyAppRemote.getPlayerApi().play(songToPlay);
    }

//    public void fetchLikedSongs(){
//
//        saveTracks.getLikedTracks(() -> {
//            userSongs = saveTracks.getSongs();
//            Log.d("response", userSongs.toString());
//
//            for(int i=0;i<userSongs.size();i++){
//                Song song;
//                song = userSongs.get(i);
//                Log.d("song",song.toString());
//            }
//            playSong(userSongs.get(0));
//        });




    //}

    public void invokeModel(int intensity){

        //allocate 8 bytes to the buffer
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(8);
        try {
            BPMmodel model = BPMmodel.newInstance(this);

            // Creates inputs for reference.
            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, 1}, DataType.FLOAT32);
            inputFeature0.loadBuffer(byteBuffer.put(1, (byte) intensity));

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