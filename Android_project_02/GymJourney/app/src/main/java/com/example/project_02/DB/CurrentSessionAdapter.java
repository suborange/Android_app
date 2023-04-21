package com.example.project_02.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.project_02.GymJourney.CurrentSessionActivity;
import com.example.project_02.GymJourney.SessionEntity;
import com.example.project_02.GymJourney.SetsEntity;
import com.example.project_02.R;

import java.util.ArrayList;
import java.util.List;

/**     SPECIFICALLY FOR CURRENT SESSION ADAPTER
 *
 */
public class CurrentSessionAdapter extends RecyclerView.Adapter<CurrentSessionAdapter.CurrentSessionHolder> {

    /** 0.02.05.042023: created and implemented this adapter for the session
     *
     */
    private List<SessionEntity> sessions = new ArrayList<>();

    // need DAO to acces sets here
    myDAO DAO_set;

    @NonNull
    @Override
    public CurrentSessionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.session_list, parent, false);
        return new CurrentSessionHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrentSessionHolder holder, int position) {
//
//        SessionEntity current_session = sessions.get(position);
//        String session_item = current_session.get();
//        holder.text_session_item.setText(session_item);
    }

    @Override
    public int getItemCount() {
        return sessions.size();
    }

    public void setSessions(List<SessionEntity> sessions) {
        this.sessions = sessions;
        notifyDataSetChanged();
    }

    public SessionEntity getSessionAt(int position) {
        return sessions.get(position);
    }



    class CurrentSessionHolder extends RecyclerView.ViewHolder {
        private TextView text_session_item;
        private TextView text_session_id;



        public CurrentSessionHolder(@NonNull View itemView) {
            super(itemView);
            text_session_item = itemView.findViewById(R.id.session_text);
            text_session_id = itemView.findViewById(R.id.session_id_text);


        }
    }
}
