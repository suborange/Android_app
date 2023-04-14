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

// TODO do i really need this? if its one journey i think i can just delete this entity?
@Entity(tableName = AppDatabase.JOURNEY_TABLE)
public class JourneyEntity {
    /**
     * 0.01.00.41023: created and added as entity; added fields;
     */


    @PrimaryKey (autoGenerate = true)
    private int db_journey_ID;



    JourneyEntity() {

    }
}
