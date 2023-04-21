package com.example.project_02.GymJourney;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_02.DB.CurrentSessionRepository;
import com.example.project_02.DB.WorkoutRepository;

import java.util.List;

/**     SPECIFICALLY FOR CURRENT SESSION VIEW
 *
 */
public class CurrentSessionViewModel extends AndroidViewModel {

    /** 0.02.05.042023: created and implemented this view model for the session
     *
     */

    private CurrentSessionRepository session_repo;
    private LiveData<List<SessionEntity>> all_Sessions;

    public CurrentSessionViewModel(@NonNull Application application) {
        super(application);
        session_repo = new CurrentSessionRepository(application);
        all_Sessions = session_repo.getAllCurrSessions(); // from workout repository
    }

    public void Insert(SessionEntity entity) {
        session_repo.Insert(entity);
    }

    public void Update(SessionEntity entity) {
        session_repo.Update(entity);
    }

    public void Delete(SessionEntity entity) {
        session_repo.Delete(entity);
    }

    public void DeleteAllUsers() {
        session_repo.DeleteAllWorkouts();
    }

    public LiveData<List<SessionEntity>> getAllCurrSessions() {
        return all_Sessions;
    }
}
