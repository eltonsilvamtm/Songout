package com.songout;


import androidx.appcompat.app.AppCompatActivity;

/**
 * The object that will represent a exercise
 */
public class Exercise extends AppCompatActivity {

        private String exercise_name, exercise_description;
        private int exercise_image;

        public Exercise(String exercise_name, String exercise_description, int exercise_image) {
            this.exercise_name = exercise_name;
            this.exercise_description = exercise_description;
            this.exercise_image = exercise_image;
        }

        public String getExercise_name() {
            return exercise_name;
        }

        public void setExercise_name(String exercise_name) {
            this.exercise_name = exercise_name;
        }

        public String getExercise_description() {
            return exercise_description;
        }

        public void setExercise_description(String email) {
            this.exercise_description = email;
        }

        public int getExercise_image() {
            return exercise_image;
        }

        public void setExercise_image(int exercise_image) {
            this.exercise_image = exercise_image;
        }

    }

