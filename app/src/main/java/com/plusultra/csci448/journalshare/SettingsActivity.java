package com.plusultra.csci448.journalshare;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

/**
 * Created by cwiddico on 2/27/2018.
 */

public class SettingsActivity extends SingleFragmentActivity {

    //**************************************************************************************************************************
    // Pass data between activity and fragment with bundle args, add parameters to newIntent and createFragment, and newInstance
    //**************************************************************************************************************************

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, SettingsActivity.class);
        return intent;
    }
    @Override
    protected android.support.v4.app.Fragment createFragment() {
        return SettingsFragment.newInstance();
    }

}
