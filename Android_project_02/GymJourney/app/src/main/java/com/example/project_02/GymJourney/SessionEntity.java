/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.02.02.041823
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import android.se.omapi.Session;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_02.DB.AppDatabase;

@Entity(tableName = AppDatabase.SESSION_TABLE)
public class SessionEntity {
    /**
     * 0.01.00.41023: created and added as entity; added fields;
     * 0.02.01.041723: correct structure, PK.sessionid, workout id, workout name, and sets count, implemented get/set methods; added sets entity;
     * 0.02.04.042023: added constructor, to setup workout id and sets count;
     */

    @PrimaryKey(autoGenerate = true)
    private int session_ID;

    // gunna be grabbed from the workout table
    private int workout_ID;
    private int sets_count;
    private int weight;
    private int reps_count;
    boolean is_active;

    public SessionEntity () {

    }

    public SessionEntity (int workout_ID, int sets_count, int weight, int reps) {
        this.workout_ID = workout_ID;
        this.sets_count = sets_count;
        this.weight = weight;
        this.reps_count = reps;
        this.is_active = false;

    }



    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public void setSession_ID(int session_ID) {
        this.session_ID = session_ID;
    }

    public int getSession_ID() {
        return session_ID;
    }

    public int getWorkout_ID() {
        return workout_ID;
    }

    public void setWorkout_ID(int workout_ID) {
        this.workout_ID = workout_ID;
    }

    public int getSets_count() {
        return sets_count;
    }

    public void setSets_count(int sets_count) {
        this.sets_count = sets_count;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getReps_count() {
        return reps_count;
    }

    public void setReps_count(int reps_count) {
        this.reps_count = reps_count;
    }
}
