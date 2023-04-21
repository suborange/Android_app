package com.example.project_02.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;


import com.example.project_02.GymJourney.SessionEntity;
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
    //private WorkoutEntity TheseWorkouts;
    //private LiveData<List<SessionEntity>> thisWorkouts;

    public WorkoutRepository (Application application) {
        AppDatabase database = AppDatabase.getInstance(application); // TOOD might cause errors here?
        DAO_workout_repo = database.getmyDAO();
        allWorkouts = DAO_workout_repo.QueryAllWorkouts();
        //TheseWorkouts = DAO_workout_repo.QueryActiveWorkout(true);
        //thisWorkouts = DAO_workout_repo.QueryThisSession(TheseWorkouts.getWorkout_ID());

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

//    public LiveData<List<SessionEntity>> getThisWorkout() {
//
//        return thisWorkouts;
//    }

    // ** ASYNC CLASSES AND TASKS TO BE USED FOR THE VIEW MODEL AND RECYCLE VIEW
    private static class InsertWorkoutAsyncTask extends AsyncTask<WorkoutEntity, Void, Void> {
        private myDAO DAO;

        private InsertWorkoutAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(WorkoutEntity... Entities) {

            DAO.Insert(Entities[0]);
            return null;
        }
    }

    private static class UpdateWorkoutAsyncTask extends AsyncTask<WorkoutEntity, Void, Void> {
        private myDAO DAO;

        private UpdateWorkoutAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(WorkoutEntity... Entities) {

            DAO.Update(Entities[0]);
            return null;
        }
    }

    private static class DeleteWorkoutAsyncTask extends AsyncTask<WorkoutEntity, Void, Void> {
        private myDAO DAO;

        private DeleteWorkoutAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(WorkoutEntity... Entities) {

            DAO.Delete(Entities[0]);
            return null;
        }
    }

    private static class DeleteAllWorkoutsAsyncTask extends AsyncTask<Void, Void, Void> {
        private myDAO DAO;

        private DeleteAllWorkoutsAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            DAO.DeleteAllWorkouts();
            return null;
        }
    }

}
