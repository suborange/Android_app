/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.05.041723
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.room.Entity;

import com.example.project_02.DB.AppDatabase;

@Entity(tableName = AppDatabase.SETS_TABLE)
public class SetsEntity {

    /**
     * 0.02.02.041823:  created and added as entity; added fields and implemented get/set methods;
     */

    private int session_id;
    private int sets_count;
    private int weight;
    private int reps_count;

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
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
