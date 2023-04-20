package com.example.project_02.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.project_02.GymJourney.UserEntity;
import com.example.project_02.GymJourney.WorkoutEntity;

import java.util.List;

/**
 *  SPECIFICALLY FOR CURRENT JOURNEY ACTIVITY, THE WORKOUT REPOSITORY
 *  got from: https://www.youtube.com/watch?v=HhmA9S53XV8&list=PLrnPJCHvNZuAPyh6nRXsvf5hF48SJWdJb&index=5
 */

public class WorkoutRepository {

    /** 0.02.03.041923: created repository to hold the user data info, with view model and recycle view;
     * implemented insert, update, delete, and delete all workouts;
     * implemented async tasks for all of those operations;
     * DONE?
     *
     */

    private myDAO DAO_workout_repo;
    private LiveData<List<WorkoutEntity>> allWorkouts;

    public WorkoutRepository (Application application) {
        AppDatabase database = AppDatabase.getInstance(application); // TOOD might cause errors here?
        DAO_workout_repo = database.getmyDAO();
        allWorkouts = DAO_workout_repo.QueryAllWorkouts();

    }

    // ** ASYNC TASKS TO HANDLE SQL OPERATIONS **
    public void Insert(WorkoutEntity entity) {
        new InsertWorkoutAsyncTask(DAO_workout_repo).execute(entity);

    }

    public void Update(WorkoutEntity entity) {
        new UpdateWorkoutAsyncTask(DAO_workout_repo).execute(entity);

    }

    public void Delete(WorkoutEntity entity) {
        new DeleteWorkoutAsyncTask(DAO_workout_repo).execute(entity);

    }

    public void DeleteAllWorkouts() {
        new DeleteAllWorkoutsAsyncTask(DAO_workout_repo).execute();

    }

    // this is called in the workout view model, using this repository
    public LiveData<List<WorkoutEntity>> getAllWorkouts() {
        return allWorkouts;
    }

    // ** ASYNC CLASSES AND TASKS TO BE USED FOR THE VIEW MODEL AND RECYCLE VIEW
    private static class InsertWorkoutAsyncTask extends AsyncTask<WorkoutEntity, Void, Void> {
        private myDAO DAO_insert;

        private InsertWorkoutAsyncTask ( myDAO dao) {
            this.DAO_insert = dao;
        }
        @Override
        protected Void doInBackground(WorkoutEntity... Entities) {

            DAO_insert.Insert(Entities[0]);
            return null;
        }
    }

    private static class UpdateWorkoutAsyncTask extends AsyncTask<WorkoutEntity, Void, Void> {
        private myDAO DAO_insert;

        private UpdateWorkoutAsyncTask ( myDAO dao) {
            this.DAO_insert = dao;
        }
        @Override
        protected Void doInBackground(WorkoutEntity... Entities) {

            DAO_insert.Update(Entities[0]);
            return null;
        }
    }

    private static class DeleteWorkoutAsyncTask extends AsyncTask<WorkoutEntity, Void, Void> {
        private myDAO DAO_insert;

        private DeleteWorkoutAsyncTask ( myDAO dao) {
            this.DAO_insert = dao;
        }
        @Override
        protected Void doInBackground(WorkoutEntity... Entities) {

            DAO_insert.Delete(Entities[0]);
            return null;
        }
    }

    private static class DeleteAllWorkoutsAsyncTask extends AsyncTask<Void, Void, Void> {
        private myDAO DAO_insert;

        private DeleteAllWorkoutsAsyncTask ( myDAO dao) {
            this.DAO_insert = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            DAO_insert.DeleteAllWorkouts();
            return null;
        }
    }

}
