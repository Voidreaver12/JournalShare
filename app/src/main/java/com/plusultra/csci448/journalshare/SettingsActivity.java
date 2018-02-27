package com.plusultra.csci448.journalshare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by cwiddico on 2/27/2018.
 */

public class SettingsActivity extends Fragment {
    private Spinner spinner;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.settings_activity, container, false);
        Spinner fontSpinner = (Spinner)v.findViewById(R.id.font_spinner);
        String[] items = new String[]{"1", "2", "three"};
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //fontSpinner.setAdapter(adapter);

        return v;
    }
}
