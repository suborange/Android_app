/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.04.041423
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.myDAO;
import com.example.project_02.databinding.ActivityMainBinding;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_02.R;

// If no user is logged in, display this main home page; if user is logged in, retrieve information and go to LoginActivity;
// If user is admin, retrieve information and go to AdminActivity
public class MainActivity extends AppCompatActivity {
    /**
     * 0.01.00.041023: created as initial landing page, will deal with initial database, and then decide if logged in or not, and go through there. maybe for now its just this page.
     * If no user is logged in, display this main home page; if user is logged in, retrieve information and go to LoginActivity; If user is admin, retrieve information and go to AdminActivity
     * 0.01.01.041123: added all fields, binding, objects, DAO, and methods, IntentFactory, clickListeners,
     * 0.01.02.041223: make both buttons work, go to their activites. then in those activities, have sign in work and create account work;
     *      can start with adding the first two items on creation of the app ( so like after the wipe data)
     *      *      Username	    Password	Is admin?
     *      *      testuser1	    testuser1	no
     *      *      admin2	        admin2	    yes
     *
     * 0.01.03.041323: added check to see if any user exists with the default user entities, if they don't exist, then create and insert them into database
     * TODO start and setup all the other activites, until i figure out the table portion
     *  make the "tables" just one text view, seperated by new lines or something, ( maybe 3 text views controlled by one scroll) and can refresh like he did
     *  and would have to figure out how to make the buttons to edit or delete work..
     *  maybe add a field or selection to delete one of them. hm field seems easiest
     *  
     */
    public static final String VERSION = "0.01.00.41023";


    //tag
    private static final String MAIN_TAG = "HOME_ACTIVITY";

    //bind
    private ActivityMainBinding binding_main;

    //objects
    Button button_login; // press to go to login screen
    Button button_createacc; // press to go to create account screen

    // DAO
    myDAO DAO_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // set binding and view
        binding_main = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding_main.getRoot());

        // setup objects to the activity.xml objects
        button_login = binding_main.loginButton;
        button_createacc = binding_main.createaccButton;

        // get DAO singleton for this activity
        DAO_main = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO();


        // TODO first check if anything exists, dont wannna add duplicate etc. need to make this safe ( also make this a function? pass myDAO)
        boolean default_users_exists = DAO_main.TestExistenceOfName("testuser1");
        boolean default_admin_exists = DAO_main.TestExistenceOfName("admin2");

        // setup default accounts, the user 1 and admin 1. create two new UserEntities
        if (!default_users_exists) {
            UserEntity default_user = new UserEntity("testuser1", "testuser1");
            // log them out, since it defaults to logging on creation.
            default_user.setLogged_in(false);
            // insert them into the list
            DAO_main.Insert(default_user);
        }
        if (!default_admin_exists) {

            UserEntity default_admin = new UserEntity("admin2", "admin2");
            default_admin.setIs_admin(true); // make the default admin an actual admin.
            default_admin.setLogged_in(false);
            // insert them into the list
            DAO_main.Insert(default_admin);

        }

        // ** CREATE ACCOUNT **
        // make the buttons have click listeners and onclick methods
        button_createacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // just needs to change intents?
                Intent createacc_activity = CreateaccActivity.IntentFactory(getApplicationContext());
                startActivity(createacc_activity);
            }
        });

        // ** LOGIN **
        // make the buttons have click listeners and onclick methods
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // just needs to change intents?
                Intent login_activity = LoginActivity.IntentFactory(getApplicationContext());
                startActivity(login_activity);
            }
        }); 

    }// end oncreate
    
    // create intents out here, to swtich to Main activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, MainActivity.class);
    }

} // end class