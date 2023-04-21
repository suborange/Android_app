/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.03.041323:
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.DB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

// import com.example.project_02.GymJourney.JourneyEntity;
import com.example.project_02.GymJourney.SessionEntity;
import com.example.project_02.GymJourney.SetsEntity;
import com.example.project_02.GymJourney.UserEntity;
import com.example.project_02.GymJourney.WorkoutEntity;

import java.util.List;

@Dao
public interface myDAO {
    /**
     * 0.01.00.41023: added insert,update, and delete for entities; add one basic query to get all results from a table; User, Workout, and Session Entity;
     * 0.01.03.041323: added test queries to check for existing or non-existing users, based on log in or user name; COULD POSSIBLY FAIL
     * 0.02.01.041723: add workout entity and its queries;
     *
     * idea: make another entity with only thing, it will hold the value of the last logged in user 0 >= and so -1 would be logged out, opr something.
     * save their use id in that other table, on load in check that table to see if a user, which user, is still logged in? way less memory and databse management?
     *
     */

    /**
     *          USER ENTITY
     */
    // look into EXITS and NOT EXISTS keywords for non existent items
    // look into IF keyword for booleans on comparisons in queries
    @Insert
    void Insert(UserEntity... userEntities); // insert an entity

    @Update
    void Update(UserEntity... userEntities); // update an entity

    @Delete
    void Delete(UserEntity... userEntities); // delete an entity


    // Delete all the users ( like for an admin)
    @Query("DELETE FROM " + AppDatabase.USER_TABLE)
    void DeleteAllUsers();

    // TEST FOR IF USERS EXIST THAT ARE LOGGED IN
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE EXISTS (SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE logged_in = :log ) ")
    boolean TestExistenceOfUsers(boolean log);

    // TEST FOR IF USER THAT IS LOGGED IN, HAS A JOURNEY NAME OR IS IT BLANK.
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE EXISTS (SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE journey_name = :null_name ) ")
    boolean TestUserJourneyName(String null_name);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE EXISTS (SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE user_nickname = :user_name) ")
    boolean TestExistenceOfName(String user_name);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE EXISTS (SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE user_nickname = :user_name AND user_nickname = :user_name2) ")
    boolean TestExistenceOfNames(String user_name, String user_name2);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " ORDER BY User_ID ASC") // need any ordering here???*
    LiveData<List<UserEntity>> QueryAllUsers(); // what to be returned form this query search?
    // query the user with this id typed in the user_id edittext field
    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE user_nickname = :Userid_name")
    UserEntity QueryThisUser(String Userid_name);

   /* @Query("SELECT logged_in FROM " + AppDatabase.USER_TABLE + " WHERE logged_in = :log ")
    List<UserEntity> getLoggedinUsers(boolean log); // query the users with logged_in = true*/

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE logged_in = :log ")
    UserEntity QueryLoggedinUser(boolean log); // query the users with logged_in = true



/*
    *//**
     *          WORKOUT ENTITY
     */
    @Insert
    void Insert(WorkoutEntity... workoutEntities); // insert an entity

    @Update
    void Update(WorkoutEntity... workoutEntities); // update an entity

    @Delete
    void Delete(WorkoutEntity... workoutEntities); // delete an entity

    @Query("SELECT * FROM " + AppDatabase.WORKOUT_TABLE + " ORDER BY workout_ID ASC") // need any ordering here???*
    LiveData<List<WorkoutEntity>> QueryAllWorkouts(); // what to be returned form this query search?

    // find the entity with this workout ID ( rather by name )
    @Query("SELECT * FROM " + AppDatabase.WORKOUT_TABLE + " WHERE workout_ID = :ID")
    WorkoutEntity QueryThisWorkoutID(int ID);

    // find the active workout
    @Query("SELECT * FROM " + AppDatabase.WORKOUT_TABLE + " WHERE is_active = :active ")
    WorkoutEntity QueryActiveWorkout(boolean active); // query the users with logged_in = true

    // Delete all the users ( like for an admin)
    @Query("DELETE FROM " + AppDatabase.WORKOUT_TABLE)
    void DeleteAllWorkouts();


    // to query for when the user needs the workout name;
//    @Query ("SELECT * FROM " + AppDatabase.WORKOUT_TABLE + " WHERE ")
//    WorkoutEntity getWorkoutName();


    /**
     *          SESSION ENTITY
     */
    @Insert
    void Insert(SessionEntity... sessionEntities); // insert an entity

    @Update
    void Update(SessionEntity... sessionEntities); // update an entity

    @Delete
    void Delete(SessionEntity... sessionEntities); // delete an entity

    @Query("SELECT * FROM " + AppDatabase.SESSION_TABLE) // need any ordering here???
    LiveData<List<SessionEntity>> QueryAllSessions(); // what to be returned form this query search?

    @Query("DELETE FROM " + AppDatabase.SESSION_TABLE) // need any ordering here???
    void DeleteAllSessions(); // what to be returned form this query search?

    @Query("SELECT * FROM " + AppDatabase.SESSION_TABLE+ " WHERE workout_ID = :id")
    LiveData<List<SessionEntity>> QueryThisSession(int id);



//
//    /**
//     *         SETS ENTITY
//     */
//    @Insert
//    void Insert(SetsEntity ... setsEntities); // insert an entity
//
//    @Update
//    void Update(SetsEntity ... setsEntities); // update an entity
//
//    @Delete
//    void Delete(SetsEntity ... setsEntities); // delete an entity
//
//    @Query("SELECT * FROM " + AppDatabase.SETS_TABLE) // need any ordering here???
//    LiveData<List<SetsEntity>> QueryAllSets(); // what to be returned form this query search?



}
