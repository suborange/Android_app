/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.05.041723
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityWorkoutBinding;

public class WorkoutActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * TODO display the user workout name; display the view of all the workout view data from video;
     *  button should be used to add a session, taking to add session activity;
     *  selecting a session takes user to the current session activity
     *
     */

    private ActivityWorkoutBinding binding_workout;

    EditText edit_workout_name_field;
    Button button_add_session;

    //DAO
    myDAO DAO_workout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        // inflate binding layoutr and set view
        binding_workout = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding_workout.getRoot());

        button_add_session = binding_workout.addSessionButton;
        edit_workout_name_field = binding_workout.workoutNameField;

        // get DAO singletong for this activity
        DAO_workout = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO();



        // get logged in user, check to see if their is a workout without a name, so need to check them all?




        // ** ADD SESSION **
        button_add_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add a session here

            }
        });

        // ** EDIT TEXT WORKOUT NAME **
        edit_workout_name_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // get the logged in user
                UserEntity user_loggedin = DAO_workout.QueryLoggedinUser(true);
                // with the user, now find the workout that was selected.
                String db_workout_name;
                if (s.toString().compareTo("") == 0 ) {
                    Toast.makeText(WorkoutActivity.this, "Enter journey name atop!", Toast.LENGTH_SHORT).show();
                }

                // if its empty, also make it blank, otherwise it will leave the last character. this is a dumb case i shouldnt care about for now. otherwise:
                // assign the text from the editfield, after a change is made to update the database of this name
                db_workout_name = edit_workout_name_field.getText().toString();
                // set the new name to our object
                // TODO fix this part
                //  then update the database with this newly formed String. eventually will stop editing and so last saved is what it is
                DAO_workout.Update();
                // should have to set text, as it should stay  until screen changes, which is handled onCreate() ( but maybe can do it anyway)

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }

    // to switch to workout activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, WorkoutActivity.class);
    }

}