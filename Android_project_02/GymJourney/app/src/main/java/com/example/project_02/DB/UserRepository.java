/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.02.02.041823
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import com.example.project_02.GymJourney.UserEntity;

import java.util.List;


/**
 *  SPECIFICALLY FOR ADMIN ACTIVITY, THE USER REPOSITORY
 *  got from: https://www.youtube.com/watch?v=HhmA9S53XV8&list=PLrnPJCHvNZuAPyh6nRXsvf5hF48SJWdJb&index=5
 */
public class UserRepository {

    /** 0.02.02.041823: created repository to hold the user data info, with view model and recycle view;
     * updated dependencies in gradle file for all this; implemented insert, update, delete, and delete all users;
     * implemented async tasks for all of those operations;
     *
     */

    private myDAO DAO_user_repo;
    private LiveData<List<UserEntity>> allUsers; // for admin page, to get all the users?

    public UserRepository (Application application) {

        AppDatabase database = AppDatabase.getInstance(application);
        DAO_user_repo = database.getmyDAO();
        allUsers = DAO_user_repo.QueryAllUsers();
    }

    // ** ASYNC TASKS TO HANDLE SQL OPERATIONS **
    public void Insert(UserEntity user) {
        new InsertUserAsyncTask(DAO_user_repo).execute(user);
    }

    public void Update(UserEntity user) {
        new UpdateUserAsyncTask(DAO_user_repo).execute(user);
    }

    public void Delete(UserEntity user) {
        new DeleteUserAsyncTask(DAO_user_repo).execute(user);
    }

    public void DeleteAllUsers() {
        new DeleteAllUserAsyncTask(DAO_user_repo).execute();
    }


    // this is called in the user view model, using this repository
    public LiveData<List<UserEntity>> getAllUsers() {
        return allUsers;
    }

    // ** ASYNC CLASSES AND TASKS TO BE USED FOR THE VIEW MODEL AND RECYCLE VIEW
    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private myDAO DAO;

        private InsertUserAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(UserEntity... userEntities) {

            DAO.Insert(userEntities[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private myDAO DAO;

        private UpdateUserAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(UserEntity... userEntities) {

            DAO.Update(userEntities[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private myDAO DAO;

        private DeleteUserAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(UserEntity... userEntities) {

            DAO.Delete(userEntities[0]);
            return null;
        }
    }

    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void> {
        private myDAO DAO;

        private DeleteAllUserAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            DAO.DeleteAllUsers();
            return null;
        }
    }


}
