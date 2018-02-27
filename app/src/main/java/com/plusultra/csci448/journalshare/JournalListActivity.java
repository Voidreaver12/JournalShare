package com.plusultra.csci448.journalshare;

import android.support.v4.app.Fragment;

/**
 * Created by ndeibert on 2/27/2018.
 */

public class JournalListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() { return new JournalListFragment(); }

    @Override
    public void onEntrySelected(JournalEntry entry) {
        if (findViewById(R.id.detail))
    }

}
