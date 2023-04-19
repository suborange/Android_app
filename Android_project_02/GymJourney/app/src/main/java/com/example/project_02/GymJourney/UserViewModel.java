package com.example.project_02.GymJourney;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.project_02.DB.UserRepository;

import java.util.List;

/**     SPECIFICALLY FOR USER VIEW
 *
 */
public class UserViewModel extends AndroidViewModel {

    /** 0.02.02.041823: created file; implemented all methods for insert, update, delete, delete all, and get all users;
     *
     *
     */
    private UserRepository user_repo;
    private LiveData<List<UserEntity>> all_Users;

    public UserViewModel(@NonNull Application application) {
        super(application);
        user_repo = new UserRepository(application);
        all_Users = user_repo.getAllUsers(); // from user repository
    }

    public void Insert(UserEntity user) {
        user_repo.Insert(user);
    }

    public void Update(UserEntity user) {
        user_repo.Update(user);
    }

    public void Delete(UserEntity user) {
        user_repo.Delete(user);
    }

    public void DeleteAllUsers() {
        user_repo.DeleteAllUsers();
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return all_Users;
    }

}
