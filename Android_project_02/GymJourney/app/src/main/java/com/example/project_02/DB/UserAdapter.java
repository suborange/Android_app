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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {
    /** 0.02.02.041823: created recycler view, to be used for the admin managing activity;
     *
     *
     *
     *
     */

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
        holder.text_useritem.setText(current_user.getUser_nickname() + "        |       " + current_user.getUser_password());
        holder.text_userid.setText(String.valueOf(current_user.getUser_ID()));

    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
        notifyDataSetChanged(); // will be changed later, not generally used, more efficient ways.
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
