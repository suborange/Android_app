/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.03.041323:
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

// import com.example.project_02.GymJourney.JourneyEntity;
import com.example.project_02.GymJourney.SessionEntity;
import com.example.project_02.GymJourney.UserEntity;
import com.example.project_02.GymJourney.WorkoutEntity;

import java.util.List;

@Dao
public interface myDAO {
    /**
     * 0.01.00.41023: added insert,update, and delete for entities; add one basic query to get all results from a table; User, Workout, and Session Entity;
     * 0.01.03.041323: added test queries to check for existing or non-existing users, based on log in or user name; COULD POSSIBLY FAIL
     *
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

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE EXISTS (SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE logged_in = :log ) ")
    boolean TestExistenceOfUsers(boolean log);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE EXISTS (SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE user_nickname = :user_name) ")
    boolean TestExistenceOfName(String user_name);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE EXISTS (SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE user_nickname = :user_name AND user_nickname = :user_name2) ")
    boolean TestExistenceOfNames(String user_name, String user_name2);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE) // need any ordering here???*
    List<UserEntity> QueryAllUsers(); // what to be returned form this query search?
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
     *//*
    @Insert
    void Insert(WorkoutEntity... workoutEntities); // insert an entity

    @Update
    void Update(WorkoutEntity... workoutEntities); // update an entity

    @Delete
    void Delete(WorkoutEntity... workoutEntities); // delete an entity

    @Query("SELECT * FROM " + AppDatabase.WORKOUT_TABLE) // need any ordering here???
    List<WorkoutEntity> getWorkoutData(); // what to be returned form this query search?


    *//**
     *          SESSION ENTITY
     *//*
    @Insert
    void Insert(SessionEntity... sessionEntities); // insert an entity

    @Update
    void Update(SessionEntity... sessionEntities); // update an entity

    @Delete
    void Delete(SessionEntity... sessionEntities); // delete an entity

    @Query("SELECT * FROM " + AppDatabase.SESSION_TABLE) // need any ordering here???
    List<SessionEntity> getSessionData(); // what to be returned form this query search?*/

}
