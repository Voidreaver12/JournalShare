package com.plusultra.csci448.journalshare;

import android.support.v4.app.Fragment;

import java.util.UUID;

/**
 * Created by ndeibert on 2/27/2018.
 */

public class JournalActivity extends SingleFragmentActivity {

    private static final String EXTRA_ENTRY_ID = "com.csci448.PLUSULTRA.entry_id";

    @Override
    protected Fragment createFragment() {
        //return new CrimeFragment();
        UUID entryId = (UUID) getIntent().getSerializableExtra(EXTRA_ENTRY_ID);
        return JournalFragment.newInstance(entryId);
    }
}
