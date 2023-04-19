/**
 * @author Ethan Bonavida
 * @since April 10, 2023
 * @version 0.02.02.041823
 * @description: an android app where a use can log in as a user, or admin. the user will be able to create a workout journey to keep track and help guide their gym journey.
 * Hopefully a simple and elegant way to track gym progress, with limited typing and hassles.
 */
package com.example.project_02.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02.GymJourney.UserEntity;
import com.example.project_02.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**     SPECIFICALLY FOR USER ADAPTER
 *
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    /** 0.02.02.041823: created recycler view, to be used for the admin managing activity;
     * implemented onCreate view holder, onBind view holder, and user holder class with getters and setters;
     *
     */

    private static final String BAR = " | ";
    private List<UserEntity> users = new ArrayList<>();


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list, parent, false);
        return new UserHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        UserEntity current_user = users.get(position);

//        String user_id_formatted = String.format(format_front, current_user.getUser_nickname());
//        String password_formatted = String.format(format_back, current_user.getUser_password());
//        String full_formatted_item = user_id_formatted + BAR + password_formatted;
        String user_item = current_user.getUser_nickname() + BAR + current_user.getUser_password();
        holder.text_useritem.setText(user_item);
        //holder.text_useritem.setText(full_formatted_item);
        holder.text_userid.setText(String.valueOf(current_user.getUser_ID()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
        notifyDataSetChanged(); // TODO will be changed later, not generally used, more efficient ways.
    }

    public UserEntity getUserAt(int position) {
        return users.get(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView text_userid;
        private TextView text_useritem;


        public UserHolder(@NonNull View itemView) {
            super(itemView);
            text_userid = itemView.findViewById(R.id.userid_item_text);
            text_useritem = itemView.findViewById(R.id.user_item_text);


        }
    }
}
