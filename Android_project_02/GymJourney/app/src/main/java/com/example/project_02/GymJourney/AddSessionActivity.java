/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.02.02.041823
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_02.R;
import com.example.project_02.databinding.ActivityAddSessionBinding;

public class AddSessionActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * 0.02.04.042023: add button functions;
     * TODO display workout name, and line up views with the view for all data;
     *  create the selectable scrollable thing from video, to have user select all this information;
     *  button should confirm the information on this activity;
     *
     *
     */

    private ActivityAddSessionBinding binding_addsesh;

    TextView text_workout_name;
    Button button_confirm_session;
    Button button_goback;

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



        // ** CONFIRM SESSIONS **
        button_confirm_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO gather all the data and store it in in sets entity. 
                
                // there will be a loop somewhere for something in regards to each set.

            }
        });

        // ** BUTTON TO GO BACK TO WORKOUT LAYOUT **
        button_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO set the session to be unactive here
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