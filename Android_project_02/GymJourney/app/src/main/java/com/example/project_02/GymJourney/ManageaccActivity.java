/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.05.041723
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project_02.R;
import com.example.project_02.databinding.ActivityManageaccBinding;

import java.util.List;

public class ManageaccActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * TODO insert the view with all the data needed;  delete item by swiping, and confirmation screen after swiping with alert dialogue;
     * 0.02.02.041823: added button function;
     * testing the live data, view model, and recycler view for managing the user accounts here;     *
     * TODO implement repository, view model, and recycler view for managing the accounts
     */

    private ActivityManageaccBinding binding_manageacc;

    Button button_goback;

    // TESTING: live data and observer for recycler view for this admin page
    private UserViewModel user_viewmodel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageacc);
        // inflate binding and set contentview
        binding_manageacc = ActivityManageaccBinding.inflate(getLayoutInflater());
        setContentView(binding_manageacc.getRoot());

        button_goback = binding_manageacc.gobackButton;


        // ** GO BACK TO ADMIN PAGE BUTTON **
        button_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin_activity = AdminActivity.IntentFactory(getApplicationContext());
                startActivity(admin_activity);

            }
        });


        // different than from reference video. got from: https://stackoverflow.com/questions/53903762/viewmodelproviders-is-deprecated-in-1-1-0
        user_viewmodel = new ViewModelProvider(this).get(UserViewModel.class);
        user_viewmodel.getAllUsers().observe(this, new Observer<List<UserEntity>>() {

            // triggered everytime the live data changes in the view model ( exactly what i want i think :D)
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                // TODO update recycler view
                Toast.makeText(ManageaccActivity.this, "OBSERVING!", Toast.LENGTH_SHORT).show();

            }
        });




    }



    // to switch to manage acc activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, ManageaccActivity.class);
    }
}