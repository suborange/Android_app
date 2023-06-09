/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */

/** VERSIONS
 * 0.01.00.41023: updated build.gradle:module:app create packages, and db files, java files and xml files; get enough xml layouts for part 02;
 *
 */
package com.example.project_02.GymJourney;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_02.DB.AppDatabase;

@Entity(tableName = AppDatabase.WORKOUT_TABLE)
public class WorkoutEntity {
    /**
     * 0.01.00.41023: created and added as entity; added fields;
     */
    @PrimaryKey
    private int journey_id;

    private int User_ID;
    private int workout_ID;

}
