/**
 * @author Ethan Bonavida
 * @version 0.02.02.041823
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 * @since April 10, 2023
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityAddSessionBinding;

public class AddSessionActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * 0.02.04.042023: add button functions;
     * 0.02.05.042023:display workout name, and create and add number picker and line up with each text;     *
     *  button should confirm the information on this activity;
     *
     *
     */

    private ActivityAddSessionBinding binding_addsesh;

    TextView text_workout_name;
    Button button_confirm_session;
    Button button_goback;
    NumberPicker nump_set; // to pick the number of sets
    NumberPicker nump_reps;
    NumberPicker nump_weight;

    myDAO DAO_addsesh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        //inflate binding, set content view
        binding_addsesh = ActivityAddSessionBinding.inflate(getLayoutInflater());
        setContentView(binding_addsesh.getRoot());

        text_workout_name = binding_addsesh.workoutNameText;
        button_confirm_session = binding_addsesh.confirmButton;
        button_goback = binding_addsesh.gobackButton;
        nump_set = binding_addsesh.setPicker;
        nump_set.setMinValue(1);
        nump_set.setMaxValue(4); // max sets of 1 - 4 sets
        nump_reps = binding_addsesh.repsPicker;
        nump_reps.setMinValue(6);
        nump_reps.setMaxValue(20);
        nump_weight = binding_addsesh.weightPicker;
        nump_weight.setMinValue(10);
        nump_weight.setMaxValue(200);

        DAO_addsesh = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO();

        // display the workout name from this active workout session
        WorkoutEntity workout = DAO_addsesh.QueryActiveWorkout(true); // get active workout for the name and even ID later one
        String workout_name = workout.getWorkout_name();
        text_workout_name.setText(workout_name);


        // ** CONFIRM SESSIONS **
        button_confirm_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int set_count = nump_set.getValue();
                int rep_count = nump_reps.getValue();
                int weight_initial = nump_weight.getValue();


                // get the workout id
                int w_id = workout.getWorkout_ID();

                // in the session, set the set counts
                SessionEntity session = new SessionEntity(w_id, set_count);
                // now add a session with this workout id and sets
                DAO_addsesh.Insert(session);

                // grab all data needed
                int s_id = session.getSession_ID();
                // now go into the set entity, and give these initial settings for all sets.
                // TODO make sure to check bounds here ( should be <= , so like 1-4)
                for (int i =1; i<= session.getSets_count(); i++ ) {
                    // loop from 1-max, inserting i, 1- max
                    SetsEntity sets = new SetsEntity(s_id, i, weight_initial, rep_count);
                    DAO_addsesh.Insert(sets); // should insert how ever many values now
                }

                // after adding, now go back to workout screen
                Intent workoutActivity = WorkoutActivity.IntentFactory(getApplicationContext());
                startActivity(workoutActivity);

            }
        });

        // ** BUTTON TO GO BACK TO WORKOUT LAYOUT **
        button_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workoutActivity = WorkoutActivity.IntentFactory(getApplicationContext());
                startActivity(workoutActivity);

            }
        });


    } // end on create

    // to switch to add session activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, AddSessionActivity.class);
    }

}