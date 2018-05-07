package com.plusultra.csci448.journalshare;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by ndeibert on 5/7/2018.
 */

public class JournalShareApplication extends Application {
    private static final String TAG = "csci488.journal.app";

    @Override
    public void onCreate() {
        super.onCreate();
        if(!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
