package com.example.project_02.DB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.project_02.GymJourney.SessionEntity;
import com.example.project_02.GymJourney.SetsEntity;

import java.util.List;

/**
 *  SPECIFICALLY FOR CURRENT SESSION ACTIVITY, THE CURRENT SESSION REPOSITORY
 *  got from: https://www.youtube.com/watch?v=HhmA9S53XV8&list=PLrnPJCHvNZuAPyh6nRXsvf5hF48SJWdJb&index=5
 */
public class CurrentSessionRepository {

    /** 0.02.05.042023: created and implemented this repository for the session
     *
     */

    private myDAO DAO_session_repo;
    private LiveData<List<SetsEntity>> allSessions;

    public CurrentSessionRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application); // TOOD might cause errors here?
        DAO_session_repo = database.getmyDAO();
        allSessions = DAO_session_repo.QueryAllSets();

    }

    // ** ASYNC TASKS TO HANDLE SQL OPERATIONS **
    public void Insert(SetsEntity entity) {
        new CurrentSessionRepository.InsertSessionAsyncTask(DAO_session_repo).execute(entity);

    }

    public void Update(SetsEntity entity) {
        new CurrentSessionRepository.UpdateSessionAsyncTask(DAO_session_repo).execute(entity);

    }

    public void Delete(SetsEntity entity) {
        new CurrentSessionRepository.DeleteSessionAsyncTask(DAO_session_repo).execute(entity);

    }

    public void DeleteAllWorkouts() {
        new CurrentSessionRepository.DeleteAllSessionsAsyncTask(DAO_session_repo).execute();

    }

    // this is called in the current session view model, using this repository
    public LiveData<List<SetsEntity>> getAllCurrSessions() {
        return allSessions;
    }

    // ** ASYNC CLASSES AND TASKS TO BE USED FOR THE VIEW MODEL AND RECYCLE VIEW
    private static class InsertSessionAsyncTask extends AsyncTask<SetsEntity, Void, Void> {
        private myDAO DAO;

        private InsertSessionAsyncTask(myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(SetsEntity... Entities) {

            DAO.Insert(Entities[0]);
            return null;
        }
    }

    private static class UpdateSessionAsyncTask extends AsyncTask<SetsEntity, Void, Void> {
        private myDAO DAO;

        private UpdateSessionAsyncTask(myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(SetsEntity... Entities) {

            DAO.Update(Entities[0]);
            return null;
        }
    }

    private static class DeleteSessionAsyncTask extends AsyncTask<SetsEntity, Void, Void> {
        private myDAO DAO;

        private DeleteSessionAsyncTask(myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(SetsEntity... Entities) {

            DAO.Delete(Entities[0]);
            return null;
        }
    }

    private static class DeleteAllSessionsAsyncTask extends AsyncTask<Void, Void, Void> {
        private myDAO DAO;

        private DeleteAllSessionsAsyncTask(myDAO dao) {
            this.DAO = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            DAO.DeleteAllSessions();
            return null;
        }
    }
}
