package com.plusultra.csci448.journalshare;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ndeibert on 2/27/2018.
 */

public class JournalListFragment extends Fragment {

    private RecyclerView mJournalRecyclerView;
    private LinearLayout mEmptyView;
    private Button mEmptyViewNewEntryButton;
    private JournalAdapter mAdapter;
    private Callbacks mCallbacks;

    public interface Callbacks {
        void onEntrySelected(JournalEntry entry);
    }


    private void createNewJournalEntry() {
        JournalEntry entry = new JournalEntry();
        JournalBook.get(getActivity()).addEntry(entry);
        updateUI();
        mCallbacks.onEntrySelected(entry);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journal_list, container, false);

        mJournalRecyclerView = view.findViewById(R.id.journal_recycler_view);
        mJournalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mEmptyView = view.findViewById(R.id.empty_list_view);

        mEmptyViewNewEntryButton = view.findViewById(R.id.empty_list_new_entry);
        mEmptyViewNewEntryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewJournalEntry();
            }
        });
        updateUI();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_journal_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_new_journal_entry:
                createNewJournalEntry();
                return true;
            case R.id.menu_item_map:
                // map
                return true;
            case R.id.menu_item_settings:
                // settings
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateUI() {
        JournalBook book = JournalBook.get(getActivity());
        List<JournalEntry> entries = book.getEntries();
        if (mAdapter == null) {
            mAdapter = new JournalAdapter(entries);
            mJournalRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
        if (entries.size() > 0) {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    private class JournalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private JournalEntry mEntry;
        private TextView mTitleTextView;
        private TextView mDateTextView;

        public JournalHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = itemView.findViewById(R.id.list_item_entry_title_text_view);
            mDateTextView = itemView.findViewById(R.id.list_item_entry_date_text_view);
        }

        @Override
        public void onClick(View v) { mCallbacks.onEntrySelected(mEntry); }

        public void bindEntry(JournalEntry entry) {
            mEntry = entry;
            mTitleTextView.setText(mEntry.getTitle());
            mDateTextView.setText(mEntry.getDate().toString());
        }
    }

    private class JournalAdapter extends RecyclerView.Adapter<JournalHolder> {
        private List<JournalEntry> mEntries;
        public JournalAdapter(List<JournalEntry> entries) { mEntries = entries; }

        @Override
        public JournalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_entry, parent, false);
            return new JournalHolder(view);
        }

        @Override
        public void onBindViewHolder(JournalHolder holder, int position) {
            JournalEntry entry = mEntries.get(position);
            holder.bindEntry(entry);
        }

        @Override
        public int getItemCount() { return mEntries.size(); }
    }
}
