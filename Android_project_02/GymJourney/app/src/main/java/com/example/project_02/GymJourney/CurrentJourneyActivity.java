/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.02.02.041823
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.project_02.DB.AppDatabase;
import com.example.project_02.DB.WorkoutAdapter;
import com.example.project_02.DB.myDAO;
import com.example.project_02.R;
import com.example.project_02.databinding.ActivityCurrentJourneyBinding;

import java.util.List;

public class CurrentJourneyActivity extends AppCompatActivity {


    /** 0.01.05.041723: created theses activities: workout, add session, current session, current journey; create IntentFactory
     *  0.02.01.041723: query the logged user, grabs name and checks if its null or not. then it saves whatever data on every keystroke, so saves journey name correctly;
     * 0.02.04.042023: insert the view with all the data needed; add button adds new item;
     * swiping right on a workout should then take it to the workout activity
     *
     */

    private ActivityCurrentJourneyBinding binding_current_journey;
    String workout_name="";

    private WorkoutViewModel current_journey_viewmodel;

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
                // got this test from https://stackoverflow.com/questions/10903754/input-text-dialog-android

                AlertDialog.Builder builder = new AlertDialog.Builder(CurrentJourneyActivity.this);
                builder.setTitle("Enter the name for the workout.");

                final EditText input_workoutname = new EditText(getApplicationContext());
                input_workoutname.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input_workoutname);

                builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        workout_name = input_workoutname.getText().toString();
                        // TODO add new workout database item
                        if(workout_name.compareTo("") == 0) {
                            dialog.cancel(); // exit if its blank and toast | can maybe have it where if the name of any of the workouts is blank, a toast is made saying enter the name.
                            Toast.makeText(CurrentJourneyActivity.this, "INVALID. RETRY!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // then click and enter a name;
                            // get the user
                            UserEntity user_logged_in = DAO_current_journey.QueryLoggedinUser(true);

                            // get the user ID to now insert
                            WorkoutEntity new_workout = new WorkoutEntity(user_logged_in.getUser_ID()); // set the user ID for this new entry
                            new_workout.setWorkout_name(workout_name); // we grab the name on click, and add it to the database
                            // DAO_current_journey.Insert(new_workout); replace with new way to insert
                            current_journey_viewmodel.Insert(new_workout); // new way using viewmodel
                            // refresh the page? to hopefully get the live data
                            // https://stackoverflow.com/questions/1397361/how-to-restart-activity-in-android
                            Intent refresh = getIntent();
                            finish();
                            startActivity(refresh);
                        }


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();;
                    }
                });

                AlertDialog goodAlert = builder.create();
                goodAlert.show();



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
        current_journey_viewmodel = new ViewModelProvider(CurrentJourneyActivity.this).get(WorkoutViewModel.class);
        current_journey_viewmodel.getAllWorkouts().observe(CurrentJourneyActivity.this, new Observer<List<WorkoutEntity>>() {

            // triggered everytime the live data changes in the view model ( exactly what i want i think :D)
            @Override
            public void onChanged(List<WorkoutEntity> Entities) {
                adapter.setWorkouts(Entities);

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

                // TODO make this select and change the intent to session activity, and setting this workout active
                WorkoutEntity temp_workout = adapter.getWorkoutAt(viewHolder.getAdapterPosition());
                temp_workout.setIs_active(true);
                current_journey_viewmodel.Update(temp_workout);

                Intent workout_activity = WorkoutActivity.IntentFactory(getApplicationContext());
                startActivity(workout_activity);

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
                WorkoutEntity temp_workout = adapter.getWorkoutAt(viewHolder.getAdapterPosition());
                String workout_str = temp_workout.getWorkout_name();



                current_journey_viewmodel.Delete(adapter.getWorkoutAt(viewHolder.getAdapterPosition()));
                Toast.makeText(CurrentJourneyActivity.this, "'" + workout_str + "' has been deleted", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);


    }


    // to switch to current journey activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, CurrentJourneyActivity.class);
    }


}