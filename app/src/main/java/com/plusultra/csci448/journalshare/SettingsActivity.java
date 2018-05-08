package com.plusultra.csci448.journalshare;

import android.content.Context;
import android.content.Intent;

/**
 * SettingsActivity used to host a SettingsFragment.
 *
 * Created by cwiddico on 2/27/2018.
 */

public class SettingsActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, SettingsActivity.class);
        return intent;
    }
    @Override
    protected android.support.v4.app.Fragment createFragment() {
        return SettingsFragment.newInstance();
    }

}
