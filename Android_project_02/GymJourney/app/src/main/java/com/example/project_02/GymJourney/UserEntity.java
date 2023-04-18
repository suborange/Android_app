/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.01.05.041723
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.GymJourney;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project_02.DB.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)
public class UserEntity {
    /**
     * 0.01.00.041023: created and added as entity; added fields;
     */

    @PrimaryKey(autoGenerate = true)
    private int User_ID = 0;

    private String User_nickname; // the user_id entered
    private String User_password; // the password entered
    private boolean is_admin; // maybe make a byte, and 0 == false, anything else not 0 == true
    private float User_weight; // how to delete?
    private boolean logged_in;

    public UserEntity() {

    }

    public UserEntity(String nickname, String password) {
        this.User_nickname = nickname;
        this.User_password = password;
        this.is_admin = false;
        this.logged_in = true; // should initially set this when the user is created and logged in?
        // TODO maybe switch logged in to false, and log in in the places where it should login

    }

    // only get the User ID
    public int getUser_ID() {
        return User_ID;
    }

    // should i add a log in function here? or prob not, in activity and then get all gym info for that user. or just set to true, and so all that info will be displayed.
    public boolean isLogged_in() {
        return logged_in;
    }

    public void setLogged_in(boolean logged_in) {
        this.logged_in = logged_in;
    }

    public String getUser_nickname() {
        return User_nickname;
    }

    public void setUser_nickname(String user_nickname) {
        User_nickname = user_nickname;
    }

    public String getUser_password() {
        return User_password;
    }

    public void setUser_password(String user_password) {
        User_password = user_password;
    }

    public boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public float getUser_weight() {
        return User_weight;
    }

    public void setUser_weight(float user_weight) {
        User_weight = user_weight;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }
}
