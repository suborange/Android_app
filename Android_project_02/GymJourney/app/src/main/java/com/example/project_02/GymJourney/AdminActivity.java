/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.03.041323:
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */

/** VERSIONS
 * 0.01.00.041023: updated build.gradle:module:app create packages, and db files, java files and xml files; get enough xml layouts for part 02;
 *
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
import com.example.project_02.databinding.ActivityAdminBinding;

public class AdminActivity extends AppCompatActivity {
    /**
     * 0.01.00.041023: created as initial landing page.
     * 0.01.01.041123: added all fields, binding, objects, DAO, and methods, IntentFactory, clickListeners;
     *   get admin user and display this admin account in text view instead of just admin;
     *   logout button should logout ( try the logged_in item to false), and then go back to the main activity;
     *   the manage accounts and admin press for luck, will just do toasts for now:
     *   manage: soon to implement
     *   luck: says a random funny quote from a small list;
     * 0.01.03.041323: synced a comment
     * 0.01.04.041423: now update query with changes ( logout); you idiot, you put the table instead of the database in DAO..;
     * TODO 0.01.05.041423: finish admin. implement manage and luck button functionalities. will need to make another layout for managing the accounts. so button will change intent to mgmt_acc intent;
     *  add are you sure button in layout, something that pops up to confirm the deletion of a user( in the database Delete()) ;
     *   go through todo's start todoing everything else. setup to github?
     */
    // tag

    // binding
    private ActivityAdminBinding binding_admin;


    // objects
    TextView text_admin_name;
    TextView text_admin_bg_name;
    Button button_manageacc;
    Button button_luck;
    Button button_logout;

    // DAO
    myDAO DAO_admin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        // inflate binding and set view
        binding_admin = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding_admin.getRoot());

        // setup objects
        text_admin_name = binding_admin.AdminText;
        text_admin_bg_name = binding_admin.AdminBgText;
        button_manageacc = binding_admin.manageaccButton;
        button_luck = binding_admin.specialAdButton;
        button_logout = binding_admin.logoutAdminButton;


        // get DAO singleton for this activity
        DAO_admin = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME) // DATABASE NAME YOU FOOOOL!
                .allowMainThreadQueries()
                .build()
                .getmyDAO(); //singleton reference


        // get admin user name and set to the textview and bg as the admin name.
        UserEntity loggedin_admin = DAO_admin.QueryLoggedinUser(true); // should only be the logged in admin user.
        String admin_name = loggedin_admin.getUser_nickname();
        text_admin_name.setText(admin_name);
        text_admin_bg_name.setText(admin_name);
        Toast.makeText(this, "M'lord " + admin_name, Toast.LENGTH_SHORT).show();

        // set on click listeners
        // ** MANAGE **
        button_manageacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO make this go to the screen that shows data on all the accounts created so far. ( user table)
                // for now TOAST
                // Toast.makeText(AdminActivity.this, "feature coming soon", Toast.LENGTH_SHORT).show();
                Intent manageacc_activity = ManageaccActivity.IntentFactory(getApplicationContext());
                startActivity(manageacc_activity);
            }
        });

        // ** LUCK *
        button_luck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO idk what to do for this secret button. my original idea was to put out funny random phrases or quotes.
                // for now TOAST
                Toast.makeText(AdminActivity.this, "feature coming soon", Toast.LENGTH_SHORT).show();

            }
        });


        // ** LOGOUT**
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO logout button should logout ( try the logged_in item to false), and then go back to the main activity.
                /*List<UserEntity> all_Users = DAO_User.getLoggedinUsers(true); // should hopefully return all users that have logged_in set to true; TODO TEST THIS
                // all_Users.get(0).setLogged_in(false); // need to find the right user and make it log out, set to false.
                for ( UserEntity user : all_Users ) {
                    user.setLogged_in(false); // now sets all accounts to false, so should log out all accounts ( but really should only be one )

                }*/
                //UserEntity loggedin_user = DAO_User.getLoggedinUser(true);
                loggedin_admin.setLogged_in(false); // log out the one user that should be logged in.
                DAO_admin.Update(loggedin_admin);
                // TODO update query

                // after log out go back to main menu, to sign in or create an account.
                Intent logout = MainActivity.IntentFactory(getApplicationContext());
                startActivity(logout);


            }
        });




    } // end oncreate



    // to switch to admin actvity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, AdminActivity.class);
    }

}