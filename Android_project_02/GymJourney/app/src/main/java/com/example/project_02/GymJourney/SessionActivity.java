package com.example.project_02.GymJourney;

import android.content.Context;
import android.content.Intent;

public class SessionActivity {

    // to switch to current journey activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, SessionActivity.class);
    }
}