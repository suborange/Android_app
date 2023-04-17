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

public class AddSessionActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * TODO display workout name, and line up views with the view for all data;
     *  create the selectable scrollable thing from video, to have user select all this information;
     *  button should confirm the information on this activity;
     *
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
    }

    // to switch to add session activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, AddSessionActivity.class);
    }

}