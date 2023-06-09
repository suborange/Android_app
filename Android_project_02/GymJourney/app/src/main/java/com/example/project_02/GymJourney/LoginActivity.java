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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityLoginBinding;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    /**
     * 0.01.00.041023: created as initial landing page.
     * 0.01.01.041123: added all fields, binding, objects, DAO, and methods, IntentFactory, clickListeners,
     * 0.01.03.041323: get the entered information, similar to create acc
     *  when sign in button is hit, get information and check to see if any users have this information;
     *  Toast bad password, and non existent user, otherwise success should send to user or admin activity page;
     * 0.01.04.041423: now update query with changes, user now logged in
     */

    // tag

    // binding
    private ActivityLoginBinding binding_login;

    // objects
    EditText edit_user_input;
    EditText edit_pass_input;
    Button button_sign_in;
    Button button_go_home;

    // DAO
    myDAO DAO_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // inflate binding, set view
        binding_login = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding_login.getRoot());


        // setup objects
        edit_user_input = binding_login.UserInput;
        edit_pass_input = binding_login.passwordInput;
        button_sign_in = binding_login.signinButton;
        button_go_home = binding_login.homeButton;






        // get DAO singleton for this activity
        DAO_login = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO();


        // button on click listeners
        // should determine correct credentials, if not display a message? for now Toast?

        // need to add try catch as to not access a blank database. ( although it should be fine with two uses, 1 user and 1 admin)

        // ** SIGN IN **
        button_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // maybe trim any whitespace?
                String userid_nickname = edit_user_input.getText().toString().trim();
                String pass_id = edit_pass_input.getText().toString().trim();

                //List<UserEntity> sign_user_in = DAO_login.QueryAllUsers();

                // first check to see if some user exists?
                boolean user_exists = DAO_login.TestExistenceOfName(userid_nickname);

                // if user exists do the query for the user name entered, and with that entity/table/item, then check for correct or incorrect password
                if (user_exists) {
                    UserEntity User_exists = DAO_login.QueryThisUser(userid_nickname);
                    // it doesnt check to see if the user even exists, so not sure what happens. because not sure how to handle wrong password, vs no record and wrong password
                    // get the password from this user,
                    String user_pass = User_exists.getUser_password();
                    // compare database password to typed password
                    if (user_pass.compareTo(pass_id) == 0) {
                        // succesful login!
                        User_exists.setLogged_in(true); // LOGGED IN
                        // update query
                        DAO_login.Update(User_exists);


                       // Toast.makeText(LoginActivity.this, "Welcome " + userid_nickname, Toast.LENGTH_SHORT).show();


                        // is this user logging in an admin or a user?
                        boolean user_is_admin = User_exists.getIs_admin();
                        if (user_is_admin) {
                            Intent admin_activity = AdminActivity.IntentFactory(getApplicationContext());
                            startActivity(admin_activity); // start admin activity
                        } else {
                            Intent user_activity = UserActivity.IntentFactory(getApplicationContext());
                            startActivity(user_activity); // start user activity
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "WRONG PASSWORD. RETRY!", Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(LoginActivity.this, "USER DOES NOT EXIST. RETRY!", Toast.LENGTH_SHORT).show();
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





    } // end oncreate


        // intnet factory
        // to switch to login actvity ( this one )
        public static Intent IntentFactory (Context pkgContext){
            return new Intent(pkgContext, LoginActivity.class);
        }

}




// when we create an entity object, is it just those rows? what exactly does it hold? does it hold it all since its a class? it seems so.

//                for (UserEntity id : sign_user_in) {
//                    if (id.getUser_nickname().compareTo(user_id) == 0){
//                        if ( id.getUser_password().compareTo(pass_id) == 0) {
//                            id.setLogged_in(true); // log in with correct credentials
//                        }
//                    }
//                }

