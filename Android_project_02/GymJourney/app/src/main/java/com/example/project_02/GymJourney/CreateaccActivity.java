/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.03.041323:
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.myDAO;
import com.example.project_02.databinding.ActivityCreateaccBinding;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_02.R;

import java.security.Key;

public class CreateaccActivity extends AppCompatActivity {

    /**
     * 0.01.00.041023: created as initial landing page.
     * 0.01.01.041123: added all fields, binding, objects, DAO, and methods, IntentFactory, clickListeners;
     * get initial user values: user id, password, is admin; switching to and from user or admin;
     * 0.01.03.041323: added a small easter egg; add test query to check for existing user with user input, if exists continue;
     * checking if the input is blank, or some reason null at all, before inserting entity.
     * 0.01.04.041423: added go home button, and implemented click to main activity
     */

    // define operator ! :: DOES_NOT ~~ cant do it in java.. dang
    private int empty_acc = 0;
    private static final String ADMIN = "admin";
    private static final boolean TO_ADMIN = true; // for all admin checking?
    private static final boolean TO_USER = false; // for all admin checking?


    // tag
    private static final String CREATION = "CREATING ACCOUNT";
    // binding
    private ActivityCreateaccBinding binding_AC;

    // objects
    EditText user_input_field;
    EditText password_input_field;
    Button button_create_account;
    Button button_go_home;

    // DAO
    myDAO DAO_createacc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createacc);
        // inflate binding and view
        binding_AC = ActivityCreateaccBinding.inflate(getLayoutInflater());
        setContentView(binding_AC.getRoot());

        // setup objects
        user_input_field = binding_AC.UserInput;
        password_input_field = binding_AC.passwordInput;
        button_create_account = binding_AC.createAccButton;
        button_go_home = binding_AC.homeButton;

        // cant be inside of click listener
        // get DAO singleton for this activity*
        DAO_createacc = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO(); // singleton reference

        NothingToSeeHere(); // nothing at all
        // one button, one listener for clicks
        button_create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                // creating new user, so create an new entity for this users data
                UserEntity add_user = getInitialUserValues(); // id, password, and admin status. now is logged in too!
                // also update logged_in as true now. is now default to on because it was created before.
                // for password could look into some hashing or simple encryption/decryption

                // if user is not null, do this stuff:
                if (add_user == null) {
                    // if null, do nothing? or TOAST?
                    Toast.makeText(CreateaccActivity.this, "EMPTY NAME. RETRY!", Toast.LENGTH_SHORT).show();
                    // clear fields to re-enter. only password? or?
                    //user_input_field.getText().clear();
                    password_input_field.getText().clear();
                    return;
                }
                // should exist, and should not be null, so now create the user and insert into database.
                // now insert new user into our table of entities!
                DAO_createacc.Insert(add_user);


                // toast on succession? account created?
                //Toast.makeText(CreateaccActivity.this, "Something happened woo!", Toast.LENGTH_SHORT).show();

                // if admin, go to admin page
                if (add_user.getIs_admin()) {
                    Intent admin_activity = AdminActivity.IntentFactory(getApplicationContext());
                    startActivity(admin_activity);
                }
                // if not admin, go to user page
                else {
                    // then create new intent to the user activity, where there, the name will be grabbed to display
                    Intent user_activity = UserActivity.IntentFactory(getApplicationContext());
                    startActivity(user_activity);
                }

            }
        });

        // ** GO HOME **
        button_go_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // simply go to the home page and thats it.
                Intent main_activity = MainActivity.IntentFactory(getApplicationContext());
                startActivity(main_activity);
            }
        });



    }
    private void NothingToSeeHere() {
        password_input_field.setImeActionLabel("Secret:)", KeyEvent.KEYCODE_ENTER);
        password_input_field.setInputType(InputType.TYPE_CLASS_TEXT); // should display secret:) and disable any nonsense from user
    }

    // ** GET USER VALUES TO CREATE AN ACCOUNT **
    private UserEntity getInitialUserValues() {
        // get string input
        String user_input = user_input_field.getText().toString();
        String password_input = password_input_field.getText().toString();

        // need to check if the user already exists.
        boolean user_exists = DAO_createacc.TestExistenceOfName(user_input);

        if (user_exists) {
            // its already created so cannot create a duplicate. so do nothing?
            return null; // null object should make nothing happen, and allow for another click.
        }

        // user must not be null now:
        //if left empty, dont cause any isses and continue forward with default information. need admin assistance.
        if ( user_input.length() > 0 && password_input.length() > 0)
        {
            // continue as normal
            // with values, can now insert them into the database for user_id and user_password.
            UserEntity user = new UserEntity(user_input, password_input);
            // also adminster if admin or not. ( check spelling for admin)
            if (user_input.contains("admin")) {
                // set this user id isAdmin to true, otherwise leave as false default?
                user.setIs_admin(TO_ADMIN);
            }
            return user;
        }
        else {
           /* empty_acc++; // increment for everytime an empty user is created
            UserEntity default_user = new UserEntity("empty"+empty_acc, "hunter2"); // created a new default empty user, with default password
            Toast.makeText(this, "Admin assistance needed!", Toast.LENGTH_LONG).show();
            return default_user;*/
            return null; // null object should make nothing happen, and allow for another click.
        }
    } // end oncreate

    // ** INTENT FACTORY **
    public static Intent IntentFactory( Context packageContext) {
        return new Intent(packageContext, CreateaccActivity.class);
    }



} // end class