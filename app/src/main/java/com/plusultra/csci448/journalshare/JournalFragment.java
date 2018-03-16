package com.plusultra.csci448.journalshare;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import java.util.UUID;

/**
 * Created by Han on 2/27/18.
 */

public class JournalFragment extends Fragment {

    private static final String ARG_ENTRY_ID = "entry_id";

    private JournalEntry mEntry;
    private EditText mTitle;
    private EditText mText;
    private TextView mDate;
    private TextView mTime;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onEntryUpdated(JournalEntry entry);
    }

    private void updateEntry() {
//        CrimeLab.get(getActivity()).updateCrime(mCrime);
        mCallbacks.onEntryUpdated(mEntry);
    }

    public static JournalFragment newInstance(UUID entryId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_ENTRY_ID, entryId);
        JournalFragment fragment = new JournalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID entryId = (UUID) getArguments().getSerializable(ARG_ENTRY_ID);
        mEntry = JournalBook.get(getActivity()).getEntry(entryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        mTitle = (EditText) v.findViewById(R.id.entry_title);
        mTitle.setText(mEntry.getTitle());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEntry.setTitle(s.toString());
                //update entry
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mText = (EditText) v.findViewById(R.id.entry_box);
        mText.setText(mEntry.getText());
        mText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mEntry.setText(s.toString());
                // update entry
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDate = (TextView) v.findViewById(R.id.entry_date);
        mDate.setText(mEntry.getDate().toString());
        mTime = (TextView) v.findViewById(R.id.entry_time);


        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_journal, menu);

    }
}
