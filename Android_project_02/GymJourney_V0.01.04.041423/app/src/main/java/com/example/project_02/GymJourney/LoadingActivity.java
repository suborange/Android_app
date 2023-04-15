/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */

/** VERSIONS
 * 0.01.00.041023: updated build.gradle:module:app create packages, and db files, java files and xml files; created main, login, admin, and user activity.xml
 * TODO-0.01.01.041123: deleted state entity, appended user entity; main activity displays home page to login; can login as admin or as user; 2 predefined users, 1 user 1 admin; landing page displays users name, indicate if admin.
 * TODO- isAdmin key in user table; include a logout button that takes back to the main activity; submit DAO, java files, video running the app, and pdf of updated layout and cases.
 *
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityLoadingBinding;

// can be used as a loading screen, can do some sort of stuff here before going to main activity?
public class LoadingActivity extends AppCompatActivity {

    /**
     * 0.01.00.041023: created as initial landing page, will deal with initial database, and then decide if logged in or not, and go through there. maybe for now its just this page.
     *  If no user is logged in, display this main home page; if user is logged in, retrieve information and go to LoginActivity; If user is admin, retrieve information and go to AdminActivity
     * 0.01.02.041223: added all fields, binding, objects, DAO, and methods, IntentFactory, clickListeners;
     * 0.01.03.041323: check the database and see if a user is logged in or not;
     *  if logged in load and go directly to user view page, or admin view page if its admin;
     *  otherwise go to main activity page, to ask for user to sign in;
     */

    private static final boolean NOT_ADMIN = false;
    private static final boolean ADMIN = true;

    // tag

    // binding
    private ActivityLoadingBinding binding_loading;

    // objects

    // DAO
    myDAO DAO_loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        // inflate binding and set view
        binding_loading = ActivityLoadingBinding.inflate(getLayoutInflater());
        setContentView(binding_loading.getRoot());

        // get DAO singleton for this activity
        DAO_loading = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO();

        // setup the app

        // TODO first check if anything exists, dont wannna add duplicate etc. need to make this safe
        boolean user_exists = DAO_loading.TestExistenceOfUsers(true);
        if (user_exists) {
            // check if any users are logged in, if so then go to logged in screen: user, admin, or if not then main screen
            UserEntity load_user = DAO_loading.QueryLoggedinUser(true);



            // NOT admin user, go through user path and activity
            if (load_user.getIs_admin() == NOT_ADMIN) { // refactor as load_user.IsAdmin(), as boolean
                Intent userPage = UserActivity.IntentFactory(getApplicationContext());
                startActivity(userPage);
            }
            // IS admin user, go through admin path and activity
            else { // if (load_user.getIs_admin() == ADMIN){
                Intent adminPage = AdminActivity.IntentFactory(getApplicationContext());
                startActivity(adminPage);
            }
        }
        else { // TODO need to check if this returns true or false, if no users logged in then go to main activity:
        Intent mainPage = MainActivity.IntentFactory(getApplicationContext());
        startActivity(mainPage);

        }

    }
}