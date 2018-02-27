package com.plusultra.csci448.journalshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Han on 2/27/18.
 */

public class JournalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        EditText entry = (EditText) v.findViewById(R.id.entry_box);
        entry.setGravity(Gravity.CENTER);

        return v;
    }
}
