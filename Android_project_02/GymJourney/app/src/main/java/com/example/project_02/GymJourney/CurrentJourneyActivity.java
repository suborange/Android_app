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

public class CurrentJourneyActivity extends AppCompatActivity {


    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * TODO insert the view with all the data needed; add button adds new item, delete item by name? edit text gets updated live;
     *  selecting a workout should then take it to the workout activity.
     *  idea: make select workout a click event, and go into the workout by long clicking.
     *  or just make two buttons, to add one or delete one by name?
     *
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_journey);
    }


    // to switch to current journey activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, CurrentJourneyActivity.class);
    }


}