/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.02.02.041823
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.project_02.DB.UserAdapter;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityManageaccBinding;

import java.util.List;

public class ManageaccActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * 0.02.02.041823: added button function;
     * testing the live data, view model, and recycler view for managing the user accounts here;
     * implement repository, view model, and recycler view for managing the accounts;
     * insert the view with all the data needed;  delete item by swiping;
     * TODO  add confirmation screen after swiping with alert dialogue;
     *  add are you sure button in layout, something that pops up to confirm the deletion of a user( in the database Delete()) ;
     *  go through todo's start todoing everything else. setup to github?
     *  and luck button functionalities.
     *  TODO crashes when deleting user that is currently logged in. add that to cannot delete
     *  0.02.03.041923:
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

        /**     RECYCLER VIEW TESTING
         *
         */

        RecyclerView recyclerView = findViewById(R.id.recycler_view_manageacc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        UserAdapter adapter = new UserAdapter();
        recyclerView.setAdapter(adapter);

        // different than from reference video. got from: https://stackoverflow.com/questions/53903762/viewmodelproviders-is-deprecated-in-1-1-0
        user_viewmodel = new ViewModelProvider(this).get(UserViewModel.class);
        user_viewmodel.getAllUsers().observe(this, new Observer<List<UserEntity>>() {

            // triggered everytime the live data changes in the view model ( exactly what i want i think :D)
            @Override
            public void onChanged(List<UserEntity> userEntities) {
                adapter.setUsers(userEntities);

            }
        }); // this observes the view for our list of users entities, and when a change is detected, it updates the view.


        // ** SWIPING AN ITEM TO DELETE IT **
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                // TODO add the alert dialog to confirm if the user should be deleted or not.
                UserEntity temp_user = adapter.getUserAt(viewHolder.getAdapterPosition());
                String user_str = temp_user.getUser_nickname();
                // check if its one of the default users
                if ((user_str.compareTo("testuser1") == 0) || (user_str.compareTo("admin2")==0)) {
                    Toast.makeText(ManageaccActivity.this, "CANNOT DELETE DEFAULT USER", Toast.LENGTH_SHORT).show();
                    return;

                }

                user_viewmodel.Delete(adapter.getUserAt(viewHolder.getAdapterPosition()));
                Toast.makeText(ManageaccActivity.this, "'" + user_str + "' has been deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


    }



    // to switch to manage acc activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, ManageaccActivity.class);
    }
}