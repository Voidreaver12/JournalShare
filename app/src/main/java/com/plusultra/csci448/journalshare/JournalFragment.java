package com.plusultra.csci448.journalshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


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
    private LinearLayout mContainer;

    private int entryBackgroundResId = R.drawable.default_bg;

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
        JournalBook journalBook = JournalBook.get(getActivity());
        mEntry = journalBook.getEntry(entryId);
        if (journalBook.isBgSet()) { entryBackgroundResId = journalBook.getEntryBgId(); }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        mContainer = (LinearLayout) v.findViewById(R.id.entry_container);
        mContainer.setBackgroundResource(entryBackgroundResId);

        mTitle = (EditText) v.findViewById(R.id.entry_title);
        mTitle.setText(mEntry.getTitle());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String converted = s.toString();
                if(converted.contains("\n")){
                    //There are occurences of the newline character
                    converted = converted.replace("\n", "");
                    mEntry.setTitle(converted);
                } else {
                    mEntry.setTitle(converted);
                }
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
        //MenuItem share = menu.findItem(R.id.menu_item_map_share);
        //MenuItem delete = menu.findItem(R.id.menu_item_delete);
        //MenuItem camera = menu.findItem(R.id.menu_item_camera);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_map_share:
                Toast.makeText(getActivity(), R.string.toast_share, Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_item_delete:
                JournalBook journal = JournalBook.get(getActivity());
                journal.deleteEntry(mEntry.getId());
                Intent intent = new Intent(getActivity(), JournalListActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_item_camera:
                Toast.makeText(getActivity(), R.string.toast_camera, Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
