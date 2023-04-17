/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.05.041723
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_02.DB.AppDatabase;

@Entity(tableName = AppDatabase.SESSION_TABLE)
public class SessionEntity {
    /**
     * 0.01.00.41023: created and added as entity; added fields;
     */

    @PrimaryKey
    private int workout_ID;

    private int session_ID;
    private String workout_name;
    private int wieght_min;
    private int weight_max;
    private int sets_count;
    private int reps_count;
    private boolean improved;


}
