package com.example.project_02.DB;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_02.GymJourney.SessionEntity;
import com.example.project_02.GymJourney.WorkoutEntity;
import com.example.project_02.R;

import java.util.ArrayList;
import java.util.List;

/**     SPECIFICALLY FOR SESSIONS ADAPTER
 *
 */
public class SessionsAdapter extends RecyclerView.Adapter<SessionsAdapter.SessionsHolder>{

    /**
     * 0.02.04.042023: created file;
     * todo created recycler view, to be used for the admin managing activity;
     *      * implemented onCreate view holder, onBind view holder, and user holder class with getters and setters;
     */


    private List<SessionEntity> sessions =new ArrayList<>();

    @NonNull
    @Override
    public SessionsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
            View item_view= LayoutInflater.from(parent.getContext())
            .inflate(R.layout.session_list,parent,false);
            return new SessionsHolder(item_view);
            }

    @Override
    public void onBindViewHolder(@NonNull SessionsHolder holder,int position){
        SessionEntity current_session= sessions.get(position);
        String session_item = "";

        session_item += " sets @";

        session_item +=" reps @";

        session_item +=" - ";

        session_item +=" lbs";

        // finish this part and then set it.

        // reuse session_item
        session_item = "#" + String.valueOf(current_session.getSession_ID());
        holder.text_session_id.setText(session_item);


        // TODO
//            String session_item=current_session.get();
//            holder.text_session_item.setText(session_item);
            }

    @Override
    public int getItemCount(){
            return sessions.size();
            }

    public void setSessions(List<SessionEntity> session){
            this.sessions =session;
            notifyDataSetChanged(); // TODO will be changed later, not generally used, more efficient ways.
            }

    public SessionEntity getSessionAt(int position){
            return sessions.get(position);
            }


    class SessionsHolder extends RecyclerView.ViewHolder {
        private TextView text_session_item;
        private TextView text_session_id;


        public SessionsHolder(@NonNull View itemView) {
            super(itemView);
            text_session_item = itemView.findViewById(R.id.session_text);
            text_session_id = itemView.findViewById(R.id.session_id_text);


        }
    }

}
