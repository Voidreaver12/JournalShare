package com.plusultra.csci448.journalshare;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;


/**
 * Created by ndeibert on 3/16/2018.
 */

public class MapActivity extends SingleFragmentActivity {
    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, MapActivity.class);
        return intent;
    }
    @Override
    protected Fragment createFragment() {
        return MapFragment.newInstance();
    }

}
