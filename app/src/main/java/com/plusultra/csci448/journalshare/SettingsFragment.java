package com.plusultra.csci448.journalshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ndeibert on 3/16/2018.
 */

public class SettingsFragment extends Fragment {
    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();
        // add args here
        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
