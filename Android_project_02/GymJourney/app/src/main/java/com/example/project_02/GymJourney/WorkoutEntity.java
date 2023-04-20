/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.02.02.041823
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_02.DB.AppDatabase;

@Entity(tableName = AppDatabase.WORKOUT_TABLE)
public class WorkoutEntity {
    /**
     * 0.01.00.41023: created and added as entity; added fields;
     * 0.02.01.041723: finalized fields, user ID and PK.workout ID; corrected structure;
     * 0.02.03.041923: added is active field
     *
     */

    @PrimaryKey(autoGenerate = true)
    private int workout_ID;

    // not unique,nor auto generated, grabbed from the logged in user entity
    private int User_ID;
    // private String journey_goal;
    private String workout_name;
    private boolean is_active;

    public WorkoutEntity() {

    }

    public WorkoutEntity(int User_ID) {
        this.User_ID = User_ID;
        this.is_active = false; // default to an non active workout state?

    }


    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        this.User_ID = user_ID;
    }

    public int getWorkout_ID() {
        return workout_ID;
    }

    public void setWorkout_ID(int workout_ID) {
        this.workout_ID = workout_ID;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }
}
