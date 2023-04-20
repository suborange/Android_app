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
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.UserAdapter;
import com.example.project_02.DB.WorkoutAdapter;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityCurrentJourneyBinding;

import java.util.List;

public class CurrentJourneyActivity extends AppCompatActivity {


    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     *  0.02.01.041723: query the logged user, grabs name and checks if its null or not. then it saves whatever data on every keystroke, so saves journey name correctly;
     *
     *
     * TODO insert the view with all the data needed; add button adds new item, delete item by name?
     *  selecting a workout should then take it to the workout activity.
     *  idea: make select workout a click event, and go into the workout by long clicking.
     *  or just make two buttons, to add one or delete one by name?
     *
     */

    private ActivityCurrentJourneyBinding binding_current_journey;
    private boolean has_null_name;

    private WorkoutViewModel workout_viewmodel;

    EditText edit_journey_name_field;
    Button button_add_workout;
    Button button_go_back;

        // DAO
    myDAO DAO_current_journey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_journey);
        // inflate layout and set content view
        binding_current_journey = ActivityCurrentJourneyBinding.inflate(getLayoutInflater());
        setContentView(binding_current_journey.getRoot());

        edit_journey_name_field = binding_current_journey.journeyNameField;
        button_add_workout = binding_current_journey.addworkoutButton;
        button_go_back = binding_current_journey.gobackButton;

        // get DAO singleton for this activity
        DAO_current_journey = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME)
                .allowMainThreadQueries()
                .build()
                .getmyDAO();


        // TODO need to add something for when the database has a value for the journey name. if it does, then  set the edittext to that text



        UserEntity user_loggedin = DAO_current_journey.QueryLoggedinUser(true); // get the logged in user
        // if null, then create an entity for this new user, leaving the journey_name as blank
        if (user_loggedin.getJourney_name().compareTo("") == 0) {
            // say something to get user to add there journey name!
            Toast.makeText(this, "Please add a journey name!", Toast.LENGTH_SHORT).show();

        }
        else { // if exists, then there is a journey created for this user, so grab the information for the name to display on the edittext field
            // get the logged in users name, and then set it to the edittext field on creation
            //UserEntity user = DAO_current_journey.QueryLoggedinUser(true); // get the logged in user
            String db_name = user_loggedin.getJourney_name();
            edit_journey_name_field.setText(db_name);

        }
        // this should handle the name of the journey atop, with the add text listener

        // later possibly add the journey goal


        /** maybe should only have the ADD button, and wipe will be to delete. a small note can be given to direct that action
         *
         */
        // ** ADD BUTTON **
        button_add_workout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO find a way to add a new workout, maybe it adds to the data view thing, and has a hint that says Enter workout name.
                // TODO add new workout database item
                // then click and enter a name; can maybe have it where if the name of any of the workouts is blank, a toast is made saying enter the name.
                // so cant be clicked or selected until the name is something as it is saved in the database.


            }
        }); // we are overriding this one specific method from View


        // ** GO BACK TO USER ACTIVITY BUTTON **
        button_go_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user_activity = UserActivity.IntentFactory(getApplicationContext());
                startActivity(user_activity);

            }
        });

        // ** EDIT TEXT JOURNEY NAME **
        edit_journey_name_field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODID guess add it all here when the text is changed
                // get the logged in user
                UserEntity user_loggedin = DAO_current_journey.QueryLoggedinUser(true);
                //  first grab the {journey_name} from the database, then
                String db_journey_name; // = user_loggedin.getJourney_name();
                // if empty string, toast to add a new name please!
                if ( s.toString().compareTo("") == 0 ) {
                    Toast.makeText(CurrentJourneyActivity.this, "Enter journey name atop!", Toast.LENGTH_SHORT).show();

                }
                // if its empty, also make it blank, otherwise it will leave the last character. this is a dumb case i shouldnt care about for now. otherwise:
                // assign the text from the editfield, after a change is made to update the database of this name
                db_journey_name = edit_journey_name_field.getText().toString();
                // set the new name to our object
                user_loggedin.setJourney_name(db_journey_name);
                //  then update the database with this newly formed String. eventually will stop editing and so last saved is what it is
                DAO_current_journey.Update(user_loggedin);
                // should have to set text, as it should stay  until screen changes, which is handled onCreate() ( but maybe can do it anyway)
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        }); // so this is that pattern, creating a new instance inside the argument. so text watcher is abstract? so it forces the override on all these methods




        /**     RECYCLER VIEW TESTING
         *
         */

        RecyclerView recyclerView = findViewById(R.id.recycler_view_workout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        WorkoutAdapter adapter = new WorkoutAdapter();
        recyclerView.setAdapter(adapter);

        // different than from reference video. got from: https://stackoverflow.com/questions/53903762/viewmodelproviders-is-deprecated-in-1-1-0
        workout_viewmodel = new ViewModelProvider(this).get(WorkoutViewModel.class);
        workout_viewmodel.getAllWorkouts().observe(this, new Observer<List<WorkoutEntity>>() {

            // triggered everytime the live data changes in the view model ( exactly what i want i think :D)
            @Override
            public void onChanged(List<WorkoutEntity> Entities) {
                adapter.setWorkouts(Entities);

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
                WorkoutEntity temp_workout = adapter.getWorkoutAt(viewHolder.getAdapterPosition());
                String workout_str = temp_workout.getWorkout_name();



                workout_viewmodel.Delete(adapter.getWorkoutAt(viewHolder.getAdapterPosition()));
                Toast.makeText(CurrentJourneyActivity.this, "'" + workout_str + "' has been deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


    }


    // to switch to current journey activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, CurrentJourneyActivity.class);
    }


}