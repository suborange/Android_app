package com.example.project_02.GymJourney;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_02.DB.WorkoutRepository;

import java.util.List;

/**     SPECIFICALLY FOR CURRENT JOURNEY VIEW
 *
 */
public class WorkoutViewModel extends AndroidViewModel {
    /** 0.02.03.041923: created file; implemented all methods for insert, update, delete, delete all, and get all workouts;
     * DONE?
     *
     */
    private WorkoutRepository workout_repo;
    private LiveData<List<WorkoutEntity>> all_Workouts;

    public WorkoutViewModel(@NonNull Application application) {
        super(application);
        workout_repo = new WorkoutRepository(application);
        all_Workouts = workout_repo.getAllWorkouts(); // from workout repository
    }

    public void Insert(WorkoutEntity entity) {
        workout_repo.Insert(entity);
    }

    public void Update(WorkoutEntity entity) {
        workout_repo.Update(entity);
    }

    public void Delete(WorkoutEntity entity) {
        workout_repo.Delete(entity);
    }

    public void DeleteAllUsers() {
        workout_repo.DeleteAllWorkouts();
    }

    public LiveData<List<WorkoutEntity>> getAllWorkouts() {
        return all_Workouts;
    }

}
