package com.plusultra.csci448.journalshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

/**
 * Created by Han on 2/27/18.
 */

public class JournalFragment extends Fragment {

    private EditText mEntry;
    private TextView mDate;
    private TextView mTime;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        Date placeHolder = new Date();


        mEntry = (EditText) v.findViewById(R.id.entry_box);

        mDate = (TextView) v.findViewById(R.id.entry_date);
        mDate.setText(placeHolder.getDate());

        mTime = (TextView) v.findViewById(R.id.entry_time);
        mTime.setText((int) placeHolder.getTime());

        return v;
    }
}
