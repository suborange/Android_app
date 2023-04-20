package com.example.project_02.GymJourney;

import android.content.Context;
import android.content.Intent;

public class SetsActivity {
    /** 0.02.04.042023:
     *    todo need to think about sets layout, and sets_list
     *     to show the data ( this is probably where another loop is needed)
     */



    // to switch to current journey activity ( this one )
    public static Intent IntentFactory(Context pkgContext) {
        return new Intent(pkgContext, SetsActivity.class);
    }
}
