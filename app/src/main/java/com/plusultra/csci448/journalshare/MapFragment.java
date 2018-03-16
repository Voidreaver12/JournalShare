package com.plusultra.csci448.journalshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by ndeibert on 3/16/2018.
 */

public class MapFragment extends Fragment {

    public static MapFragment newInstance() {
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
