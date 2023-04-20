package com.example.project_02.GymJourney;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_02.DB.SessionsRepository;
import com.example.project_02.DB.WorkoutRepository;

import java.util.List;

/**     SPECIFICALLY FOR WORKOUT VIEW
 *
 */
public class SessionViewModel extends AndroidViewModel {

    /** 0.02.04.042023: created file;  implemented all methods for insert, update, delete, delete all, and get all sessions;
     *
     */

    private SessionsRepository sessions_repo;
    private LiveData<List<SessionEntity>> all_sessions;

    public SessionViewModel(@NonNull Application application) {
        super(application);
        sessions_repo = new SessionsRepository(application);
        all_sessions = sessions_repo.getAllSessions(); // from session repository
    }

    public void Insert(SessionEntity entity) {
        sessions_repo.Insert(entity);
    }

    public void Update(SessionEntity entity) {
        sessions_repo.Update(entity);
    }

    public void Delete(SessionEntity entity) {
        sessions_repo.Delete(entity);
    }

    public void DeleteAllUsers() {
        sessions_repo.DeleteAllSessions();
    }

    public LiveData<List<SessionEntity>> getAllSessions() {
        return all_sessions;
    }
}
