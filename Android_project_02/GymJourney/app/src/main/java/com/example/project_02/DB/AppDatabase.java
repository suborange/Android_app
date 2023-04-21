/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.05.041723
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.DB;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.project_02.GymJourney.SessionEntity;
import com.example.project_02.GymJourney.SetsEntity;
import com.example.project_02.GymJourney.UserEntity;
import com.example.project_02.GymJourney.WorkoutEntity;

@Database(entities = {UserEntity.class, WorkoutEntity.class, SessionEntity.class, SetsEntity.class}, version = 5 ) // , SessionEntity.class, WorkoutEntity.class
public abstract class AppDatabase extends RoomDatabase {
    /**
     * 0.01.00.41023: created all constant variables for possible database as of this version;
     * 0.01.05.041723: add sets table;
     */

    public static final String DB_NAME = "JOURNEY_DB";
    public static final String JOURNEY_TABLE = "JOURNEY_TABLE"; // poss same as user
    public static final String USER_TABLE = "USER_TABLE"; // poss same as journey
    public static final String WORKOUT_TABLE = "WORKOUT_TABLE"; // the table of all the workouts created, keyed to one journey_id
    public static final String SESSION_TABLE = "SESSION_TABLE"; // the table of all the sessions created within one workout, keyed to one workout_id
    public static final String SETS_TABLE = "SETS_TABLE";
    public static final String LOGIN_STATE = "LOGIN_STATE"; //simply the state of the user, are they logged in already. or are they not logged in at all?

    public abstract myDAO getmyDAO(); // how does this even work? we never define.


    // TODO testing purpose for this next step, got from: https://www.youtube.com/watch?v=HhmA9S53XV8&list=PLrnPJCHvNZuAPyh6nRXsvf5hF48SJWdJb&index=5
    // this might cause an issue, depending how the application/context works. this one will only grab the one context on creation.
    private static AppDatabase instance;
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null ) {
            instance = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    // .addCallback(room_callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback room_callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // new PopulateDbAsyncTask(instance).execute();


        }
    };

    private static  class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private myDAO DAO_populate;

        private PopulateDbAsyncTask(AppDatabase database) {
            DAO_populate = database.getmyDAO();
        }

        // To add stuff on creation. i think i will just leave it blank for now
        @Override
        protected Void doInBackground(Void... voids) {
            // DAO_populate.Insert(new UserEntity());
            return null;
        }
    }


}
