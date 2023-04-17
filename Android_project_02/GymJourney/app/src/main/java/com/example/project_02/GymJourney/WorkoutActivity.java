/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.05.041723
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_02.R;

public class WorkoutActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * TODO display the user workout name; display the view of all the workout view data from video;
     *  button should be used to add a session, taking to add session activity;
     *  selecting a session takes user to the current session activity
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
    }

    // to switch to workout activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, WorkoutActivity.class);
    }

}