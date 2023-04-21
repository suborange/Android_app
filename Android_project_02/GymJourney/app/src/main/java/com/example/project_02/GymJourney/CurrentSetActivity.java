package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.project_02.DB.myDAO;
import com.example.project_02.R;

public class CurrentSetActivity extends AppCompatActivity {


    TextView text_reps;
    TextView text_weight;

    // DAO


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_set);
    }
}