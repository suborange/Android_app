package com.example.project_02.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02.GymJourney.UserEntity;
import com.example.project_02.GymJourney.WorkoutEntity;
import com.example.project_02.R;

import java.util.ArrayList;
import java.util.List;

/**     SPECIFICALLY FOR WORKOUT ADAPTER
 *
 */
public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.WorkoutHolder> {

    /** 0.02.03.041923: created recycler view, to be used for the admin managing activity;
     * implemented onCreate view holder, onBind view holder, and user holder class with getters and setters;
     * DONE?
     */

    private List<WorkoutEntity> workouts = new ArrayList<>();

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_list, parent, false);
        return new WorkoutHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder holder, int position) {
        // this is for the current journey, and workout names
        WorkoutEntity current_workout = workouts.get(position);

        String workout_item = current_workout.getWorkout_name();
        holder.text_workout_item.setText(workout_item);



    }

    @Override
    public int getItemCount() {
        return workouts.size();
    }

    public void setWorkouts(List<WorkoutEntity> workouts) {
        this.workouts = workouts;
        notifyDataSetChanged(); 
    }

    public WorkoutEntity getWorkoutAt(int position) {
        return workouts.get(position);
    }



    class WorkoutHolder extends RecyclerView.ViewHolder {
        private TextView text_workout_item;



        public WorkoutHolder(@NonNull View itemView) {
            super(itemView);
            text_workout_item = itemView.findViewById(R.id.workout_item_text);


        }
    }


}
