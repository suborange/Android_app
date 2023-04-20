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
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.SessionsAdapter;
import com.example.project_02.DB.WorkoutAdapter;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityWorkoutBinding;

import java.util.List;


public class WorkoutActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current workout; create IntentFactory
     * 0.02.04.042023: display the user workout name; display the view of all the workout view data from video;
     *  button should be used to add a session, taking to add session activity;
     *  todo sliding right on a session takes user to the current session activity;
     *
     */

    private ActivityWorkoutBinding binding_workout;

    private SessionViewModel workout_viewmodel;

    TextView text_workout_name;
    Button button_add_session;
    Button button_goback;

    //DAO
    myDAO DAO_workout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        // inflate binding layoutr and set view
        binding_workout = ActivityWorkoutBinding.inflate(getLayoutInflater());
        setContentView(binding_workout.getRoot());

        button_add_session = binding_workout.addsessionButton;
        text_workout_name = binding_workout.workoutNameText;
        button_goback = binding_workout.gobackButton;

        // get DAO singletong for this activity
        DAO_workout = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO();

        WorkoutEntity temp = DAO_workout.QueryActiveWorkout(true);
        String workout_name = temp.getWorkout_name();
        text_workout_name.setText(workout_name);




        // ** ADD SESSION **
        button_add_session.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // add a session here
                Intent add_session_activity = AddSessionActivity.IntentFactory(getApplicationContext());
                startActivity(add_session_activity);

            }
        });

        //** GO BACK BUTTON 88
        button_goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // write to un activate the workout, by going back
                WorkoutEntity temp_workout = DAO_workout.QueryActiveWorkout(true);
                temp_workout.setIs_active(false);
                DAO_workout.Update(temp_workout);
                // go back to the journey page
                Intent current_journey = CurrentJourneyActivity.IntentFactory(getApplicationContext());
                startActivity(current_journey);

            }
        });


        /**     RECYCLER VIEW TESTING
         *
         */

        RecyclerView recyclerView = findViewById(R.id.recycler_view_session);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        SessionsAdapter adapter = new SessionsAdapter();
        recyclerView.setAdapter(adapter);

        // different than from reference video. got from: https://stackoverflow.com/questions/53903762/viewmodelproviders-is-deprecated-in-1-1-0
        workout_viewmodel = new ViewModelProvider(WorkoutActivity.this).get(SessionViewModel.class);
        workout_viewmodel.getAllSessions().observe(WorkoutActivity.this, new Observer<List<SessionEntity>>() {

            // triggered everytime the live data changes in the view model ( exactly what i want i think :D)
            @Override
            public void onChanged(List<SessionEntity> Entities) {
                adapter.setSessions(Entities);

            }
        }); // this observes the view for our list of users entities, and when a change is detected, it updates the view.


        // ** SWIPING AN ITEM TO ACCESS IT **
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                // need to open up the sets activity for this view page
                Intent sets_activity = SetsActivity.IntentFactory(getApplicationContext());
                startActivity(sets_activity);

            }
        }).attachToRecyclerView(recyclerView);

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
                SessionEntity temp_session = adapter.getSessionAt(viewHolder.getAdapterPosition());
                String workout_str = String.valueOf(temp_session.getSession_ID());



                workout_viewmodel.Delete(adapter.getSessionAt(viewHolder.getAdapterPosition()));
                Toast.makeText(WorkoutActivity.this, "'" + workout_str + "' deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);





    }

    // to switch to workout activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, WorkoutActivity.class);
    }

}