/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.02.06.042123
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityCurrentJourneyBinding;
import com.example.project_02.databinding.ActivityUserBinding;

public class UserActivity extends AppCompatActivity {
    /**
     * 0.01.00.041023: created as initial landing page; added all fields, binding, objects, DAO, and methods, IntentFactory, clickListeners
     * 0.01.01.041123: get logged in user information and set as the name for the text view field;
     * logout button should logout (try the logged_in item to false), and then go back to the main activity;
     * the current and restart journey buttons will just make a toast for now. soon to come.
     * 0.01.03.041323: synced comments, and made toast longer for failures
     * 0.01.04.041423: now update query with changes ( logout)
     * 0.02.05.042023: added restart journey functionality. deletes all data successfully1;
     */

    private static final boolean LOGGED_OUT = false;
    // tag

    // binding
    private ActivityUserBinding binding_user;

    // objects
    TextView text_User_name;

    Button button_current_journey;
    Button button_restart_journey;
    Button button_logout_User;


    // DAO
    myDAO DAO_User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        // inflate binding and get view
        binding_user = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding_user.getRoot());

        // setup objects
        text_User_name = binding_user.UserText;
        button_logout_User = binding_user.logoutUser;
        button_current_journey = binding_user.currentJourneyButton;
        button_restart_journey = binding_user.restartJourneyButton;

        // get DAO singleton for this activity
        DAO_User = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO(); // singleton reference


        // get user name, and then display the name for both bg and text
        UserEntity loggedin_user = DAO_User.QueryLoggedinUser(true); // get the one and only logged in user for this activity
        String user_name = loggedin_user.getUser_nickname();
        text_User_name.setText(user_name);
        Toast.makeText(this, "Welcome " + user_name, Toast.LENGTH_SHORT).show();



        // implement buttons, using DB, there is no change in data here yet, no writing, only reading data
        // ** CURRENT **
        button_current_journey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  the current and restart journey buttons will just make a toast for now. soon to come.
//                Toast.makeText(UserActivity.this, "feature coming soon", Toast.LENGTH_SHORT).show();
                    Intent current_journey_activity = CurrentJourneyActivity.IntentFactory(getApplicationContext());
                    startActivity(current_journey_activity);
            }
        });


        // ** RESTART **
        button_restart_journey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAO_User.DeleteAllSessions();
                DAO_User.DeleteAllWorkouts();
                // todo does not reset the workout name tho?
                // the current and restart journey buttons will just make a toast for now. soon to come.
                Toast.makeText(UserActivity.this, "journey reset", Toast.LENGTH_SHORT).show();

            }
        });

        // ** LOGOUT **
        // log out button should take the user @logged_in and set it to logged out
        button_logout_User.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  logout button should logout ( try the logged_in item to false), and then go back to the main activity.

                loggedin_user.setLogged_in(false); // log out the one user that should be logged in.
                // update query
                DAO_User.Update(loggedin_user);

                // after log out go back to main menu, to sign in or create an account.
                Intent logout = MainActivity.IntentFactory(getApplicationContext());
                startActivity(logout);

            }
        });


    } // end oncreate


    // to switch to user actvity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext,UserActivity.class);

    }
}