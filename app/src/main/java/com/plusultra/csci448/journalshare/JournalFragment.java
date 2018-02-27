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
import java.util.UUID;

/**
 * Created by Han on 2/27/18.
 */

public class JournalFragment extends Fragment {

    private static final String ARG_ENTRY_ID = "entry_id";

    private JournalEntry mEntry;
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
        UUID entryId = (UUID) getArguments().getSerializable(ARG_ENTRY_ID);
        mEntry = JournalBook.get(getActivity()).getEntry(entryId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        Date placeHolder = new Date();


        mText = (EditText) v.findViewById(R.id.entry_box);

        mDate = (TextView) v.findViewById(R.id.entry_date);
        mDate.setText(placeHolder.getDate());

        mTime = (TextView) v.findViewById(R.id.entry_time);
        mTime.setText((int) placeHolder.getTime());

        return v;
    }
}
