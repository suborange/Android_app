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
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.CurrentSessionAdapter;
import com.example.project_02.DB.SessionsAdapter;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityCurrentSessionBinding;

import java.util.List;

// BEFORE workout activity
// AFTER current set activity
public class CurrentSessionActivity extends AppCompatActivity {

    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     * 0.02.05.042023:
     * TODO display both the workout name and the session id name; line up the header with the data view;
     * the top buttons select which number to change, the bottom buttons increase which ever was selected to change;
     * display and refresh these displayed numbers.
     *
     */

    private ActivityCurrentSessionBinding binding_currsesh;
    private CurrentSessionViewModel session_viewmodel;

    TextView text_workout_name;

    //DAO
    myDAO DAO_session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_session);
        // inflate binding layout and set content view
        binding_currsesh = ActivityCurrentSessionBinding.inflate(getLayoutInflater());
        setContentView(binding_currsesh.getRoot());

        text_workout_name = binding_currsesh.workoutNameText;

        DAO_session = Room.databaseBuilder(this, AppDatabase.class,AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO();

        // get active session
        SessionEntity session = DAO_session.QueryActiveSession(true);

        // get the set data for it all and set it to the recycler view stuff;





        /**     RECYCLER VIEW TESTING
         *
         */

        RecyclerView recyclerView = findViewById(R.id.recycler_view_session);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        CurrentSessionAdapter adapter = new CurrentSessionAdapter();
        recyclerView.setAdapter(adapter);

        // different than from reference video. got from: https://stackoverflow.com/questions/53903762/viewmodelproviders-is-deprecated-in-1-1-0
        session_viewmodel = new ViewModelProvider(CurrentSessionActivity.this).get(CurrentSessionViewModel.class);
        session_viewmodel.getAllCurrSessions().observe(CurrentSessionActivity.this, new Observer<List<SessionEntity>>() {

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
                // recyclerView.setBackgroundColor(Color.parseColor("#1434A4"));
//                SessionEntity activate_session = adapter.getSessionAt(viewHolder.getAdapterPosition());
//                activate_session.setIs_active(true); // now we have the active session!!

                // need to open up the session activity for this view page
                Intent session_activity = CurrentSessionActivity.IntentFactory(getApplicationContext());
                startActivity(session_activity);

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
                // recyclerView.setBackgroundColor(Color.parseColor("#FAF8D9"));


                // TODO add the alert dialog to confirm if the user should be deleted or not.
                SessionEntity temp_session = adapter.getSessionAt(viewHolder.getAdapterPosition());
                String workout_str = String.valueOf(temp_session.getSession_ID());



                workout_viewmodel.Delete(adapter.getSessionAt(viewHolder.getAdapterPosition()));
                Toast.makeText(WorkoutActivity.this, "'" + workout_str + "' deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);




    }

    // to switch to current session activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, CurrentSessionActivity.class);
    }


}