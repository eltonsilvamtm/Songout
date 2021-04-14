package com.songout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ChosenExerciseActivity extends AppCompatActivity {
    TextView exercise_name, exercise_description;
    ImageView exercise_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosen_exercise);

        exercise_name = findViewById(R.id.selected_name);
        exercise_description = findViewById(R.id.selected_description);
        exercise_image = findViewById(R.id.selected_image);



        Intent intent = getIntent();
        exercise_name.setText(intent.getStringExtra("selected_name"));
        exercise_description.setText(intent.getStringExtra("selected_description"));
        exercise_image.setImageResource(intent.getIntExtra("selected_image", 0));




        //System.out.println(exercise_name.getText().toString());




    }
}