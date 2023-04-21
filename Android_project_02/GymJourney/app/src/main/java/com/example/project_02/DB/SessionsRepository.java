package com.example.project_02.DB;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.project_02.GymJourney.SessionEntity;
import com.example.project_02.GymJourney.WorkoutEntity;

import java.util.List;

/**
 *  SPECIFICALLY FOR WORKOUT ACTIVITY, THE SESSIONS REPOSITORY
 *  got from: https://www.youtube.com/watch?v=HhmA9S53XV8&list=PLrnPJCHvNZuAPyh6nRXsvf5hF48SJWdJb&index=5
 */

public class SessionsRepository {


    /** 0.02.04.042023: created repository to hold the user data info, with view model and recycle view;
     *  implemented insert, update, delete, and delete all sessions;
     *  implemented async tasks for all of those operations;
     *
     */

    private myDAO DAO_session_repo;
    private LiveData<List<SessionEntity>> allSessions;


    public SessionsRepository (Application application) {
        AppDatabase database = AppDatabase.getInstance(application); // TOOD might cause errors here?
        DAO_session_repo = database.getmyDAO();
        //WorkoutEntity temp = DAO_session_repo.QueryActiveWorkout(true);
        //allSessions = DAO_session_repo.QueryThisSession(temp.getWorkout_ID());
        allSessions =DAO_session_repo.QueryAllSessions();

    }


    // ** ASYNC TASKS TO HANDLE SQL OPERATIONS **
    public void Insert(SessionEntity entity) {
        new SessionsRepository.InsertSessionAsyncTask(DAO_session_repo).execute(entity);

    }

    public void Update(SessionEntity entity) {
        new SessionsRepository.UpdateSessionAsyncTask(DAO_session_repo).execute(entity);

    }

    public void Delete(SessionEntity entity) {
        new SessionsRepository.DeleteSessionAsyncTask(DAO_session_repo).execute(entity);

    }

    public void DeleteAllSessions() {
        new SessionsRepository.DeleteAllSessionsAsyncTask(DAO_session_repo).execute();

    }

    // this is called in the session view model, using this repository
    public LiveData<List<SessionEntity>> getAllSessions() {
        return allSessions;
    }

    // ** ASYNC CLASSES AND TASKS TO BE USED FOR THE VIEW MODEL AND RECYCLE VIEW
    private static class InsertSessionAsyncTask extends AsyncTask<SessionEntity, Void, Void> {
        private myDAO DAO;

        private InsertSessionAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(SessionEntity... Entities) {

            DAO.Insert(Entities[0]);
            return null;
        }
    }

    private static class UpdateSessionAsyncTask extends AsyncTask<SessionEntity, Void, Void> {
        private myDAO DAO;

        private UpdateSessionAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(SessionEntity... Entities) {

            DAO.Update(Entities[0]);
            return null;
        }
    }

    private static class DeleteSessionAsyncTask extends AsyncTask<SessionEntity, Void, Void> {
        private myDAO DAO;

        private DeleteSessionAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(SessionEntity... Entities) {

            DAO.Delete(Entities[0]);
            return null;
        }
    }

    private static class DeleteAllSessionsAsyncTask extends AsyncTask<Void, Void, Void> {
        private myDAO DAO;

        private DeleteAllSessionsAsyncTask ( myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            DAO.DeleteAllSessions();
            return null;
        }
    }



}
