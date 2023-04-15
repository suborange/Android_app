package com.example.project_02.GymJourney;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.project_02.R;

public class ManageaccActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageacc);
    }



    // to switch to manage acc actvity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, ManageaccActivity.class);
    }
}