/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.05.041723
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.project_02.GymJourney.SessionEntity;
import com.example.project_02.GymJourney.UserEntity;
import com.example.project_02.GymJourney.WorkoutEntity;

@Database(entities = {UserEntity.class}, version = 2 ) // , SessionEntity.class, WorkoutEntity.class
public abstract class AppDatabase extends RoomDatabase {
    /**
     * 0.01.00.41023: created all constant variables for possible database as of this version.
     */

    public static final String DB_NAME = "JOURNEY_DB";
    public static final String JOURNEY_TABLE = "JOURNEY_TABLE"; // poss same as user
    public static final String USER_TABLE = "USER_TABLE"; // poss same as journey
    public static final String WORKOUT_TABLE = "WORKOUT_TABLE"; // the table of all the workouts created, keyed to one journey_id
    public static final String SESSION_TABLE = "SESSION_TABLE"; // the table of all the sessions created within one workout, keyed to one workout_id
    public static final String SETS_TABLE = "SETS_TABLE";
    public static final String LOGIN_STATE = "LOGIN_STATE"; //simply the state of the user, are they logged in already. or are they not logged in at all?

    public abstract myDAO getmyDAO(); // how does this even work? we never define.

}
