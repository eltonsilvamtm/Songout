package com.songout.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.songout.R;

import java.util.ArrayList;

/**
 * Class responsible for
 */
public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private ArrayList<Exercise> exercisesArray = new ArrayList<>();
    Context context;


    public ExerciseAdapter(ArrayList<Exercise> exercisesArray, Context context){
        this.exercisesArray = exercisesArray;
        this.context = context;
    } //constructor

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homepage_design, parent, false); //inflate the layout xml file and display it on the parent
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {

        holder.bind(exercisesArray.get(position)); //bind method comes from the ProjectViewHolder inner class
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChosenExerciseActivity.class);
                intent.putExtra("exercise_name", exercisesArray.get(position).getExercise_name());
                intent.putExtra("exercise_description", exercisesArray.get(position).getExercise_description());
                intent.putExtra("exercise_image", exercisesArray.get(position).getExercise_image());
                v.getContext().startActivity(intent); //you cannot start an activity without a context(this) so that you a Context object which will refer to the activity you wish to retrieve the information
            }
        });
    }

    @Override
    public int getItemCount() {

        return exercisesArray.size();
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {

        TextView text_view_exercise_name, text_view_exercise_description;
        ImageView image_view_exercise_image;
        CardView card_view_exercises;

        public ExerciseViewHolder(@NonNull View itemView){
            super(itemView);

            //itemView.setOnClickListener(this); //set the screen to capture the users click
            card_view_exercises = itemView.findViewById(R.id.card_view_parent);
            text_view_exercise_name = itemView.findViewById(R.id.text_view_exercise_name);
            text_view_exercise_description = itemView.findViewById(R.id.text_view_exercise_description);
            image_view_exercise_image = itemView.findViewById(R.id.image_view_exercise_image);

        }

        public void bind(Exercise exercise) {

            text_view_exercise_name.setText(exercise.getExercise_name());
            text_view_exercise_description.setText(exercise.getExercise_description());
            image_view_exercise_image.setImageResource(exercise.getExercise_image());


        }


//        @Override
//        public void onClick(View v) {
//            int position = getAdapterPosition();
//            Exercise chosenExercise = exercisesArray.get(position);
//
//        }
    }
}
